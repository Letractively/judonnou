package edu.hm.jurkowsk.excercise_1;

public class Sum {
	public static void main(String[] args) {
		// "Zutaten" 
		int n;   // A. 
		int s;   // B. 
		int i;   // C.
		
		// Zubereitung
		n = 1; // 1.
		s = 0;  // 2.
		i = 1; // 3.
		while( i<=n ) // 4.
		{ 
		   s = s + i;     // 4.a
		   i = i + 1; // 4.b
		}
		System.out.println(s); // 5
	}
}