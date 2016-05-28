package rendering;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import gameplay.Vector2;

public class Buttons 
{
	private ArrayList<Image> buttons = new ArrayList<Image>();
	private int hovered = 6;
	
	public Buttons()
	{
		SpriteSheet buttonSheet = new SpriteSheet(new Texture("ButtonSheet"), 1200, 400);
		for(int c = 0; c < 3; c++)
		{
			for(int r = 0; r < 2; r++)
			{
				Image img = new Image(new Sprite(buttonSheet,c+1,r+1,3,2,Positions.getButtonSize()), new Vector2());
				buttons.add(img);
				img.setPos(Positions.getButtonPos(img, c));
			}
		}
	}
	
	public void setHovered(int h) {hovered = h;}
	
	public int select()
	{
		Point clickPoint = MouseInfo.getPointerInfo().getLocation();
		for(Image i : buttons)
		{
			if(i.getBounds().contains(clickPoint))
			{
				return buttons.indexOf(i);
			}
		}
		return 6;
	}
	
	public void render(Graphics2D g)
	{
		hovered = 6;
		Point clickPoint = MouseInfo.getPointerInfo().getLocation();
		for(Image i : buttons)
		{
			if(i.getBounds().contains(clickPoint))
			{
				hovered = buttons.indexOf(i);
			}
		}
		for(int i = 0; i < 3; i++)
		{
			buttons.get(i*2).render(g);
		}
		if(hovered < 6)
		{
			buttons.get(hovered).render(g);
		}

	}
}
