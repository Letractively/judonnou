package eu.benjam.liga;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ij.IJ;
import ij.ImagePlus;
import ij.io.FileInfo;
import ij.process.ImageProcessor;

	public class jImage {
	
	public static void main(String[] args) {
		jImage ji = new jImage();
		ji.checkPixels();
	}
	public void checkPixels(){
		/*Check Colors of all Pixels */
		String imgPath = "D:/liga/szene1.png";
		String imgOut = "D:/liga/szene1_out.png";
		
		ImagePlus image = new ImagePlus(imgPath);
//        image.show(); 
//		IJ.run(image, "Enhance Contrast", "saturated=115.35");
		IJ.run(image, "Make Binary", "");
//		IJ.run(image, "Find Edges", "");
		
		ImageProcessor processor = image.getProcessor();
		processor.drawLine(0, 0, image.getWidth(), image.getHeight());
		
		BufferedImage croppedImage = processor.getBufferedImage();
		new ImagePlus("croppedImage", croppedImage).show();
		
		for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
            	int [] colorArray = image.getPixel(x,y);
            	int redValue = colorArray[0];
            	int greenValue = colorArray[1];
            	int blueValue = colorArray[2];
            	if(redValue > 200 ){
            		image.setColor(Color.cyan);
            		image.draw(x,y,1,1);
            		image.setRoi(x, y, 1, 1);
            		image.updatePosition(33, 33, 33);
            		//System.out.println("white " + colorArray.toString());
            	}

            }
        }
		image.show(); 

	}
}
