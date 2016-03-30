import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/////////////////////////////////////////////////////////////
public class BlackJack
{
	List<BlackJackPlayer> players;
	Deck<BlackJackCard> deck;
//-----------------------------------------------------------
	public BlackJack() throws InstantiationException, IllegalAccessException
	{
		this.players = new ArrayList<BlackJackPlayer>();
		deck = new Deck<BlackJackCard>( BlackJackCard.class );
		deck.shuffle();
	}
//-----------------------------------------------------------
	private void resetGame() throws InstantiationException, IllegalAccessException
	{
		deck.reshuffle();
		for ( int i = 0; i < players.size(); i++ )
			players.get( i ).resetPlayer();
	}
//-----------------------------------------------------------
	public void startNewGame() throws InstantiationException, IllegalAccessException
	{
		resetGame(); // Re-shuffle deck and re-init players
		placeBets(); // Place bets
		dealHand();  // Deal players their initial hand (2 cards)
		playGame();  // 
	}
//-----------------------------------------------------------
	public void dealHand()
	{
		for ( int j = 0; j < 2; j++ )
			for ( int i = 0; i < players.size(); i++ )
				hit( players.get( i ) );
	}
//-----------------------------------------------------------
	public void placeBets()
	{
		// Just place random bets for now
		Random rnd = new Random();
		for ( int i = 0; i < players.size(); i++ )
			players.get( i ).placeBet( rnd.nextInt( 20 ) );
	}
//-----------------------------------------------------------
	public void playGame()
	{
		for ( int i = 0; i < players.size(); i++ )
		{
			BlackJackPlayer cPlayer = players.get( i );
			while ( cPlayer.score() < 17 ) hit( cPlayer );
		}
		
		checkScores(); // Check for scores, blackjacks, etc.
	}
//-----------------------------------------------------------
	public void hit( BlackJackPlayer p ) { p.addToHand( deck.dealCard() ); }
//-----------------------------------------------------------
	public boolean blackJack()
	{
		boolean blackjack = false;
		for ( int i = 0; i < players.size(); i++ )
		{
			if ( players.get( i ).isBlackJack() )
			{
				blackjack = true;
				System.out.println("Player " + i + " has blackjack!");
			}
		}
		return blackjack;
	}
//-----------------------------------------------------------
	public void checkScores()
	{
		if ( !blackJack() )
		{
			int winner = 0;
			int max = 0;
			
			for ( int i = 0; i < players.size(); i++ )
			{
				BlackJackPlayer p = players.get( i );
				if ( p.busted() ) System.out.println("Player " + i + " busted!");
				else
				{
					System.out.println("Player " + i + " scores " + p.score() );
					if ( max < p.score() )
					{
						winner = i;
						max = p.score();
					}
				}
			}
			System.out.println("Player " + winner + " wins with " + max );
		}
	}
//-----------------------------------------------------------
	public void addPlayers( int num )
	{
		for ( int i = 0; i < num; i++ )
			players.add( new BlackJackPlayer() );
	}
//-----------------------------------------------------------
	public void removePlayer() { players.remove( players.size()-1 ); }
//-----------------------------------------------------------
}
/////////////////////////////////////////////////////////////