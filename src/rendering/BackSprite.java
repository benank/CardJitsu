package rendering;

import gameplay.Vector2;

public class BackSprite 
{
	public static Sprite get(Vector2 scaled_size)
	{
		return new Sprite(
				new SpriteSheet(new Texture("CardBack"), 520, 590),
				1,1,1,1,scaled_size);
	}
	
	
	
}
