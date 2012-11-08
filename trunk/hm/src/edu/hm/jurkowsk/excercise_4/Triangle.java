package edu.hm.jurkowsk.excercise_4;
/**
 * @author Benjamin Jurkowski (jurkowsk@hm.edu)
 */
public class Triangle {	
	private int length;
	private int lines;
	private int stars;
	private int dots;
	
	public Triangle(int length){
		if(length%2>0){
			/* Length */
			this.length = length;
			
			/* Lines */
			this.lines = (this.length/2)+1;
			
			/* Stars */
			int stars_temp = 1;
			for(int i = 0; i<lines; i++){
				this.stars += stars_temp;
				stars_temp += 2;
			}
			
			/* Dots */
			this.dots = (this.length*this.lines)-this.stars;
		}else{
			System.out.println("Bitte einen ungeraden Wert gr��er als 0 eingeben!");
		}
	}
	
	public void display_triangle(){
		int temp = 1; //z�hler ungerade zahlen
		String out = ""; //output string
		for(int i=0; i<lines; i++){ //f�r jede zeile
			int dotsLeftIndex = (length-temp)/2; //index f�llung links
			int starsIndex = dotsLeftIndex+temp; //index f�r pyramide ("*")
			int dotsRightIndex = dotsLeftIndex*2+starsIndex; //index f�llung rechts
			temp =  temp+2; //hochz�hlen ungerade zahl
			out = "";
			for(int j=0; j<length;j++){ //f�r jede index position in aktueller zeile
				if(j<dotsLeftIndex){	//setze "." bis zum index links
					out += ".";
				}else if(j<starsIndex){ //setze "*" bis zum index pyramide
					out += "*";
				}else if(j<dotsRightIndex){ //setze "." bis zum index recht
					out += ".";
				}
			}
			System.out.println(out);
			//n�chste zeile
		}
	};
	
	/* Getters */
	public int get_length(){
		return this.length;
	}
	public int get_lines(){
		return this.lines;
	}
	public int get_stars(){
		return this.stars;
	}
	public int get_dots(){
		return this.dots;	
	}
	
}