package eu.benjam.liga.field;

import java.awt.Color;

public class Measures {
	/** Measures in Meters 2D
	 * ______.  ._____
	 * |			 |
	 * |			 |
	 * |y-achse		 |
	 * |			 |
	 * |			 |
	 * |			 |
	 * | __________  |
	 * | | _______ | |
	 * |_|_|_.  ._||_|
	 * 		x-Achse
	 * **/

	/** PIXEL X **/
	public static double PIXEL_PER_METER;
	public static double FIELD_X_PIXEL;

	/*FARBEN*/
	private static Color COLOR_FIELD = new Color(70, 180, 40); //#46B428 R70 G180 B40 GREEN
	private static Color COLOR_LINE = new Color(255, 255, 255); //WHITE
	
	/*FELD*/
	private static double FIELD_X = 68;
	private static double FIELD_Y= 105;
	
	/*AUSSENFELD*/
	private static double OUTER_FIELD_LENGTH = 4;
	
	/*TOR*/
	private static double GOAL_X = 7.32;
	private static double GOAL_Y = 1;
	
	/*PUNKTE, KREISE*/
	private static double CENTER_CIRCLE_RADIUS = 9.5;
	private static double CORNER_RADIUS= 1;
	private static double PENALTY_SPOT_Y = 11;
	
	/*RÄUME*/
	private static double PENALTY_AREA_X = 40.32;
	private static double PENALTY_AREA_Y = 16.5;
	
	private static double GOAL_AREA_X= 18.32;	
	private static double GOAL_AREA_Y= 5.5;

	
	public Measures(int fieldXPixel){
		PIXEL_PER_METER = fieldXPixel/FIELD_X ;
		
	}
	
	/* GETTERS */
	public int getFIELD_X() {
		return new Double(PIXEL_PER_METER*FIELD_X).intValue();
	}
	public int getFIELD_Y() {
		return new Double(PIXEL_PER_METER*FIELD_Y).intValue();
	}
	public int getGOAL_X() {
		return new Double(PIXEL_PER_METER*GOAL_X).intValue();
	}
	public int getGOAL_Y() {
		return new Double(PIXEL_PER_METER*GOAL_Y).intValue();
	}
	public int getCENTER_CIRCLE_RADIUS() {
		return new Double(PIXEL_PER_METER*CENTER_CIRCLE_RADIUS).intValue();
	}
	public int getPENALTY_SPOT_Y() {
		return new Double(PIXEL_PER_METER*PENALTY_SPOT_Y).intValue();
	}
	public int getCORNER_RADIUS() {
		return new Double(PIXEL_PER_METER*CORNER_RADIUS).intValue();
	}
	public int getPENALTY_AREA_X() {
		return new Double(PIXEL_PER_METER*PENALTY_AREA_X).intValue();
	}
	public int getPENALTY_AREA_Y() {
		return new Double(PIXEL_PER_METER*PENALTY_AREA_Y).intValue();
	}
	public int getGOAL_AREA_X() {
		return new Double(PIXEL_PER_METER*GOAL_AREA_X).intValue();
	}
	public int getGOAL_AREA_Y() {
		return new Double(PIXEL_PER_METER*GOAL_AREA_Y).intValue();
	}
	public int getOUTER_FIELD_LENGTH() {
		return new Double(PIXEL_PER_METER*OUTER_FIELD_LENGTH).intValue();
	}	
	public static Color getCOLOR_FIELD() {
		return COLOR_FIELD;
	}
	public static Color getCOLOR_LINE() {
		return COLOR_LINE;
	}
	
}
