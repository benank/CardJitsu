package rendering;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class ImageTransform 
{
	private AffineTransform transform = new AffineTransform();
	private double rotateDegrees;
	private Sprite sprite;
	
	public ImageTransform(Sprite sprite)
	{
		this.sprite = sprite;
	}
	
	public void buildTransform()
	{
		transform.translate(sprite.getWidth() * 0.5, sprite.getHeight() * 0.5);
		transform.rotate(Math.toRadians(rotateDegrees));
	}
	
	public void render(Graphics2D g)
	{
		   AffineTransform origTransform = g.getTransform();

		   g.setTransform (transform);

		   g.drawImage(sprite.getImage(),
		                sprite.getWidth(),
		                sprite.getHeight(),
		                null);

		   g.setTransform( origTransform );
	}
}
