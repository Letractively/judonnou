package edu.hm.jurkowsk.excercise_4;

import edu.hm.jurkowsk.excercise_2.In;

/**
 * @author Benjamin Jurkowski (jurkowsk@hm.edu)
 * @date 07.11.2012
 * 
 * Faktultäten:
 * 01 Architektur
 * 02 Bauingenieurwesen
 * 03 Maschinenbau, Fahrzeugtechnik, Flugzeugtechnik
 * 04 Elektrotechnik und Informationstechnik
 * 05 Versorgungs- und Gebäudetechnik, Verfahrenstechnik Papier und Verpackung, Druck- und Medientechnik
 * 06 Feinwerk- und Mikrotechnik, Physikalische Technik
 * 07 Informatik und Mathematik
 * 08 Geoinformation
 * 09 Wirtschaftsingenieurwesen
 * 10 Betriebswirtschaft
 * 11 Angewandte Sozialwissenschaften
 * 12 Design
 * 13 Studium Generale und Interdisziplinäre Studien
 * 14 Tourismus
 * 
 */
public class Fakultaet_ID {
	public static void main(String[] args) {
		int fid = In.readInt();
		
		switch ( fid )	{
			case 0:
				break;
			case 1:
				System.out.println("Architektur");
				break;
			case 2:
				System.out.println("Bauingenieurwesen");
				break;
			case 3:
				System.out.println("Maschinenbau, Fahrzeugtechnik, Flugzeugtechnik");
				break;
			case 4:
				System.out.println("Elektrotechnik und Informationstechnik");
				break;
			case 5:
				System.out.println("Versorgungs- und Gebäudetechnik, Verfahrenstechnik Papier und Verpackung, Druck- und Medientechnik");
				break;
			case 6:
				System.out.println("Feinwerk- und Mikrotechnik, Physikalische Technik");
				break;
			case 7:
				System.out.println("Informatik und Mathematik");
				break;
			case 8:
				System.out.println("Geoinformation");
				break;
			case 9:
				System.out.println("Wirtschaftsingenieurwesen");
				break;
			case 10:
				System.out.println("Betriebswirtschaft");
				break;
			case 11:
				System.out.println("Angewandte Sozialwissenschaften");
				break;
			case 12:
				System.out.println("Design");
				break;
			case 13:
				System.out.println("Studium Generale und Interdisziplinäre Studien");
				break;
			case 14:
				System.out.println("Tourismus");
				break;
			default:
				System.out.println("Ungültige ID.");
				
		}
	}
}
