package rendering;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameplay.Vector2;

public class Particle 
{
	private Vector2 pos;
	private Vector2 size;
	private Sprite sprite;
	private Vector2 speed;
	private int direction = (Math.random() >= 0.5f) ? -1 : 1;
	private int timeOut;
	private int time;
	
	public Particle(Vector2 pos, Vector2 speed, Sprite sprite, Rectangle bounds, int timeOut)
	{
		this.size = new Vector2(sprite.getWidth(), sprite.getHeight());
		this.pos = pos;
		this.sprite = sprite;
		this.speed = speed;
		speed.x *= direction;
		direction = (Math.random() >= 0.5f) ? -1 : 1;
		speed.y *= direction;
		this.timeOut = timeOut;
	}
	
	public Rectangle getSpriteBounds() 
	{
		return new Rectangle((int) pos.x, (int) pos.y, sprite.getWidth(), sprite.getHeight());
	}
	
	public void render(Graphics2D g)
	{
		if(time < timeOut)
		{
			move();
			sprite.render(g, (int) pos.x, (int) pos.y);
			time++;
			//g.setColor(Color.blue);
			//g.draw(getSpriteBounds());
			//System.out.println(pos);
		}
	}
	
	private void move()
	{
		//System.out.println(bounds + " " + spriteBounds);
		pos = pos.add(speed.mult(0.2));
		double maxTime = timeOut * 0.5;
		if(time > maxTime)
		{
			float scale = (float) (1 - ((time - maxTime) / maxTime));
			if(size.x > 1 && size.y > 1)
			{
				sprite.setOpacity(scale);
			}
		}
	}
}
