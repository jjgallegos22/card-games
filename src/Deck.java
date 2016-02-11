import java.util.Random;

public class Deck
{
	private Card[] cards;
	//private int index;
	private int deckSize;
	
	public Deck()
	{
		final int SIZE = 52;
		this.cards = new Card[SIZE];
		this.deckSize = SIZE;
		initDeck();
		//index = SIZE - 1;
	}
	
	private void initDeck()
	{
		int i, j;
		int suitSize = deckSize / 4;
		for ( i = 0, j = 1; i < suitSize; i++, j++ )
			cards[i] = new Card( j, Card.Suit.Heart );
		for ( i = suitSize, j = 1; i < suitSize*2; i++, j++ )
			cards[i] = new Card( j, Card.Suit.Spade );
		for ( i = suitSize*2, j = 1; i < suitSize*3; i++, j++ )
			cards[i] = new Card( j, Card.Suit.Diamond );
		for ( i = suitSize*3, j = 1; i < suitSize*4; i++, j++ )
			cards[i] = new Card( j, Card.Suit.Club );
	}
	
	public void shuffle()
	{
		// Use shuffle sort
		Random rnd = new Random();
		int[] unsorted = new int[deckSize];
		for( int i = 0; i < unsorted.length; i++ )
			unsorted[i] = rnd.nextInt( 1000 );
		
		for ( int i = 0; i < unsorted.length-1; i++ )
		{
			int min = i;
			for ( int j = i+1; j < unsorted.length; j++ )
			{
				if ( unsorted[j] < unsorted[min] ) min = j;
			}
			swap( unsorted, i, min );
			swap( i, min );
		}
	}
	
	public void swap( int[] array, int i, int j )
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public void swap( int i , int j )
	{
		Card temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}
	 
	public void print()
	{	
		String str = "";
		for ( int i = 0; i < deckSize; i++ )
		{
			if ( i != 0 && ( i % 13 == 0 ) )
			{
				System.out.println( str );
				str = "";
			}
			Card cur = cards[i];
			str += cur.value() + "," + cur.suitString() + " ";
		}
		System.out.println( str );
	}
}
