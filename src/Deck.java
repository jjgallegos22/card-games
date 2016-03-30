import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/////////////////////////////////////////////////////////////
public class Deck<C extends Card>
{
//-----------------------------------------------------------
	private final int DECK_SIZE = 52;
	private List<C> deck; // Deck
	private int top;      // Top of deck
	private Class<C> type;
//-----------------------------------------------------------
	public Deck( Class<C> type ) throws InstantiationException, IllegalAccessException
	{
		this.type = type;
		initDeck();
	}
//-----------------------------------------------------------
	private void initDeck() throws InstantiationException, IllegalAccessException
	{
		this.deck = new ArrayList<C>();
		top = DECK_SIZE - 1;
		int i, j;
		int suitSize = DECK_SIZE / 4;
		for ( i = 0, j = 1; i < suitSize; i++, j++ )
		{
			C card = type.newInstance();
			((Card)card).setCard( j, Card.Suit.Heart );
			deck.add( card );
		}
		for ( i = suitSize, j = 1; i < suitSize*2; i++, j++ )
		{
			C card = type.newInstance();
			((Card)card).setCard( j, Card.Suit.Spade );
			deck.add( card );
		}
		for ( i = suitSize*2, j = 1; i < suitSize*3; i++, j++ )
		{
			C card = type.newInstance();
			((Card)card).setCard( j, Card.Suit.Diamond );
			deck.add( card );
		}
		for ( i = suitSize*3, j = 1; i < suitSize*4; i++, j++ )
		{
			C card = type.newInstance();
			((Card)card).setCard( j, Card.Suit.Club );
			deck.add( card );
		}
	}
//-----------------------------------------------------------
	public void reshuffle() throws InstantiationException, IllegalAccessException
	{
		initDeck();
		shuffle();
	}
//-----------------------------------------------------------
	public void shuffle()
	{
		// Knuth Shuffle
		Random rnd = new Random();
		for ( int i = DECK_SIZE-1; i > 0; i-- )
		{
			int j = rnd.nextInt( i + 1 );
			if ( i != j )
			{
				C temp = deck.get( i );
				deck.set( i, deck.get( j ) );
				deck.set( j,  temp);
			}
		}
	}
//-----------------------------------------------------------
	public List<C> dealHand( int num )
	{
		assert ( num > top ); // Should be enough cards to deal
		List<C> hand = new ArrayList<C>();
		for ( int i = 0; i < num; i++ ) hand.add( dealCard() );
		return hand;
	}
//-----------------------------------------------------------
	public C dealCard() { return deck.remove( top-- ); }
//-----------------------------------------------------------
	public int remainingCards() { return DECK_SIZE - top; }
//-----------------------------------------------------------
	public String toString()
	{	
		String str = "";
		for ( int i = 0; i < DECK_SIZE; i++ )
		{
			if ( i != 0 && ( i % 13 == 0 ) ) str += "\n";
			str += deck.get( i ) + ", ";
		}
		return str;
	}
//-----------------------------------------------------------
	public void test()
	{
		System.out.println( this );
		System.out.println("\nShuffling deck");
		shuffle();
		System.out.println( this );
		System.out.println("\nDrawing cards: ");
		while ( top >= 0 ) System.out.println( dealCard() );
	}
//-----------------------------------------------------------
}
/////////////////////////////////////////////////////////////