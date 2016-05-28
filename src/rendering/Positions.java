package rendering;

import gameplay.Card;
import gameplay.Vector2;

public class Positions 
{
	public static final Vector2 screen_size = Screen.getSize();

	public static final double card_size = 0.14;
	public static final double color_size = 0.05;
	public static final double card_play_size = 0.175;
	public static final double back_size = 0.12;
	public static final Vector2 scaled_size = new Vector2(Screen.getSize().x * 0.88 * card_size, Screen.getSize().x * card_size);
	public static final Vector2 scaled_color_size = new Vector2(Screen.getSize().x * color_size, Screen.getSize().x * color_size);
	public static final Vector2 play_size_v2 = new Vector2(Screen.getSize().x * 0.88 * card_play_size, Screen.getSize().x * card_play_size);


	public static Vector2 getPlayCardRight(Card c)
	{
		return screen_size.div(2).sub(new Vector2(-c.getWidth() * 0.05, c.getHeight() * 0.66));
	}
	
	public static Vector2 getPlayCardLeft(Card c)
	{
		return screen_size.div(2).sub(new Vector2(c.getWidth() * 1.05, c.getHeight() * 0.66));
	}

	public static Vector2 getLowerHand(Card c, int i)
	{
		return new Vector2(
				screen_size.x / 2 - c.getWidth() * 2.5 + c.getWidth() * i,
				screen_size.y - c.getHeight() - screen_size.y * 0.05);
	}
	
	public static Vector2 getUpperHand(Card c, int i)
	{
		return new Vector2(
				screen_size.x / 2 - c.getBackWidth() * 2.5 + c.getBackWidth() * i,
				screen_size.y * 0.025);
	}

	public static Vector2 getleftHandWins(Card c, int index, int i)
	{
		return new Vector2(
				(index + 1) * c.getColorWidth(),
				screen_size.x * 0.025 + i * c.getColorHeight() * 0.33);
	}

	public static Vector2 getrightHandWins(Card c, int index, int i)
	{
		return new Vector2(
				screen_size.x - c.getColorWidth() * 4 + index * c.getColorWidth(),
				screen_size.x * 0.025 + i * c.getColorHeight() * 0.33);
	}

	public static Vector2 getrightHandWinner(Card c, int i)
	{
		return new Vector2(
					screen_size.x - c.getColorWidth() * 4 + i * c.getColorWidth(),
					screen_size.y / 2 - c.getColorHeight() / 2);
	}

	public static Vector2 getleftHandWinner(Card c, int i)
	{
		return new Vector2(
				c.getColorWidth() * 4 - i * c.getColorWidth(),
				screen_size.y / 2 - c.getColorHeight() / 2);
	}

	public static Vector2 getUpperCardSpawn()
	{
		return new Vector2(screen_size.x / 2, -screen_size.y * 0.25);
	}

	public static Vector2 getLowerCardSpawn()
	{
		return new Vector2(screen_size.x / 2, screen_size.y * 1.25);
	}

	public static Vector2 getSenseiNameSize()
	{
		return screen_size.div(9);
	}

	public static Vector2 getYouNameSize()
	{
		return screen_size.div(9);
	}

	public static Vector2 getSenseiNamePos(Image i)
	{
		return new Vector2(screen_size.x * 0.25 - i.getWidth() / 2, screen_size.y * 0.075 - i.getHeight() / 2);
	}

	public static Vector2 getYouNamePos(Image i)
	{
		return new Vector2(screen_size.x * 0.7 - i.getWidth() / 2, screen_size.y * 0.075 - i.getHeight() / 2);
	}

	public static Vector2 getSenseiWinSize()
	{
		return screen_size.mult(0.33);
	}

	public static Vector2 getYouWinSize()
	{
		return screen_size.mult(0.33);
	}

	public static Vector2 getWinPos(Image i)
	{
		return new Vector2(screen_size.x / 2 - i.getWidth() / 2, screen_size.y / 2 - i.getHeight() * 0.7);
	}
	
	public static Vector2 getButtonSize()
	{
		return new Vector2(screen_size.x * 0.15, screen_size.x * 0.075);
	}

	public static Vector2 getButtonPos(Image img, int i)
	{
		return new Vector2(screen_size.x * 0.5 - img.getWidth() * 1.5 + img.getWidth() * i,
				screen_size.y * 0.5 + img.getHeight());
	}
	
	public static Vector2 getTitleSize()
	{
		return new Vector2(screen_size.x * 0.5, screen_size.x * 0.5 * 0.73);
	}
	
	public static Vector2 getTitlePos(Sprite i)
	{
		return new Vector2(screen_size.x * 0.5 - i.getWidth() * 0.5, screen_size.y * 0.375 - i.getHeight() * 0.5);
	}
	
	
}
