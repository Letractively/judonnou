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
		int fid;
		do{
			System.out.println("Bitte geben Sie die ID der Fakultät ein:");
			fid = In.readInt();
			switch ( fid )	{
				case 0: 
					System.out.println("Programm wird terminiert");
					break;
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 8:
					System.out.println("Faktultät " + fid + " - Bereich: Technik");
					break;
				case 7:
				case 9:
				case 10:
				case 14:
					System.out.println("Faktultät " + fid + " - Bereich: Wirtschaft");
					break;
				case 11:
					System.out.println("Faktultät " + fid + " - Bereich: Soziales");
					break;
				case 12:
					System.out.println("Faktultät " + fid + " - Bereich: Design");
					break;
				case 13:
					System.out.println("Faktultät " + fid + " - Bereich: Generale");
					break;
				default:
					System.out.println(fid + " ist eine ungültige ID.");
					break;
					
			}
		}while(fid>0);
	}
}
