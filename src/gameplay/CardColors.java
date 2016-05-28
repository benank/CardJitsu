package gameplay;

public class CardColors 
{
	public static String[][] color = 
		{{"Red", "Orange", "Purple", "Green", "Red", "Yellow", "Green", "Green"},
		 {"Yellow", "Blue", "Purple", "Orange", "Red", "Blue", "Green", "Yellow"},
		 {"Green", "Green", "Purple", "Blue", "Blue", "Red", "Green", "Orange"}};
	
	public static String getColor(int row, int col)
	{
		return color[col-1][row-1];
	}
	
}
