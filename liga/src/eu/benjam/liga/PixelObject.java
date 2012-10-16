package eu.benjam.liga;

import java.util.ArrayList;

public class PixelObject {
	ArrayList<Point> points = new ArrayList<Point>();
	
	public void add(Point point) {
		points.add(point);
	}
	public ArrayList<Point> getPoints() {
		return points;
	}
	public int width() {
		int min_x = 9999;
		int max_x = 0;
		for(int i=0; i<points.size(); i++){
			Point temp = points.get(i);
			if(temp.getX()<min_x){
				min_x = temp.getX();
			}
			if(temp.getX()>max_x){
				max_x = temp.getX();
			}
		}
		return max_x-min_x;
	}
	public int height() {
		int min_y = 9999;
		int max_y = 0;
		for(int i=0; i<points.size(); i++){
			Point temp = points.get(i);
			if(temp.getY()<min_y){
				min_y = temp.getY();
			}
			if(temp.getY()>max_y){
				max_y = temp.getY();
			}
		}
		return max_y-min_y;
	}
	public int size() {
		return points.size();
	}
	public int resolution() {
		return height()*width();
	}
	/* Returns the amount of White Pixels in the Whole Image in percent */
	public Double fillAmount() {
		return new Double(points.size()) / new Double(resolution())*100;
	}
	
	
}
