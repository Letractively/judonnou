package eu.benjam.liga;


import ij.*;
import ij.gui.NewImage;
import ij.process.*;

import java.util.Vector;

import eu.benjam.hough.HoughLine;
import eu.benjam.hough.HoughTransform;

import java.awt.Color;
import java.awt.image.BufferedImage;
public class LigaFilterGreen {

	public static void main(String[] args) {
		LigaFilterGreen fbm = new LigaFilterGreen();
		String imgPath1 = "D:/liga/90.png";
		String imgPath2 = "D:/liga/schalke.png";

//		fbm.filterGreen(new ImagePlus(imgPath1), new ImagePlus(imgPath2));
		
	}

	/* image2: the bigger image */
	public BufferedImage filterGreen(BufferedImage bi){
		  
		ImagePlus image1 = new ImagePlus("bi", bi);
		ImagePlus image2 = new ImagePlus("bi", bi);
//		IJ.run(image1, "Make Binary","");
//		IJ.run(image2, "Make Binary","");
//		image2.show();
//		image1.show();
//		image2.show();
//		
//		image2.setRoi(488, 789, image1.getWidth(), image1.getHeight());
//		image2.show();
//		ImageProcessor processor2 = image2.getProcessor();
//		ImageProcessor ip2 = processor2.crop();
//		
//		BufferedImage croppedImage = ip2.getBufferedImage();
//		new ImagePlus("croppedImage", croppedImage).show();
//		
		
		
	
		int width1 = image1.getWidth();
		int height1 = image1.getHeight();
		int width2 = image2.getWidth();
		int height2 = image2.getHeight();
		
		int step = 1;
		
		double treffer = 0;
		double image1Resolution = width1 * height1;
		double[] best_score = new double[3];
		best_score[0] = 0;//score
		best_score[1] = 0;//x
		best_score[2] = 0;//y
	
		ImagePlus image = NewImage.createRGBImage("Neues Bild",     width2, height2, 1, NewImage.FILL_BLACK);
		ImageProcessor processor = image.getProcessor();
		//Image2
       	for (int y2 = 0; y2 < height2; y2+=step) {
       		for (int x2 = 0; x2 < width2; x2+=step) {
       			
       			int [] colorArrayTemp = image2.getPixel(x2,y2);//Big
       			int redValue = colorArrayTemp[0];
            	int greenValue = colorArrayTemp[1];
            	int blueValue = colorArrayTemp[2];
            	
            	/** MY GREENFILTER **/
//            	if(greenValue>75){
//            		double redPercentage = (new Double(redValue)/new Double(greenValue))*100;
//            		double bluePercentage = (new Double(blueValue)/new Double(greenValue))*100;
//            		if(redPercentage>50 && redPercentage < 85 && bluePercentage < 58){
//            			processor.setColor(Color.GREEN);
//            			processor.drawDot(x2, y2);
//            		}
//            		//System.out.println("greenValue " + greenValue);
//            	}
            	/** MEIN FELDLINIEN FILTER **/
            	if(greenValue>130 && redValue > 90 && blueValue > 75 && greenValue > redValue+10 && greenValue > blueValue +10 && (blueValue+redValue+greenValue)>330) {
            		processor.setColor(Color.WHITE);
            		processor.drawDot(x2, y2);
//            		processor.drawDot(x2-1, y2);
//            		processor.drawDot(x2-1, y2-1);
//            		processor.drawDot(x2, y2-1);
//            		processor.drawDot(x2+1, y2+1);
            	}
            	
       			
            	
//				//Image1
//		        for (int x = 0; x < width1; x++) {
//		        	for (int y = 0; y < height1; y++) {        		
//                   		
//                    	int [] colorArray1 = image1.getPixel(x,y);//Small
//                    	int [] colorArray2 = image2.getPixel(x+x2,y+y2);//Big
//                    	
//                    	int colValueAImage1 = colorArray1[0];//Small
//                    	int colValueAImage2 = colorArray2[0];//Big
//                    	
//                    	if(colValueAImage1 == colValueAImage2){
//                    		treffer++;
//                    	}else{
////                    		treffer--;
//                    	}
//                    }
//        		}
//		        image2.setRoi(x2, y2, image1.getWidth(), image1.getHeight());
//        		ImageProcessor processor2 = image2.getProcessor();
        		
//        		try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		        
        		if((treffer/image1Resolution)>best_score[0]){
        			best_score[0]=treffer/image1Resolution;
        			best_score[1] = x2;
        			best_score[2] = y2;
        		}
            	treffer = 0;
            }
       		//System.out.println("y2: " + y2 + "/" + height2);
        }
       BufferedImage bufferedImage = processor.getBufferedImage();
       System.out.println("LigaFilterGreen processed.");
       return bufferedImage;
       	
/*
 * HUGH
 */
//
//        // create a hough transform object with the right dimensions 
//        HoughTransform h = new HoughTransform(bufferedImage.getWidth(), bufferedImage.getHeight()); 
// 
//        // add the points from the image (or call the addPoint method separately if your points are not in an image 
//        h.addPoints(bufferedImage); 
//        
//        // get the lines out 
//        Vector<HoughLine> lines = h.getLines(1); 
//        System.out.println(lines);
// 
//        // draw the lines back onto the image 
//        for (int j = 0; j < lines.size(); j++) { 
//            HoughLine line = lines.elementAt(j); 
//           // line.draw(bufferedImage, Color.RED.getRGB()); 
//        } 
//		new ImagePlus("bi", bufferedImage).show();       	
//       	
//		
//		/* CRACK CAP */
//		
////		CrackCap cc = new CrackCap();
////		cc.getImage();
//		
//       	System.out.println("best_score: " + best_score[0]);
//       	System.out.println("best_score x: " + best_score[1]);
//       	System.out.println("best_score y: " + best_score[2]);
//        image2.setRoi(new Double(best_score[1]).intValue(), new Double(best_score[2]).intValue(), image1.getWidth(), image1.getHeight());
//		//image2.show();
       
	}
}
