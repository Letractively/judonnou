package eu.benjam.bol;

import ij.ImagePlus;

	public class jImage {
	
	public static void main(String[] args) {
		jImage ji = new jImage();
		ji.checkPixels();
	}
	public void checkPixels(){
		/*Check Colors of all Pixels */
		String imgPath = "D:/objects/game2.png";
		ImagePlus image = new ImagePlus(imgPath);
		
		for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
            	int [] colorArray = image.getPixel(x,y);
            	int redValue = colorArray[0];
            	int greenValue = colorArray[1];
            	int blueValue = colorArray[2];

            }
        }

	}
}
