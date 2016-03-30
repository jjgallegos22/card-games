/////////////////////////////////////////////////////////////
public class Card 
{
/////////////////////////////////////////////////////////////
	public enum Suit
	{
		Heart(0), Spade(1), Diamond(2), Club(3);
		private int value;
//-----------------------------------------------------------
		private Suit( int v ) { value = v; }
//-----------------------------------------------------------
		public String toString()
		{ 
			if      ( value == 0 ) return "Heart";
			else if ( value == 1 ) return "Spade";
			else if ( value == 2 ) return "Diamond";
			else if ( value == 3 ) return "Club";
			else                   return ""; // assert
		}
//-----------------------------------------------------------
	}
/////////////////////////////////////////////////////////////
	protected Suit suit;
	protected int value;
//-----------------------------------------------------------
	public Card( int value, Suit suit )
	{
		this.value = value;
		this.suit = suit;
	}
//-----------------------------------------------------------
	public Card()
	{
		this.value = 0;
		this.suit = null;
	}
//-----------------------------------------------------------
	public void setCard( int value, Suit suit )
	{ 
		this.value = value;
		this.suit = suit;
	}
//-----------------------------------------------------------
	public int value() { return value; }
//-----------------------------------------------------------
	public Suit suit() { return suit; }
//-----------------------------------------------------------
	public boolean isAce() { return value == 1; }
//-----------------------------------------------------------
	public boolean isFaceCard() { return ( value >= 11 && value <= 13 ); }
//-----------------------------------------------------------
	public String toString()
	{
		String str = "";
		if      ( value == 1  ) str = "Ace";
		else if ( value == 11 ) str = "Jack";
		else if ( value == 12 ) str = "Queen";
		else if ( value == 13 ) str = "King";
		else                    str = value + "";
		str += " of " + suit;
		return str;
	}
//-----------------------------------------------------------
}
/////////////////////////////////////////////////////////////
class BlackJackCard extends Card
{
	public BlackJackCard( int v, Suit s ) { super( v, s ); }
//-----------------------------------------------------------
	public BlackJackCard() { super(); }
//-----------------------------------------------------------
	public int value()
	{
		if ( value >= 11 && value <= 13 ) return 10;
		else return value;
	}
//-----------------------------------------------------------
	public int min() { return value(); }
//-----------------------------------------------------------
	public int max()
	{ 
		if ( value == 1 ) return 11;
		else return value();
	}
//-----------------------------------------------------------
}
/////////////////////////////////////////////////////////////