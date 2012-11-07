package edu.hm.jurkowsk.excercise_3;

class Quersumme_Rek
{
	public static void main (String [] args)
	{
		int zahl = 30857;
		
		if (zahl <= 1000000000)
		{
		
		System.out.println("Eingabe: Positive ganze Zahl: " + zahl);
		System.out.println("Ausgabe: Die Quersumme ergibt sich aus: " + calculate(zahl));
		}
		else
		{
			System.out.println("Sie haben keine Zahl in dem Bereich 0 bis 1.000.000.000 eingegeben, bitte versuchen sie es mit einer Gültigen Zahl");
		}
		
	}
	
	static int quersumme = 0;

	public static int calculate (int n)
	{
		System.out.println(n);
		quersumme = quersumme + n % 10;
		n = n / 10;		
	
		if(n>0){
			calculate(n);
		}
		
		return quersumme;
	}
}