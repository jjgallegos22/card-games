
public class Card 
{
	public enum Suit
	{
		Heart, Spade, Diamond, Club;
	}
	
	private Suit suit;
	private int value;
	
	public Card( int value, Suit suit )
	{
		this.value = value;
		this.suit = suit;
	}
	
	public int value() { return value; }
	
	public Suit suit() { return suit; }
	
	public String suitString()
	{
		String str;
		switch( suit )
		{
		case Heart:
			str = "H";
			break;
		case Spade:
			str = "S";
			break;
		case Diamond:
			str = "D";
			break;
		case Club:
			str = "C";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}
}
