package edu.hm.jurkowsk.excercise_2;

public class Quersumme {

	public static void main(String[] args) {
		int input = 4567;
		System.out.println("Input: " + input);
		System.out.println("digit sum: " + getDigitSum(input));
	}
	
	static int digitSum = 0;
	public static int getDigitSum(int input) {
		if(input>0 && input <10000){ //0 < input < 10.000
			digitSum += input%10; //last digit of input
			System.out.println(input%10);
			input = input/10; //cut the last digit by dividing
			getDigitSum(input); //recursion till input <= 0
		}
		return digitSum;
	}
}
