import java.util.*;

public class Player {
	protected int totalWins = 0;
	private final boolean dealer;
	protected double money = 50.00;
	
	public Player(boolean t) {
		dealer = t;
	}
	
	private ArrayList<Card> playerCards = new ArrayList<Card>();
	
	protected void givePlayerCard(Card c) {
		playerCards.add(c);
	}
	
	protected void listMyCards() { //Tells the user their cards
		System.out.println("Your Current Cards Are: ");
		int playersCardNumber = 1;
		for(Card c : playerCards) {
			System.out.println("Card #" + playersCardNumber + " " + c.getLegalName());
			playersCardNumber++;
		}
	}
	
	protected ArrayList<Card> flushPCards() { //Gets rid of their cards and returns them to be used in the dispose stack
		ArrayList<Card> temp = playerCards;
		playerCards.clear();
		return temp; 
	}
	
	public boolean isDealer() {
		return this.dealer; //See if player is dealer
	}
	
	public int getWins() {
		return this.totalWins; //Get wins
	}
	
	protected int getValOfCards() { //Get total point value of all cards
		int point = 0;
		for(Card c : playerCards) {
			String numberOfCard = c.getNumber();
			int amountOfCard = 0;
			try {
				amountOfCard = Integer.valueOf(numberOfCard);
			} catch (NumberFormatException e){ //In case it is not a number, it will run the below
				if(c.getNumber().equals("Jack") || c.getNumber().equals("Queen") || c.getNumber().equals("King")) {
					amountOfCard = 10;
				} else if (c.getNumber().equals("Ace")) { //If card is Ace
					boolean stillChoosing = true; 
					int aceValOfCard = c.getAceValue();
					if(aceValOfCard==0){ //Checks if AceValue of the card is set
						while (stillChoosing){ //While user is still choosing the AceValue
							if(!(isDealer())) { //If the user IS NOT a dealer
								System.out.print("Would you like your ace to be a 1 or 11?");
								Scanner sc = new Scanner(System.in);
								int newValue = 0; //Tracks their new AceValue for the card
								try {
									newValue = sc.nextInt();
								} catch (Exception ex) {continue;} //Reitterates the loop
								if(newValue == 1) { //If choice = 1, set AceValue to 1
									amountOfCard = 1;
									c.setAceValue(1);
									stillChoosing=false;
								} else if (newValue == 11) { //If choice = 11, set AceValue to 11
									amountOfCard = 11;
									c.setAceValue(11);
									stillChoosing=false;
								}
								continue; //Reitterates the loop
							} else { //If the user IS a dealer
								if(point<10) {
									amountOfCard = 11;
									c.setAceValue(11);
									stillChoosing = false;
								} else{
									amountOfCard = 1;
									c.setAceValue(1);
									stillChoosing = false;
								}
							}
						}
					} else {
						amountOfCard = c.getAceValue(); //Sets the amountOfCard to the AceValue if it has already been set
					}
				}
			}
			point+=amountOfCard; //Adds amountOfCard to total points
		}
		return point;
	}
}