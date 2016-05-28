package rendering;

import gameplay.Element;

public class ColorSheet 
{
	public static SpriteSheet get()
	{
		return new SpriteSheet(new Texture("ColorSheet"), 600, 1200);
	}
	public static int getRow(String c)
	{
		if(c.equals("Red")) {return 1;}
		else if(c.equals("Orange")) {return 2;}
		else if(c.equals("Purple")) {return 3;}
		else if(c.equals("Green")) {return 4;}
		else if(c.equals("Yellow")) {return 5;}
		else if(c.equals("Blue")) {return 6;}
		else {return 1;}
	}
	public static int getCol(Element e)
	{
		if(e == Element.Ice) {return 1;}
		else if(e == Element.Fire) {return 2;}
		else if(e == Element.Water) {return 3;}
		else {return 1;}
	}
}
