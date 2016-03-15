import java.util.*;

public class BlackJack {
	public static void main(String args[]) {
		Table tab = new Table(amountOfDecks()); //Creates a table with 4 decks
		/**
		* NOTE: You must create the regular player FIRST
		* Then make the dealer
		**/
		tab.addPlayers(new Player(false)); //Creates a non-dealer player
		tab.addPlayers(new Player(true)); //Creates a dealer player
		tab.startGame(); //Starts game
	}
	
	private static int amountOfDecks() {
		int numberOfDecks;
		while(true) {
			while(true) {
				System.out.println("DECK SETUP: Please type how many decks you would like.");
				Scanner sc = new Scanner(System.in);
				try {
					numberOfDecks = sc.nextInt();
				} catch (Exception e) {
					continue;
				}
				if(numberOfDecks > 100) {
					System.out.println("Too many decks.");
					continue;
				} else {
					break;
				}
			}
			if(numberOfDecks>=1) {
				System.out.println();
				break;
			}
			continue;
		}
		return numberOfDecks;
	}
}
