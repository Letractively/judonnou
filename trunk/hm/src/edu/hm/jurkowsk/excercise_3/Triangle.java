package edu.hm.jurkowsk.excercise_3;
/**
 * @author Benjamin Jurkowski (jurkowsk@hm.edu)
 */
public class Triangle {
	public static void main(String[] args) {
		
		int width = 35; //input zahl (breite)
		if(width%2>0){//nur ungerade zahlen >0
			int height = (width/2)+1;
			int oddCounter = 1; //zähler ungerade zahlen
			String out = ""; //output string
			for(int i=0; i<height; i++){ //für jede zeile
				int spaceLeftIndex = (width-oddCounter)/2; //index füllung links
				int pyramid = spaceLeftIndex+oddCounter; //index für pyramide ("*")
				int spaceRightIndex = spaceLeftIndex*2+pyramid; //index füllung rechts
				oddCounter =  oddCounter+2; //hochzählen ungerade zahl
				out = ""; //ouput string für aktuelle zeile
				for(int j=0; j<width;j++){ //für jede index position in aktueller zeile
					if(j<spaceLeftIndex){	//setze "." bis zum index links
						out += ".";
					}else if(j<pyramid){ //setze "*" bis zum index pyramide
						out += "*";
					}else if(j<spaceRightIndex){ //setze "." bis zum index recht
						out += ".";
					}
				}
				System.out.println(out); //ausgabe aktueller zeile
				//nächste zeile
			}
		}else{
			System.out.println("Bitte einen ungeraden Wert größer als 0 eingeben!");
		}
	}
}