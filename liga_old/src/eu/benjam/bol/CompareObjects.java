package eu.benjam.bol;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CompareObjects {
	
	boolean first = true;
	BufferedImage original;
	int numberGlobal = 0;
	int rotations = 0;	
	int angle = 10;
	int maxrotations = 360 / angle;
	public static void main(String[] args) throws IOException {
		

	//	co.startComparing(6, propose);	
	}
	public Match startComparing(BufferedImage propose, int CX, int CY) throws IOException {
		File f2 = new File("d://Captcha/images/numbers/2.png");	
		File f4 = new File("d://Captcha/images/numbers/4.png");	
		File f5 = new File("d://Captcha/images/numbers/5.png");
		File f6 = new File("d://Captcha/images/numbers/6.png");
		File f7 = new File("d://Captcha/images/numbers/7.png");
		File f8 = new File("d://Captcha/images/numbers/8.png");
		File f9 = new File("d://Captcha/images/numbers/9.png");
		
		double highestScore;
		String scoreDigit = "ZERO";
		
		double score2 = loadImages(f2, propose);
		System.out.println("Checked Number 2...");
		highestScore = score2;
		scoreDigit = "TWO";
		
		double score4 = loadImages(f4, propose);
		System.out.println("Checked Number 4...");
		if (score4 > highestScore) {
			highestScore = score4;
			scoreDigit = "FOUR";
		}
		double score5 = loadImages(f5, propose);
		System.out.println("Checked Number 5...");
		if (score5 > highestScore) {
			highestScore = score5;
			scoreDigit = "FIVE";
		}
		double score6 = loadImages(f6, propose);
		System.out.println("Checked Number 6...");
		if (score6 > highestScore) {
			highestScore = score6;
			scoreDigit = "SIX";
		}
		double score7 = loadImages(f7, propose);
		System.out.println("Checked Number 7...");
		if (score7 > highestScore) {
			highestScore = score7;
			scoreDigit = "SEVEN";
		}
		double score8 = loadImages(f8, propose);
		System.out.println("Checked Number 8...");
		if (score8 > highestScore) {
			highestScore = score8;
			scoreDigit = "EIGTH";
		}
		double score9 = loadImages(f9, propose);
		System.out.println("Checked Number 9...");
		if (score8 > highestScore) {
			highestScore = score9;
			scoreDigit = "NINE";
		}
		//System.out.println(scoreNumber);
		Match myMatch = new Match(CX, CY, highestScore, scoreDigit);
		System.out.println("CHARCHECK: " + myMatch.score + " " + myMatch.digit );
		return myMatch;		
	}
	
	public double loadImages(File f, BufferedImage biPropose) throws IOException  {
		//LODE NUMBER		
		BufferedImage biNumber = ImageIO.read(f);			
		int nHeight = biNumber.getHeight();
		int nWidth = biNumber.getWidth();
		
		
		//SCALE
		BufferedImage scaledImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g = scaledImage.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(biPropose, 0, 0, nWidth, nHeight, null);
		g.dispose();
		
//		File file = new File("d://Captcha/images/numbers/scaled.png");
//		ImageIO.write(scaledImage, "png", file);		
		
		biPropose = scaledImage;	

		//System.out.println("Comparing Proposal " + nHeight*nWidth + "px......");		
		return compareImages(biNumber, biPropose);

	}

	
	public Double compareImages(BufferedImage white, BufferedImage black) throws IOException {
		int height = white.getHeight();
		int width = white.getWidth();		
		int matches = 0;
		int fails = 0;
		double score = 0;
		int max = height*width;	
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				// Test every Pixel if Black or White
				if ((white.getRGB(x, y) * -1) < 2 && (black.getRGB(x, y) * -1) < 2 ) {
					matches++;
				}
				else if ((white.getRGB(x, y) * -1) > 2 && (black.getRGB(x, y) * -1) > 2 ) {
					matches++;
				}
				else {
					fails++;
				}
			}
			
			if (((double)matches/(double)max > score)) {
				score = (double)matches/(double)max;
			}			
		}		

		//System.out.println("fails " + fails + " matches " + matches);
		//System.out.println("score " + score);
		
		
		if(score < 0.7 && rotations < maxrotations ) {
			rotations ++;
			compareImages(white, flipImage(black, angle));	
			return score;
		}
		else if (score >= 0.7) {		
//			File scoreFile = new File("d://Captcha/images/scored/SCORED.png");
//			ImageIO.write(black, "png", scoreFile);
//			long time = System.currentTimeMillis();
//			while(System.currentTimeMillis() < time + 5000)
//			{}
			return score;
		}
		return score;		
	}
	
	public BufferedImage flipImage(BufferedImage img, int angle) throws IOException {
	
		int w = img.getWidth();    
		int h = img.getHeight();          
		BufferedImage rotated_img = new BufferedImage(w, h, img.getType());    
		Graphics2D g = rotated_img.createGraphics();    
		g.rotate(Math.toRadians(angle), w/2, h/2);    
		g.drawImage(img, null, 0, 0);
		return rotated_img;		
	}
	
	
}
