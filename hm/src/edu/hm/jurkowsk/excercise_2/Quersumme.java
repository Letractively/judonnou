package edu.hm.jurkowsk.excercise_2;

import java.util.ArrayList;

/**
 * @author Benjamin Jurkowski (jurkowsk@hm.edu)
 * 
 */
public class Quersumme {

	public static void main(String[] args) {
		Quersumme qs = new Quersumme();
		qs.getDigitSum(4567);
	}
	
	int digitSum = 0;
	int recursionCount = 0;
	
	public void getDigitSum(int input) {
		/*Output text*/
		if(recursionCount==0){
			System.out.println("Eingabe: Positive ganze Zahl " + input);
			System.out.print("Ausgabe: Die Quersumme ergibt sich aus: ");
		}
		if(input>0 && input <10000){ //0 < input < 10.000
			digitSum += input%10; //last digit of input
			System.out.print(input%10 + " ");
			recursionCount++;
			input = input/10; //dividing input by 10 to cut the last digit
			getDigitSum(input); //recursion till input <= 0
		}else{
			/*recursion ended*/
			System.out.print("= " + digitSum);
		}
	}
}
