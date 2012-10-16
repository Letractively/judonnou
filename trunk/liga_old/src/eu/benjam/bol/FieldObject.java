package eu.benjam.bol;

import java.awt.Color;
import java.util.ArrayList;

public class FieldObject {
	ArrayList<Point> points = new ArrayList<Point>();
	int avg_color;
	
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	public Color getAvgColor() {
		int temp_blue = 0;
		int temp_green = 0;
		int temp_red = 0;
		int count = 0;
		for(int i = 0; i<points.size();i++){
			Color current_color = new Color(points.get(i).getColor());
			temp_blue += current_color.getBlue();
			temp_green += current_color.getGreen();
			temp_red += current_color.getRed();
			
			count++;
			//System.out.println("avg_color: current_color " + current_color);
		}
		temp_blue = temp_blue/count;
		temp_green = temp_green/count;
		temp_red = temp_red/count;
		//System.out.println(temp_blue + " - " + temp_green + " - " + temp_red);
		Color avgColor = new Color(temp_red,temp_green,temp_blue,255);
		//System.out.println(temp);
		return avgColor;	
	}
	public void setNewPoint(Point point) {
		this.points.add(point);
	}
	
}
