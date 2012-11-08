package edu.hm.jurkowsk.excercise_4;

public class Triangle_Test {
	public static void main(String[] args) {
		
		Triangle tri = new Triangle(51);
		
		tri.display_triangle();
		
		System.out.println("Anzahl der Sterne: " + tri.get_stars());
		System.out.println("Anzahl der Punkte: " + tri.get_dots());
	}
}
