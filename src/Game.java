/////////////////////////////////////////////////////////////
public class Game 
{
//-----------------------------------------------------------
	public static void main( String[] args ) throws InstantiationException, IllegalAccessException
	{
		final int NUM_GAMES = 20; // Play n amount of games
		BlackJack game = new BlackJack();
		game.addPlayers( 2 );
		for ( int i = 0; i < NUM_GAMES; i++ )
		{
			game.startNewGame();
		}
	}
//-----------------------------------------------------------
}
/////////////////////////////////////////////////////////////