package edu.hm.jurkowsk.excercise_1;
/**
 * @author Benjamin Jurkowski (jurkowsk@hm.edu)
 * 
 */
public class Square {
	public static void main(String[] args) {
		
		int number = 9; //Input number
		int square = 0; //Output number
		
		/*Counters*/
		int whileCounter = 1;
		int tempCounter = 1;
		
		/*Square algorithm*/
		while(whileCounter<=number){
			if(tempCounter % 2 > 0){ //if tempCounter is an "odd number"
				square += tempCounter; //add "odd number" to square
				whileCounter++; //count up the whileCounter
			}
			tempCounter++;
		}
		System.out.println(square);
	}
}