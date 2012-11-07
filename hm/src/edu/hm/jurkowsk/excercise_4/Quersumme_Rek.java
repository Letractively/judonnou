package edu.hm.jurkowsk.excercise_4;

/**
 * @author Benjamin Jurkowski (jurkowsk@hm.edu)
 * @date 07.11.2012
 */

public class Quersumme_Rek {

	public static void main(String[] args) {
		int input = 4567;
		System.out.println("Eingabe: Positive ganze Zahl " + input);
		System.out.println("= " + calculate(4567));
	}
	
	static int digitSum = 0;
	static int recursionCount = 0;
	
	public static int calculate(int n) {
		/*Output text*/
		if(recursionCount==0){
			System.out.print("Ausgabe: Die Quersumme ergibt sich aus: ");
		}
		if(n>0 && n <1000000000){ //0 < n < 1.000.000.000
			digitSum += n%10; //last digit of n
			System.out.print(n%10 + " ");
			recursionCount++;
			n = n/10; //dividing n by 10 to cut the last digit
			calculate(n); //recursion till n <= 0
		}
		return digitSum;
	}
}
