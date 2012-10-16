package eu.benjam.bol;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;


public class CrackCap {
	int z = 0;
	int o = 0;
	int width;
	int height;
	int obj_nr = 0;
	int cap_nr = 0;
	int proNumber;
	ArrayList<File> files = new ArrayList<File>();
	ArrayList<ArrayList<Point>> allBlackObjects = new ArrayList<ArrayList<Point>>();
	ArrayList<Point> blackPixels = new ArrayList<Point>();
	ArrayList<Point> currentObject = null;
	Point next;
	ArrayList<Match> matches = new ArrayList<Match>();

	public static void main(String[] args) throws IOException {
		CrackCap cc = new CrackCap();
		for (int i = 0; i < 1; i++) {			
			System.out.println("\nGetting next Captcha...");
			cc.getImage();
			cc.cap_nr ++;			
			Iterator<Match> matchesIT = cc.matches.iterator();
			while (matchesIT.hasNext()) {
				Match next = matchesIT.next();
				System.out.println(next.score + " " +  next.digit);
				
			}			
		}
	}
	
	
	public void getImage() throws IOException {

		files.clear();
		allBlackObjects.clear();
		blackPixels.clear();
		currentObject = null;
		next = null;

		URL captchaURL = new URL("http://berlin.pennergame.de/security/captcha1.jpg");
		File gameImage1 = new File("d://1.png");
		File source = new File("d:test.png");
		File output = new File("d:out.png");
		File outputColor = new File("d:out_color.png");

		BufferedImage bi = ImageIO.read(gameImage1);
		//BufferedImage bi = ImageIO.read(captchaURL.openStream());
		
		
		ImageIO.write(bi, "png", output);
		
		// BufferedImage bufferedImage = new BufferedImage(200,
		// 200,BufferedImage.TYPE_BYTE_INDEXED);

		BufferedImage blackAndWhiteImage = new BufferedImage(bi.getWidth(null),
				bi.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
		
		BufferedImage colorImage = new BufferedImage(bi.getWidth(null),
				bi.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
		
		
		Graphics2D gColor = (Graphics2D) colorImage.getGraphics();
		gColor.drawImage(bi, 0, 0, null);
		
		Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
		g.drawImage(bi, 0, 0, null);
		

		width = bi.getWidth();
		height = bi.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				// Test every Pixel if Black or White
				if ((blackAndWhiteImage.getRGB(x, y) * -1) > 2) {
					blackPixels.add(new Point(x, y));
					// System.out.println("x" + x + " / y" + y );
				}
			}
		}
		if (blackPixels.size() > 0) {
			createPixelObject();
		}

		else {
			System.out.println("No black Pixels.");
		}
		
		
		Iterator<Match> matchesIt = matches.iterator();
		
		Match bestMatch = new Match(0, 0, 0.0, "ZERO");
		while (matchesIt.hasNext()) {
			Match nextMatch = matchesIt.next();
			if (bestMatch.score < nextMatch.score) {
				bestMatch = nextMatch;
			}			
		}
			//DRAW RING
//			System.out.println(bestMatch.getDigit());
//			System.out.println(bestMatch.getScore());
//			System.out.println("x " + bestMatch.getX() + " y " + bestMatch.getY());
			gColor.setColor(Color.RED);
			gColor.drawArc(bestMatch.getX(), bestMatch.getY(), 5, 5, 0, 360);

	
		g.dispose();
		gColor.dispose();
		
		ImageIO.write(blackAndWhiteImage, "png", output);
		ImageIO.write(colorImage, "png", outputColor);
		
	}

	public void createPixelObject() throws IOException {
		while (freePixel()) {
			Point currentPoint = getNextFreePixel();
			currentObject = new ArrayList<Point>();
			calcNeighbors(currentPoint);
			if(currentObject.size() > 180) {
				allBlackObjects.add(currentObject);
			}			
		}
		// System.out.println(allBlackObjects.size() + " Object/s");
		// System.out.println("---------------------------------");

		Iterator<ArrayList<Point>> allBlackObjectsIt = allBlackObjects.iterator();
		System.out.println(allBlackObjects.size() + " Objects found.");
		while (allBlackObjectsIt.hasNext()) {			
			ArrayList<Point> currentObject = allBlackObjectsIt.next();
			writeObject(currentObject);

//			Iterator<Point> currentObjectIt = currentObject.iterator();
//			while (currentObjectIt.hasNext()) {
//				Point currentPoint = currentObjectIt.next();
//				// System.out.println(currentPoint.getX() + "/" +
//				// currentPoint.getY());
//
//			}			
		}		

	}

