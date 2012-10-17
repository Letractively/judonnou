package edu.hm.jurkowsk.excercise_2;

import java.util.ArrayList;

public class Fibonacci {
/**
 * @author Benjamin Jurkowski (jurkowsk@hm.edu)
 * n:			00,01,02,03,04,05,06,07,08,09,..
 * fibnoacci:	00,01,01,02,03,05,08,13,21,34,...
 */
	public static void main(String[] args) {
		int n = 9; //0 <= n <= 46
		ArrayList<Integer> f = new ArrayList<Integer>();
		f.add(0); //f0
		f.add(1); //f1
		
		if(n>=0 && n<46){
			
			for(int i=f.size(); i<=n;i++){ //start from f2
				int temp = f.get(i-2)+f.get(i-1); //fn = fn-2 + fn-1 für n >= 2
				f.add(temp); //add next fibonacci
			}
			
			System.out.println(f.get(n));
			
		}else{
			
			System.out.println("wrong input n: " + n);
		}
	}
}
