package edu.hm.jurkowsk;

public class Square {
	public static void main(String[] args) {
		
		int number = 2;
		
		int squareCounter = 1;
		int tempCounter = 1;
		int square = 0;
		
		while(squareCounter<=number){
			if(tempCounter % 2 > 0){
				square += tempCounter;
				squareCounter++;
			}
			tempCounter++;
		}
		System.out.println(square);
	}
}