	public Point getNextFreePixel() {
		Point freePixel = null;
		Iterator<Point> blackPixelsIt = blackPixels.iterator();
		while (blackPixelsIt.hasNext()) {
			Point tempPixel = blackPixelsIt.next();
			if (!tempPixel.isChecked()) {
				freePixel = tempPixel;
				break;
			}
		}
		return freePixel;
	}

	public boolean freePixel() {
		Iterator<Point> blackPixelsIt = blackPixels.iterator();
		while (blackPixelsIt.hasNext()) {
			if (!blackPixelsIt.next().isChecked())
				return true;
		}
		return false;
	}

	public boolean isNeighbor(Point first, Point second) {
		if (first.getX() == second.getX() || first.getX() == second.getX() + 1
				|| first.getX() == second.getX() - 1) {
			if (first.getY() == second.getY()
					|| first.getY() == second.getY() + 1
					|| first.getY() == second.getY() - 1) {
				return true;
			}
		}
		return false;
	}

	public void calcNeighbors(Point currentPoint) {
		currentObject.add(currentPoint);
		currentPoint.checked = true;
		Iterator<Point> blackPixelIt = blackPixels.iterator();
		while (blackPixelIt.hasNext()) {
			Point next = blackPixelIt.next();
			if (!next.checked) {
				if (isNeighbor(currentPoint, next)) {
					next.checked = true;
					calcNeighbors(next);
				}
			}
		}
	}
	
	int mix = 0;
	int chars = 0;

	public void writeObject(ArrayList<Point> currentObject) throws IOException {		
		obj_nr ++;
		int HX = 0;
		int HY = 0;
		int LX = 999;
		int LY = 999;
		
			proNumber = 6;
			
			Iterator<Point> getOffsetIt = currentObject.iterator();		
			//GET OFFSET
			
			BufferedImage bufImgObj = new BufferedImage(width,height,
					BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D gObject = (Graphics2D) bufImgObj.getGraphics();
			while (getOffsetIt.hasNext()) {
				Point point = getOffsetIt.next();
				int x = point.getX();
				int y = point.getY();

				if (x > HX) {
					HX = x;
				}
				if (y > HY) {
					HY = y;
				}
				if (x < LX) {
					LX = x;
				}
				if (y < LY) {
					LY = y;
				}
				gObject.drawLine(point.getX(),point.getY(), point.getX(), point.getY());				
			}				   
			
			//ERSTELLE SNIPPETFILE

			File fileSnippet = new File("d://snippets/snippet_" + cap_nr + "_" + obj_nr + ".png");

			//ERSTELLE OBJECTFILE
			String pathnameObject = "d://objects/obj_" + cap_nr + "_" + obj_nr + ".png";
			File fileObject = new File(pathnameObject);
			
			BufferedImage bufImgSni = new BufferedImage(HX-LX, HY-LY,
					BufferedImage.TYPE_3BYTE_BGR);
			
			//ERSTELLE GRAFIK
			Graphics2D gSnippet = (Graphics2D) bufImgSni.getGraphics();
			Iterator<Point> currentObjIt = currentObject.iterator();
			
			//ERSTELLE G-BILD OBJEKT OHNE OFFSET
			while (currentObjIt.hasNext()) {
				Point p1 = currentObjIt.next();
				gSnippet.drawLine(p1.getX() - LX, p1.getY() - LY, p1.getX() - LX, p1.getY() - LY);
			}		

			gSnippet.dispose();
			gObject.dispose();
			
			CompareBigObject cbo = new CompareBigObject();
			

			//CREATE CURRENT MATCH
			Match currentMatch = new Match(0, 0, 0.0, "MIX");
			if(bufImgSni.getWidth() < 40 || bufImgSni.getHeight() < 40) {
				
				BufferedImage biggerSni  =  new BufferedImage(bufImgSni.getWidth()+20, bufImgSni.getHeight()+20, BufferedImage.TYPE_BYTE_BINARY);
				Graphics2D gTest = (Graphics2D) biggerSni.getGraphics();
				gTest.drawImage(bufImgSni, 0, 0, null);
				gTest.dispose();
				
				bufImgSni=biggerSni;
			}
		
			System.out.println("Checking Snippet " + obj_nr+ "...");
			//ImageIO.write(bufImgSni, "png", fileSnippet);				
			//currentMatch = cbo.startComparing(bufImgSni, LX, LY);	

			
			System.out.println("-----------------------\n");
			
			
			matches.add(currentMatch);			
			
			ImageIO.write(bufImgObj, "png", fileObject);		

	}

}
