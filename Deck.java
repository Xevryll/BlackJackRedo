import java.util.*;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>(); //Array of non-used cards
	private ArrayList<Card> disposed = new ArrayList<Card>(); //Array of used cards

	private final String[] face = { "Hearts", "Spades", "Clubs", "Diamonds" }; //All possible suits
	private final String[] numbers = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" }; //All possible faces
	
	public Deck() {} //Java loves to complain about this...
	
	public Deck(int a) {
		for(int i=1; i<=a; i++) {
			for(String faces : face) { //Goes through all faces
				for(String nums : numbers) { //Goes through all numbers
					cards.add(new Card(nums, faces)); //Creates new card based off of face and number
				}
			}
		}
	}
	
	private boolean containsCard(Card c) { //Check if deck contains card X
		for(Card ce : cards) {
			if(ce.getFace() == c.getFace() && ce.getNumber() == c.getNumber()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean removeCards(String face, String number) { //Remove card from deck
		String temp = number + " of " + face;
		for(Card c : cards) {
			if(c.getLegalName().equals(temp)) {
				cards.remove(c);
				return true;
			}
		}
		return false;
	}
	
	protected ArrayList<Card> getTheCards(){ //Get all cards
		return cards;
	}
	
	protected void shuffleCards(){ //iCard Shuffle
		Collections.shuffle(cards);
	}
	
	protected Card getRandomCard() { //Get a random card
		if(cards.size()<1) {
			System.out.println("Dealer: We are out of cards!");
			System.exit(0); //Ends program
		}
		Card c = cards.get(0); //Create a temp copy of the card
		disposeCard(c); //Adds card to disposed deck
		cards.remove(0);  //Removes card from deck
		return c;
	}
	
	protected void disposeCard(Card c) { //Dispose of cards
		disposed.add(c);
	}
	
	protected int sizeOfDeck() { //Grab size of deck
		return cards.size();
	}
	
	protected boolean flushDeck() { //Empty the deck
		cards.clear();
		return true;
	}
}
