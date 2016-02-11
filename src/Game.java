
public class Game 
{
	public static void main( String[] args )
	{
		Deck deck = new Deck();
		deck.print();
		System.out.println("\nShuffling deck");
		deck.shuffle();
		deck.print();
	}
}
