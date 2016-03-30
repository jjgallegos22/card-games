import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
/////////////////////////////////////////////////////////////
public class Player<C extends Card>
{
	protected List<C> hand;
	protected int bet;
	protected int score;
//-----------------------------------------------------------
	public Player() { resetPlayer(); }
//-----------------------------------------------------------
	public void resetPlayer()
	{
		hand = new ArrayList<C>();
		bet = 0;
		score = 0;
	}
//-----------------------------------------------------------
	@SuppressWarnings("unchecked")
	public void addToHand( C... cards )
	{
		for ( C c : cards ) hand.add( c );
		calculateScore();
	}
//-----------------------------------------------------------
	public void placeBet( int bet ) { this.bet = bet; }
//-----------------------------------------------------------
	private void calculateScore()
	{
		score = 0;
		for ( int i = 0; i < hand.size(); i++ ) score += hand.get( i ).value();
	}
//-----------------------------------------------------------
	public int score() { return score; }
//-----------------------------------------------------------
	public int bet() { return bet; }
//-----------------------------------------------------------
}
/////////////////////////////////////////////////////////////
class BlackJackPlayer extends Player<BlackJackCard>
{
//-----------------------------------------------------------
	public BlackJackPlayer() { super(); }
//-----------------------------------------------------------
	public void addToHand( BlackJackCard... cards )
	{
		for ( BlackJackCard c : cards ) hand.add( c );
		calculateScore();
	}
//-----------------------------------------------------------
	private void calculateScore()
	{
		score = 0;
		boolean wildCard = false;
		for ( int i = 0; i < hand.size(); i++ )
		{
			BlackJackCard cur = hand.get( i );
			if ( cur.isAce() ) wildCard = true;
			else score += cur.value();
		}
		
		if ( wildCard )
		{
			int max = score + 11;
			int min = score + 1;
			if ( max == 21 || min == 21 ||
			   ( Math.abs( max - 21 ) < Math.abs( min - 21 ) ) ) score = max;
			else                                                 score = min;
		}
	}
//-----------------------------------------------------------
	public boolean busted() { return score > 21; }
//-----------------------------------------------------------
	public boolean isBlackJack() { return score == 21; }
//-----------------------------------------------------------
}
/////////////////////////////////////////////////////////////