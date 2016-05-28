package rendering;

import gameplay.Vector2;

public class WinSprite 
{
	public static Sprite getSenseiWin(Vector2 scaled_size)
	{
		return new Sprite(
				new SpriteSheet(new Texture("SenseiWins"), 1300, 500),
				1,1,1,1,scaled_size);
	}
	
	public static Sprite getYouWin(Vector2 scaled_size)
	{
		return new Sprite(
				new SpriteSheet(new Texture("YouWin"), 1000, 500),
				1,1,1,1,scaled_size);
	}
}
