package eu.benjam.bol;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CompareBigObject {
	
	int bigWidth;
	int bigHeight;
	int myLX;
	int myLY;
	int step = 3;
	double angle = 10;
	double currentAngle = -40.0;
	int maxRotationTries = (int) (135/angle);
	int rotationTries = 1;
	BufferedImage biNumber;
	BufferedImage rotated;

	
	Match myMatch = new Match(0,0,0.0,"ZERO");

	
	public static void main(String[] args) {
//		CompareBigObject cbo = new CompareBigObject();
//		
//		File pro = new File("d://Captcha/images/numbers/pro.png");	
//		BufferedImage propose;
//
//
//		try {			
//			propose = ImageIO.read(pro);
//			cbo.bigHeight = propose.getHeight();
//			cbo.bigWidth = propose.getWidth();
//			Match bestMatch = cbo.startComparing(propose, 0, 0);
//			System.out.println("Best Score: " + bestMatch.getScore() + " digit: " + bestMatch.getDigit() );
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}
	
	
	public Match startComparing(BufferedImage propose, int LX, int LY) throws IOException {
		bigWidth = propose.getWidth();
		bigHeight = propose.getHeight();
		myLX = LX;
		myLY = LY;		

		
		
		Match myMatch = new Match(0, 0, 0.0, "ZERO");
		double highestScore = 0.0;		
		
		biNumber = ImageIO.read(new File("d://Eigene Dateien/Daten/workspace/images/numbers/2.png"));
		rotated = biNumber;
		Match myMatch2 = new Match(0,0,0.0,"TWO");
		myMatch2 = compareWithBigImage(propose, "2" , myMatch2);		
		if(highestScore<myMatch2.score) {			
			myMatch = myMatch2;
			highestScore = myMatch.score;
		}	
		System.out.println(myMatch2.score);
		System.out.println("Checked Number 2...");
		
		biNumber = ImageIO.read(new File("d://Eigene Dateien/Daten/workspace/images/numbers/4.png"));
		rotated = biNumber;
		Match myMatch4 = new Match(0,0,0.0,"FOUR");
		myMatch4 = compareWithBigImage(propose, "4" , myMatch4);		
		if(highestScore<myMatch4.score) {
			myMatch = myMatch4;
			highestScore = myMatch.score;
		}
		System.out.println(myMatch4.score);
		System.out.println("Checked Number 4...");
		
		biNumber = ImageIO.read(new File("d://Eigene Dateien/Daten/workspace/images/numbers/5.png"));
		rotated = biNumber;
		Match myMatch5 = new Match(0,0,0.0,"FIVE");
		myMatch5 = compareWithBigImage(propose, "5" , myMatch5);		
		if(highestScore<myMatch5.score) {
			myMatch = myMatch5;
			highestScore = myMatch.score;
		}		
		System.out.println(myMatch5.score);
		System.out.println("Checked Number 5...");
		
		biNumber = ImageIO.read(new File("d://Eigene Dateien/Daten/workspace/images/numbers/6.png"));
		rotated = biNumber;
		Match myMatch6 = new Match(0,0,0.0,"SIX");
		myMatch6 = compareWithBigImage(propose, "6" , myMatch6);
		if(highestScore<myMatch6.score) {
			myMatch = myMatch6;
			highestScore = myMatch.score;
		}
		System.out.println(myMatch6.score);
		System.out.println("Checked Number 6...");
		
		biNumber = ImageIO.read(new File("d://Eigene Dateien/Daten/workspace/images/numbers/7.png"));
		rotated = biNumber;
		Match myMatch7 = new Match(0,0,0.0,"SEVEN");
		myMatch7 = compareWithBigImage(propose, "7" , myMatch7);
		if(highestScore<myMatch7.score) {
			myMatch = myMatch7;
			highestScore = myMatch.score;
		}
		System.out.println(myMatch7.score);
		System.out.println("Checked Number 7...");
		
		
		biNumber = ImageIO.read(new File("d://Eigene Dateien/Daten/workspace/images/numbers/8.png"));
		rotated = biNumber;
		Match myMatch8 = new Match(0,0,0.0,"EIGTH");
		myMatch8 = compareWithBigImage(propose, "8" , myMatch8);
		if(highestScore<myMatch8.score) {
			myMatch = myMatch8;
			highestScore = myMatch.score;
		}
		System.out.println(myMatch8.score);
		System.out.println("Checked Number 8...");
		
		
		biNumber = ImageIO.read(new File("d://Eigene Dateien/Daten/workspace/images/numbers/9.png"));
		rotated = biNumber;
		Match myMatch9 = new Match(0,0,0.0,"NINE");
		myMatch9 = compareWithBigImage(propose, "9" , myMatch9);
		if(highestScore<myMatch9.score) {
			myMatch = myMatch9;
			highestScore = myMatch.score;
		}
		System.out.println(myMatch9.score);
		System.out.println("Checked Number 9...");
		
		System.out.println("CHECK: " + myMatch.score + " " + myMatch.digit );
		biNumber = null;
		rotated = null;
			
		return myMatch;
	}
	
	
	public Match compareWithBigImage(BufferedImage biBigImg, String aa, Match currentMatch) throws IOException {		
		int tempx1 = 0;
		int tempy1 = 0;	
		int tempx2 = 0;
		int tempy2 = 0;	
		int xx = 0;
		int yy =0 ;
		int tX = 0;
		int tY = 0;
		BufferedImage biCrop = null;
		
		double currentScore = 0.0;

		int nHeight = rotated.getHeight();
		int nWidth = rotated.getWidth();
		double bestCurrentScore = 0.0;

		for (int j=0; j < ((bigHeight-nHeight)/step); j++) {
			for(int i=0; i < ((bigWidth-nWidth)/step); i++) {
				//CUT
				biCrop = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_BYTE_BINARY);		
				//biNumber VS  biCrop 
				Graphics2D gCrop = biCrop.createGraphics();	
				gCrop.translate(tX, tY);		
				gCrop.drawImage(biBigImg, 0, 0, null);
				gCrop.dispose();
				
				currentScore = calcScore(nWidth, nHeight, rotated ,biCrop);
				if(bestCurrentScore<currentScore) {
					bestCurrentScore=currentScore;
				}
				if(currentMatch.score < currentScore) {
					currentMatch.setScore(currentScore);
					
					//CALC COORDINATES
					tempx1 = myLX+(tX*-1);
					tempy1 = myLY+(tY*-1);
					
					tempx2 = myLX+((tX*-1)+nWidth);
					tempy2 = myLY+((tY*-1)+nHeight);
					
					xx = (tempx1 + ((tempx2 - tempx1)/2));
					yy = (tempy1 + ((tempy2 - tempy1)/2));
					
					currentMatch.setX(xx);
					currentMatch.setY(yy);
				}
				tX = tX - step;				
		}
			tX = 0;
			tY = tY - step;	
		}
		
		if (maxRotationTries > rotationTries) {
			rotationTries++;
			RotateImage ri = new RotateImage();			
			currentAngle = currentAngle+angle;
			//System.out.println("Rotating " + aa +": " + currentAngle+"°...");
			rotated = ri.flipImage(biNumber, currentAngle);			
			compareWithBigImage(biBigImg, aa, currentMatch);			
			return currentMatch;			
		}
		else {
			BufferedImage colorr = new BufferedImage(biBigImg.getWidth(), biBigImg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D gColor = colorr.createGraphics();
			gColor.setColor(Color.RED);			
			gColor.drawImage(biBigImg, 0, 0, null);
			gColor.drawString(aa, currentMatch.getX(),currentMatch.getY());
//			File fr = new File("d://Eigene Dateien/Daten/workspace/images/numbers/vorschlag_" + aa +  ".png");	
//			ImageIO.write(colorr, "png", fr);				
			//System.out.println("Score: " + currentMatch.score);
			rotationTries = 1;
			currentAngle = -40;
			return currentMatch;				
		}	
	}
	
	int z= 0;
	public Double calcScore(int width, int heigth, BufferedImage one, BufferedImage two) throws IOException {
//		File ftwo = new File("d://Eigene Dateien/Daten/workspace/images/numbers/a/2"+z+".png");	
//		File fone = new File("d://Eigene Dateien/Daten/workspace/images/numbers/1.png");	
//		ImageIO.write(one, "png", fone);
//		ImageIO.write(two, "png", ftwo);
		z++;
		
		//SET SAME BINARY TYPE
		BufferedImage biOne = new BufferedImage(width, heigth, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D gOne = (Graphics2D) biOne.getGraphics();
		gOne.drawImage(one, 0, 0, null);
		gOne.dispose();
		
		BufferedImage biTwo = new BufferedImage(width, heigth, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D gTwo = (Graphics2D) biTwo.getGraphics();
		gTwo.drawImage(two, 0, 0, null);
		gTwo.dispose();


		double score = 0.0;
		int matches = 0;
		int fails = 0;
		int max = heigth*width;	
		
		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {				
				if ((biOne.getRGB(x, y)) == (biTwo.getRGB(x, y)) ) {
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
		return score;		
	}
	
}
