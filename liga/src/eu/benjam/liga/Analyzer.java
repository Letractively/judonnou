package eu.benjam.liga;

import ij.ImagePlus;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import eu.benjam.hough.HoughLine;
import eu.benjam.hough.HoughTransform;

public class Analyzer {
	public static void main(String[] args) {

		try {
				LigaFilterGreen lfg = new LigaFilterGreen();
				LigaFilterObjects lfo = new LigaFilterObjects();
				
				int i = 5;
				File filePath = new File("d:/liga/scenes/"+i+".png/");
				System.out.println(i+".png....");
				BufferedImage inputImage = ImageIO.read(filePath);
				BufferedImage analyzedImage = lfg.filterGreen(inputImage);
				
				
				analyzedImage = lfo.getFilteredImage(analyzedImage);
		        
//		        new ImagePlus("bi", analyzedImage).show();
		        
		        /* HUGH */
		        // create a hough transform object with the right dimensions 
		        HoughTransform h = new HoughTransform(analyzedImage.getWidth(), analyzedImage.getHeight()); 
				 
		        // add the points from the image (or call the addPoint method separately if your points are not in an image 
		        h.addPoints(analyzedImage); 
			
		        // get the lines out 
		        Vector<HoughLine> lines = h.getLines(1); 
		        // draw the lines back onto the image 
		        for (int j = 0; j < lines.size(); j++) { 
		            HoughLine line = lines.elementAt(j); 
		            line.draw(analyzedImage, Color.RED.getRGB()); 
		            System.out.println("line radius from center " + line.r);
		            System.out.println("line angle" + line.theta*180/Math.PI);
		        } 
		        /* HUGH END */
		        new ImagePlus("bi", analyzedImage).show();
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
