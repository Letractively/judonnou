package eu.benjam.bol;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RotateImage {	
	
	public static void main(String[] args) throws IOException {		
		
		File f6 = new File("d://Captcha/images/numbers/6.png");		
		BufferedImage biNumber = ImageIO.read(f6);
		RotateImage ri = new RotateImage();		
		ri.flipImage(biNumber,90);		
		
	}
	
	public BufferedImage flipImage(BufferedImage img, double angle) throws IOException {
		int HX = 0;
		int HY = 0;
		int LX = 999;
		int LY = 999;

		int w = img.getWidth();    
		int h = img.getHeight();
		int max = w;
		
		double temp = (w*w) + (h*h);
		
		int pyth = (int) Math.pow(temp, (double)1/2);	
	
		if (h>max) {
			max = h;
		}
		int position = 0;
		
		BufferedImage rotated_img = new BufferedImage(max*2, max*2, BufferedImage.TYPE_BYTE_BINARY);    
		Graphics2D g = rotated_img.createGraphics();    
		g.rotate(Math.toRadians(angle), pyth/2,  pyth/2);   
		g.drawImage(img, null, position, -position);
		
		for (int y = 0; y < pyth; y++) {
			for (int x = 0; x < pyth; x++) {

				// Test every Pixel if Black or White
				if ((rotated_img.getRGB(x, y) * -1) < 2) {
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
				}
			}
		}

		BufferedImage biCropRotated = new BufferedImage(HX-LX+1, HY-LY+1, BufferedImage.TYPE_BYTE_BINARY);		
		Graphics2D gCrop = biCropRotated.createGraphics();
		gCrop.translate(LX*-1, LY*-1);	
		gCrop.drawImage(rotated_img, 0, 0, null);
		gCrop.dispose();
		
//		File rotatedFile = new File("d://Captcha/images/numbers/rotated"+ System.currentTimeMillis() + ".png");	
//		ImageIO.write(biCropRotated, "png", rotatedFile);
		
		return biCropRotated;		
	}
}
