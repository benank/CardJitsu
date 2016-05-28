package rendering;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import gameplay.Card;
import gameplay.Element;
import gameplay.Vector2;

public class Particles 
{
	private Vector2 screen_size = Screen.getSize();
	private Rectangle bounds;
	private int numParticles = 450;
	private Sprite sprite;
	private int maxTimeout = 60;
	private Vector2 size = new Vector2(screen_size.x * 0.025, screen_size.x * 0.03);
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public Particles(Card c, Element element)
	{
		bounds = c.getBounds();
		
		if(element == Element.Fire)
		{
			this.sprite = new Sprite(
					new SpriteSheet(new Texture("ParticleSheet"), 600, 200),
					2, 1, 3, 1, size);
		}
		else if(element == Element.Ice)
		{
			this.sprite = new Sprite(
					new SpriteSheet(new Texture("ParticleSheet"), 600, 200),
					1, 1, 3, 1, size);
		}
		else
		{
			this.sprite = new Sprite(
					new SpriteSheet(new Texture("ParticleSheet"), 600, 200),
					3, 1, 3, 1, size);
		}
		
		for(int i = 0; i < numParticles; i++)
		{
			double xAdd = (Math.random() >= 0.5) ? -Math.random() * bounds.getWidth() / 2 : Math.random() * bounds.getWidth() / 2;
			double yAdd = (Math.random() >= 0.5) ? -Math.random() * bounds.getHeight() / 2 : Math.random() * bounds.getHeight() / 2;
			particles.add(new Particle(
					new Vector2(bounds.getCenterX() + xAdd, bounds.getCenterY() + yAdd), //pos
					new Vector2(Math.random() * 30d, Math.random() * 30d), //speed
					sprite,
					bounds,
					(int) (Math.random() * maxTimeout) //lifetime
					));
		}
	}
	
	public void render(Graphics2D g)
	{
		for(Particle p : particles)
		{
			p.render(g);
		}
	}
	
}
