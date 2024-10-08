package Game;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int input=0;
		do {
			System.out.println("******** CONNECT 4 GAME ********");
			System.out.println();
			System.out.println("\t 1. Player to Player");
			System.out.println("\t 2. Player to Computer");
			System.out.println("\t 3. Close");
			System.out.println();
			input=s.nextInt();
			
			switch(input) {
			case 1: 
				PlayerToPlayer.gameStart();
				break;
			case 2:
				PlayerToComputer.gameStart();
				break;
			case 3:
				System.out.println("********Thank You********");
				break;
			default:
				System.out.println("Enter Valid Number");
			}
		}while(input!=3);
	}

}
