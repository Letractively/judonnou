package eu.benjam.liga;

import ij.ImagePlus;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;


public class LigaFilterObjects {
	int z = 0;
	int o = 0;
	int width;
	int height;
	int obj_nr = 0;
	int cap_nr = 0;
	static String OUTPUT_PATH = "d://liga/objects/";
	ArrayList<File> files = new ArrayList<File>();
	
	ArrayList<Point> tempPointList = new ArrayList<Point>();
	PixelObject currentObject = null;
	//ArrayList<Point> currentObject = null;
	Point next;
	public static void main(String[] args) throws IOException {
		LigaFilterObjects ff = new LigaFilterObjects();
		
		File gameImage1 = new File("d:/liga/BLACK.png/");
		BufferedImage blackAndWhiteImage = ImageIO.read(gameImage1);
		new ImagePlus("bi", ff.getFilteredImage(blackAndWhiteImage)).show();
	}

	public ArrayList<PixelObject> getPixelObjects(BufferedImage img){
		loadAllPointsFromImage(img);
		ArrayList<PixelObject> pixelObjectsList = new ArrayList<PixelObject>();
		while (freePixel()) {
			Point currentPoint = getNextFreePixel();
			currentObject = new PixelObject();
			calcNeighbors(currentPoint);
			/* CUSTOM FILTER */
//			currentObject.fillAmount() < 24
			
			if(currentObject.resolution() > 300) {
				Double ratio = 0.0;
				if(currentObject.width()>currentObject.height()){
					ratio = new Double(currentObject.height()) / new Double(currentObject.width()) *100;
				}else if(currentObject.height()>currentObject.width()){
					ratio = new Double(currentObject.width()) / new Double(currentObject.height()) *100;
				}else{
					ratio = 100.0;
				}
//				System.out.println("ratio " + ratio + "height " + currentObject.height());
				if(ratio<11){
					pixelObjectsList.add(currentObject);
					System.out.println("ratio " + ratio);
				}else if(currentObject.fillAmount() < 20){
					pixelObjectsList.add(currentObject);
				}
					
				//Adding new Object to ArrayList allBlackObjects
				//System.out.println("Found Object");
				
			}			
		}
		System.out.println("Found " + pixelObjectsList.size() +  " objects...");
		return pixelObjectsList;
		
	}
	
	/** INITIAL BLACK WHITE PIXEL CHECK **/
	public void loadAllPointsFromImage(BufferedImage img) {
		ArrayList<PixelObject> points = new ArrayList<PixelObject>();
		files.clear();
		points.clear();
		tempPointList.clear();
		currentObject = null;
		next = null;

		/* Make sure that Image is BW */
		BufferedImage blackAndWhiteImage = new BufferedImage(img.getWidth(null),img.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
		
		Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
		g.drawImage(img, 0, 0, null);

		width = img.getWidth();
		height = img.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Test every Pixel if Black or White
				if ((blackAndWhiteImage.getRGB(x, y) * -1) == 1) {
					tempPointList.add(new Point(x, y));
				}
			}
		}

	}
	public void savePixelObjects(ArrayList<PixelObject> pixelObjectsList){		
		for(int i = 0; i<pixelObjectsList.size();i++){
			savePixelObject(pixelObjectsList.get(i));
		}
		System.out.println("Saved Objects..");
	}
	public void savePixelObject(PixelObject pixelObject){		
		obj_nr ++;
		Iterator<Point> objectPoints = pixelObject.getPoints().iterator();		
		BufferedImage bufImgObj = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D gObject = (Graphics2D) bufImgObj.getGraphics();
		
		while (objectPoints.hasNext()) {
			Point point = objectPoints.next();
			int x = point.getX();
			int y = point.getY();
			gObject.drawLine(x,y,x,y);				
		}				   
		/* PIXELOBJECT AS PNG */
		String pathnameObject = OUTPUT_PATH + "obj_" + obj_nr +"_res_" +pixelObject.width() + "x" + pixelObject.height()+"_"+pixelObject.fillAmount()+ ".png";
		File fileObject = new File(pathnameObject);
		gObject.dispose();
		//System.out.println("Writing object" + obj_nr+ "...");
		try {
			ImageIO.write(bufImgObj, "png", fileObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/** GENERATE BUFFEREDIMAGE WITH OBJECTS **/
	public BufferedImage getFilteredImage(BufferedImage img){
		ArrayList<PixelObject> pixelObjectsList = getPixelObjects(img);
		savePixelObjects(pixelObjectsList);
		PixelObject pixelObject = null;
		BufferedImage bufImgObj = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D gObject = (Graphics2D) bufImgObj.getGraphics();
		
		/* DRAW OBJECTS */
		for(int i = 0; i<pixelObjectsList.size();i++){
			pixelObject = pixelObjectsList.get(i);
			Iterator<Point> objectPoints = pixelObject.getPoints().iterator();
			while (objectPoints.hasNext()) {
				Point point = objectPoints.next();
				int x = point.getX();
				int y = point.getY();
				gObject.drawLine(x,y,x,y);
			}		
		}
		System.out.println("LigaFilterObjects processed...");
		return bufImgObj;
	}
			
		
	
	/*******************************
	 * CALCULATING METHODS 
	 **********************************/
	public void calcNeighbors(Point currentPoint) {
		currentObject.add(currentPoint);
		currentPoint.checked = true;
		Iterator<Point> blackPixelIt = tempPointList.iterator();
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
	public Point getNextFreePixel() {
		Point freePixel = null;
		Iterator<Point> blackPixelsIt = tempPointList.iterator();
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
		Iterator<Point> blackPixelsIt = tempPointList.iterator();
		while (blackPixelsIt.hasNext()) {
			if (!blackPixelsIt.next().isChecked())
				return true;
		}
		return false;
	}

	public boolean isNeighbor(Point first, Point second) {
		if (first.getX() == second.getX() || first.getX() == second.getX() + 1 || first.getX() == second.getX() - 1) {
			if (first.getY() == second.getY() || first.getY() == second.getY() + 1	|| first.getY() == second.getY() - 1) {
				return true;
			}
		}
		return false;
	}
}
