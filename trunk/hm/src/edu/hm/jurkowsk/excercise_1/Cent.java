package edu.hm.jurkowsk.excercise_1;

public class Cent {
	
	public static void main(String[] args) {
		
		/* Input */
		int cents = 49;
		
		 /* Available coins */
		int[] coins = {50,20,10,5,2,1};

		/* for each coin possibility (50,20,10,5,2,1)*/
		for(int i=0; i<coins.length; i++){
			int coin = coins[i]; //get next coin
			int coinCount = cents/coin; //check how many coins fit
			cents = cents - coinCount*coin; //subtract the value of the coins which fit
			System.out.println(coin + "-Cent: " + coinCount);
		}
	}
}
