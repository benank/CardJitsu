package gameplay;

public class Wait 
{			
	public static long playCard()
	{
		return (long) (Math.random() * 1 * 1000);
	}
	public static long revealCard()
	{
		return (long) (1 * 1000);
	}
	public static long determineWinner()
	{
		return (long) (3 * 1000);
	}
}
