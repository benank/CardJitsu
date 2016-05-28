package rendering;

import java.awt.Graphics2D;
import java.util.ArrayList;

import gameplay.AI;
import gameplay.Vector2;

public class Names 
{
	private ArrayList<Image> images = new ArrayList<Image>();
	
	public Names()
	{
		if(AI.getDifficulty() == 0)
		{
				images.add(new Image(
				NameSprite.getOpponent(Positions.getSenseiNameSize(), "Sensei"),
				new Vector2()));
		}
		else if(AI.getDifficulty() == 1)
		{
				images.add(new Image(
					NameSprite.getOpponent(Positions.getSenseiNameSize(), "Sensei"),
					new Vector2()));
		}
		else if(AI.getDifficulty() == 2)
		{
				images.add(new Image(
					NameSprite.getOpponent(Positions.getSenseiNameSize(), "Sensei"),
					new Vector2()));
		}
		else if(AI.getDifficulty() == 3)
		{
				images.add(new Image(
					NameSprite.getOpponent(Positions.getSenseiNameSize(), "Nico"),
					new Vector2()));
		}
		
		images.add(new Image(
				NameSprite.getYou(Positions.getYouNameSize()),
				new Vector2()));
		images.get(0).setPos(Positions.getSenseiNamePos(images.get(0)));
		images.get(1).setPos(Positions.getYouNamePos(images.get(1)));
	}
	
	public void render(Graphics2D g)
	{
		for(Image i : images)
		{
			i.render(g);
		}
	}
	
	
}
