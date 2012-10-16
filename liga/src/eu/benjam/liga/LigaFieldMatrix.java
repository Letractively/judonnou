package eu.benjam.liga;

import ij.ImagePlus;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LigaFieldMatrix {

	int resolution = 1000;
	BufferedImage bufImgObj = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_RGB);
	public static void main(String[] args) {

		Double angle = 95.0;
		
		LigaFieldMatrix matrix = new LigaFieldMatrix();
		matrix.drawMatrixLine(96.0);
		matrix.drawMatrixLine(80.0);
		new ImagePlus("MyImage", matrix.bufImgObj).show();

	}
	
	public void drawMatrixLine(Double angle){
		
		// FROM 0 TO 90
		Double m = Math.tan(Math.toRadians(90-angle));
		System.out.println("Steigung m " + m);
		
		Graphics2D gObject = (Graphics2D) bufImgObj.getGraphics();
		System.out.println(new Double(resolution/m));
		for(int i=0;i<=10;i++){
			gObject.drawLine(0, resolution-i*100, resolution,(resolution-(new Double(resolution*m).intValue()))-i*100);
		}
		
		System.out.println(new Double(resolution*m).intValue() );
		
	}

//	
	

	
	
	
}
