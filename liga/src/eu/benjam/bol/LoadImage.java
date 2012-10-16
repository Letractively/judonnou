package eu.benjam.bol;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class LoadImage {
	public static void main(String[] args) throws IOException {
		LoadImage li = new LoadImage();
		for (int i = 0; i < 1; i++) {			
			System.out.println("Getting next Image...");
			li.getImage();
		}
	}
	
	int z = 0;
	int o = 0;
	int width;
	int height;
	int obj_nr = 0;
	int cap_nr = 0;
	int proNumber;
	ArrayList<File> files = new ArrayList<File>();
	ArrayList<FieldObject> allBlackObjects = new ArrayList<FieldObject>();
	ArrayList<Point> fieldObjectPixels = new ArrayList<Point>();
	ArrayList<Point> points = null;
	Point next;
	ArrayList<Match> matches = new ArrayList<Match>();
	
	public void getImage() throws IOException  {

		files.clear();
		allBlackObjects.clear();
		fieldObjectPixels.clear();
		points = null;
		next = null;

		File gameImage1 = new File("d://objects/game2.png");
		File output = new File("d://out.png");

		/* Color Image*/
		BufferedImage bi = ImageIO.read(gameImage1);
		BufferedImage colorImage = new BufferedImage(bi.getWidth(null), bi.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
		//colorImage = new Contrast(image).contrast(0.3);

		
		
		Graphics2D gColor = (Graphics2D) colorImage.getGraphics();
		gColor.drawImage(bi, 0, 0, null);
		//Save Color Img as output.png
		String outputPath = "d://objects/output.png";
		File outputFile = new File(outputPath);
		ImageIO.write(colorImage, "png", outputFile);
		System.out.println("test");
		
		/* Get image resolution */
		width = bi.getWidth();
		height = bi.getHeight();

		/* GEHE JEDEN PIXEL DURCH */
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Test every Pixel if Colored or not
				/* WENN AKTUELLE PIXELFARBE -1 < 0 IST */
				/** SUCHE GRÜN **/
				Color tempCol = new Color(colorImage.getRGB(x, y));
				if(tempCol.getGreen() > 100){
					//System.out.println(tempCol.getGreen());
					if ((colorImage.getRGB(x, y) ) < -1) {
						Point tempPoint = new Point(x, y);
						tempPoint.setColor(colorImage.getRGB(x, y));
						fieldObjectPixels.add(tempPoint);
					}
				}
				
				
				
			}
		}
		if (fieldObjectPixels.size() > 0) {
			//Go on
			createFieldObject();
		}else {
			System.out.println("No colored FieldObjects found.");
		}
	
		gColor.dispose();
		ImageIO.write(colorImage, "png", output);
	}
	
	
	public void createFieldObject() throws IOException {
		while (freePixel()) {
			/** Creating Player/Ball Object **/
			Point currentPoint = getNextFreePixel();
			points = new ArrayList<Point>();
			calcNeighbors(currentPoint);
			
			if(points.size() > 20) {
				FieldObject fo = new FieldObject();
				fo.setPoints(points);
				allBlackObjects.add(fo);
				//allBlackObjects.add(currentObject);
			}			
		}
		// System.out.println(allBlackObjects.size() + " Object/s");
		// System.out.println("---------------------------------");

		Iterator<FieldObject> allBlackObjectsIt = allBlackObjects.iterator();
		System.out.println(allBlackObjects.size() + " Objects found.");
		while (allBlackObjectsIt.hasNext()) {	
			FieldObject currentObject = allBlackObjectsIt.next();
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
		Iterator<Point> blackPixelsIt = fieldObjectPixels.iterator();
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
		Iterator<Point> objectPixelsIt = fieldObjectPixels.iterator();
		while (objectPixelsIt.hasNext()) {
			if (!objectPixelsIt.next().isChecked())
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
		points.add(currentPoint);
		currentPoint.checked = true;
		Iterator<Point> blackPixelIt = fieldObjectPixels.iterator();
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

	public void writeObject(FieldObject fieldObject) throws IOException {		
		obj_nr ++;
		int HX = 0;
		int HY = 0;
		int LX = 999;
		int LY = 999;
		
			proNumber = 6;
			
			Iterator<Point> getOffsetIt = fieldObject.getPoints().iterator();
			//GET OFFSET
			
			BufferedImage bufImgObj = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gObject = (Graphics2D) bufImgObj.getGraphics();
			gObject.setColor(fieldObject.getAvgColor());
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
				//gObject.setBackground(Color.green);
				gObject.drawLine(point.getX(),point.getY(), point.getX(), point.getY());				
			}				   
			
			//ERSTELLE SNIPPETFILE

			File fileSnippet = new File("d://snippets/snippet_" + cap_nr + "_" + obj_nr + ".png");

			//ERSTELLE OBJECTFILE
			String pathnameObject = "d://objects/obj_" + cap_nr + "_" + obj_nr + ".png";
			File fileObject = new File(pathnameObject);
			
			BufferedImage bufImgSni = new BufferedImage(HX-LX, HY-LY, BufferedImage.TYPE_INT_RGB);
			
			//ERSTELLE GRAFIK
			Graphics2D gSnippet = (Graphics2D) bufImgSni.getGraphics();
			Iterator<Point> currentObjIt = points.iterator();
			
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
