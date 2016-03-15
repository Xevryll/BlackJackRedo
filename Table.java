import java.util.*;

public class Table {
	
	private Deck deck;
	private ArrayList<Player> players = new ArrayList<Player>();
	private boolean game = true;
	
	public Table() {
		deck = new Deck(1); //Default Constructor: Makes only 1 deck
	}
	
	public Table(int a) {
		deck = new Deck(a); //Modified Constructor: Allows for variable a decks.
	}
	
	protected void startGame() { //Starts game
		int gameNumber = 0;
		while(this.game) { //While game is going on
			if(!(this.deck.sizeOfDeck() >= players.size()*2)) {
				endGame(); //When deck is out of needed cards.
			}
			gameNumber++;
			flushPlayerCards(); //Removes all of players card if any
			deck.shuffleCards();
			giveCards(); //Give each player two cards
			int valueOfPlayersCards = 0;
			boolean gameIsRunning = true;
			while(gameIsRunning) { //While the round is going on
				System.out.println("You are now playing: Game #" + gameNumber + ".");
				for(Player p : players) {
					String nameWhoPlays;
					if(p.isDealer()) {
						nameWhoPlays = "================== Dealer Plays ==================";
					} else {
						nameWhoPlays = "================== Player Plays ==================";
					}
					System.out.println(nameWhoPlays);
					boolean playerChoiceLoop = true;
					while(playerChoiceLoop) {//While player is choosing
						System.out.println();
						p.listMyCards();
						valueOfPlayersCards = p.getValOfCards();
						if(!p.isDealer()) { //If player is not dealer
							if(valueOfPlayersCards>21) {
								System.out.println("PLAYER BUST!");
								evaluateGame();
								playerChoiceLoop=false;
								gameIsRunning=false;
							} else if (valueOfPlayersCards==21) {
								evaluateGame();
								playerChoiceLoop=false;
								gameIsRunning=false;
							} else if (valueOfPlayersCards<21) {
								System.out.println("Your amount: " + valueOfPlayersCards);
								System.out.print("Hit or stand?");
								Scanner sc = new Scanner(System.in);
								String g = sc.next();
								if(g.equalsIgnoreCase("Hit")) {
									p.givePlayerCard(deck.getRandomCard());
								} else if (g.equalsIgnoreCase("Stand")) {
									playerChoiceLoop=false;
									gameIsRunning=false; //Stops loop to go onto the next player
								}
							}
						} else { //If player is a dealer
							if(valueOfPlayersCards>21) {
								System.out.println("DEALER BUST!");
								evaluateGame();
								playerChoiceLoop=false;
								gameIsRunning=false;
							} else if (valueOfPlayersCards==21) {
								System.out.println("Dealer amount: " + valueOfPlayersCards);
								System.out.print("Hit or stand?stand");
								evaluateGame();
								playerChoiceLoop=false;
								gameIsRunning=false;
							} else if (valueOfPlayersCards<17) {
								System.out.println("Dealer amount: " + valueOfPlayersCards);
								System.out.print("Hit or stand?hit");
								p.givePlayerCard(deck.getRandomCard());
							} else {
								System.out.println("Dealer amount: " + valueOfPlayersCards);
								System.out.print("Hit or stand?stand");
								evaluateGame();
								playerChoiceLoop=false;
								gameIsRunning=false;
							}
						}
					}
				}
			}
		}
		endGame();
	}
	
	protected void addPlayers(Player p) { //Registers a player to the table
		if(players.size() < 2) {
			this.players.add(p);
		} else {
			System.out.println("Sorry, this is a small table. Only two players are allowed to play.");
		}
	}
	
	protected void flushTheDeck() {//Emptys the deck
		this.deck.flushDeck();
	}
	
	protected void giveCards() {
		for(Player p : players) {
			p.givePlayerCard(deck.getRandomCard());
			p.givePlayerCard(deck.getRandomCard());
		}
	}
	
	protected void flushPlayerCards(){ //Flushes all player cards and disposes it to the deck
		for(Player p : players) {
			ArrayList<Card> temp = p.flushPCards();
			for(Card c : temp) {
				this.deck.disposeCard(c);
			}
		}
	}
	
	private void evaluateGame() {//Goes through all of game, says who had the most points and what the amount was
		System.out.println();
		int winnerPoints = 0;
		Player pe = new Player(false);
		for(Player p : players) {
			if(p.getValOfCards()>winnerPoints) {
				if(p.getValOfCards()<=21) {
					winnerPoints = p.getValOfCards();
					pe = p;
				}
			}
		}
		String winnerPlayer;
		if(pe.isDealer()) {
			winnerPlayer = "Dealer";
		} else {
			winnerPlayer = "Player";
		}
		for(Player p : players) {
			if(pe==p) {
				p.totalWins++;
			}
		}
		System.out.println("Winner: " + winnerPlayer);
		System.out.println("End Amount: " + winnerPoints);
		System.out.println();
	}
	
	private void endGame() { //Ends game, tells the winners and total wins
		for(Player p : players) {
			if(p.isDealer()) {
				System.out.println("Dealer Wins: " + p.totalWins);
				System.out.println("Dealer Last Deal Amount: " + p.getValOfCards());
			} else {
				System.out.println("Player Wins: " + p.totalWins);
				System.out.println("Player Last Deal Amount: " + p.getValOfCards());
			}
		}
		flushTheDeck();
		players.clear();
		this.game = false;
		System.exit(0);
	}
} 