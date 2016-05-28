package rendering;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameplay.Vector2;

public class Image 
{
	private Vector2 pos;
	private Sprite sprite;
	
	public Image(Sprite sprite, Vector2 pos)
	{
		this.sprite = sprite;
		this.pos = pos;
	}

	public int getWidth() {return sprite.getWidth();}
	public int getHeight() {return sprite.getHeight();}
	public Vector2 getPos() {return pos;}
	public Rectangle getBounds() {return new Rectangle((int) pos.x, (int) pos.y, getWidth(), getHeight());}

	public void setPos(Vector2 v) {pos = v;}
	
	public void render(Graphics2D g)
	{
		sprite.render(g, (int) pos.x, (int) pos.y);
	}
}
