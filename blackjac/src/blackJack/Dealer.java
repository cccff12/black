package blackJack;

public class Dealer {
	
	private String pattern;
	private String number;
	private Card card;
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.setPattern(card.getPattern());
		this.setNumber(card.getNumber());
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	
	
}