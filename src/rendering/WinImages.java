package rendering;

import java.awt.Graphics2D;
import java.util.ArrayList;

import gameplay.Vector2;

public class WinImages 
{
	private ArrayList<Image> images = new ArrayList<Image>();
	private int winnerId;
	private boolean enabled = false;
	
	public WinImages()
	{
		images.add(new Image(
				WinSprite.getYouWin(Positions.getYouWinSize()),
				new Vector2()));
		images.add(new Image(
				WinSprite.getSenseiWin(Positions.getSenseiWinSize()),
				new Vector2()));
		images.get(0).setPos(Positions.getWinPos(images.get(0)));
		images.get(1).setPos(Positions.getWinPos(images.get(1)));
	}

	public void setWinnerId(int id) {winnerId = id;}
	public void setEnabled(boolean enabled) {this.enabled = enabled;}
	
	public void render(Graphics2D g)
	{
		if(enabled)
		{
			images.get(winnerId).render(g);
		}
	}
	
	
}
