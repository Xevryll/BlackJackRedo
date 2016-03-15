import java.util.*;

public class Card {
	
	private final String faceOfCard;
	private final String cardNumber;
	private final String legalName;
	private int aceValue = 0;
	
	public Card(final String num, final String face) {
		this.faceOfCard = face; //Sets face
		this.cardNumber = num; //Sets value
		this.legalName = num + " of " + face; //Sets real-world name. IE: "2 of Hearts"
	}
	
	public String getNumber() {
		return this.cardNumber; //Sends cardNumber
	}
	
	public String getFace() {
		return this.faceOfCard; //Sends faceOfCard
	}
	
	public String getLegalName() {
		return this.legalName; //Sends legalName
	}
	
	public int getAceValue() {
		return this.aceValue; //Sends aceValue
	}
	
	protected void setAceValue(int i) {
		aceValue = i;
	}
}
