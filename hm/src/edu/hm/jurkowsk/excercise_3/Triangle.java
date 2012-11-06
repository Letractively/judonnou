package edu.hm.jurkowsk.excercise_3;
/**
 * @author Benjamin Jurkowski (jurkowsk@hm.edu)
 */
public class Triangle {
	public static void main(String[] args) {
		
		int width = 35; //input zahl (breite)
		if(width%2>0){//nur ungerade zahlen >0
			int height = (width/2)+1;
			int oddCounter = 1; //z�hler ungerade zahlen
			String out = ""; //output string
			for(int i=0; i<height; i++){ //f�r jede zeile
				int spaceLeftIndex = (width-oddCounter)/2; //index f�llung links
				int pyramid = spaceLeftIndex+oddCounter; //index f�r pyramide ("*")
				int spaceRightIndex = spaceLeftIndex*2+pyramid; //index f�llung rechts
				oddCounter =  oddCounter+2; //hochz�hlen ungerade zahl
				out = ""; //ouput string f�r aktuelle zeile
				for(int j=0; j<width;j++){ //f�r jede index position in aktueller zeile
					if(j<spaceLeftIndex){	//setze "." bis zum index links
						out += ".";
					}else if(j<pyramid){ //setze "*" bis zum index pyramide
						out += "*";
					}else if(j<spaceRightIndex){ //setze "." bis zum index recht
						out += ".";
					}
				}
				System.out.println(out); //ausgabe aktueller zeile
				//n�chste zeile
			}
		}else{
			System.out.println("Bitte einen ungeraden Wert gr��er als 0 eingeben!");
		}
	}
}