package eu.benjam.liga.field;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Field {
	public static void main(String[] args) {
		Field field = new Field();
		field.saveImage("", field.generateImage());
	}
	
	
	/* INPUT */
	Measures m = new Measures(68*2);
	
	private int FIELD_X_PIXEL = m.getFIELD_X();
	private int FIELD_Y_PIXEL = m.getFIELD_Y();
	private int MITTELKREIS_RADIUS = m.getCENTER_CIRCLE_RADIUS();
	
	
	public BufferedImage generateImage(){
		BufferedImage fieldImage = new BufferedImage(FIELD_X_PIXEL,FIELD_Y_PIXEL, BufferedImage.TYPE_INT_RGB);
		Graphics2D gFieldImage = (Graphics2D) fieldImage.getGraphics();
		gFieldImage.setColor(Color.white);
		/*FELD LINIE AUSSEN*/
		gFieldImage.drawRect(0, 0, FIELD_X_PIXEL-1, FIELD_Y_PIXEL-1);
		/*MITTELLINIE*/
		gFieldImage.drawLine(0, FIELD_Y_PIXEL/2, FIELD_X_PIXEL, FIELD_Y_PIXEL/2);
		/*MITTELKREIS*/
		gFieldImage.drawOval(FIELD_X_PIXEL/2-MITTELKREIS_RADIUS, FIELD_Y_PIXEL/2-MITTELKREIS_RADIUS,MITTELKREIS_RADIUS*2,MITTELKREIS_RADIUS*2);
		
		
		
		
		Canvas canvas = new Canvas();
		canvas.createImage(FIELD_X_PIXEL, FIELD_Y_PIXEL);
		
		return fieldImage;
	}
	public void saveImage(String imagePath, BufferedImage bufferedImage){
//		File imageFile = new File(imagePath);
		File imageOutputFile = new File("d://objects/fieldImage.png");
		try {
			ImageIO.write(bufferedImage, "png", imageOutputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
