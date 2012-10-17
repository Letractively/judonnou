package edu.hm.jurkowsk.excercise_1;

public class MinMax {

	public static void main(String[] args) {
		int[] values = {50,20,10,5,2,150,56,99}; //any values
		
		int max = 0;
		int min = 9999;
		
		for(int i=0; i<values.length; i++){ //iterate through each value
			int value = values[i];
			if(value>max){ //if current value is greater than max 
				max = value;
			}
			if(value<min){ //if current value is lesser than min 
				min = value;
			}
		}
		System.out.println("max: " + max);
		System.out.println("min: " + min);
	}
}
