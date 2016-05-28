package rendering;

import gameplay.Vector2;

public class NameSprite 
{
	public static Sprite getOpponent(Vector2 scaled_size, String name)
	{
		return new Sprite(
				new SpriteSheet(new Texture(name), 500, 200),
				1,1,1,1,scaled_size);
	}
	
	public static Sprite getYou(Vector2 scaled_size)
	{
		return new Sprite(
				new SpriteSheet(new Texture("You"), 500, 250),
				1,1,1,1,scaled_size);
	}
}
