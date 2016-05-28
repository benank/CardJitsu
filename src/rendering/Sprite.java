package rendering;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import gameplay.Vector2;

public class Sprite 
{
	private BufferedImage image;
	private float opacity = 1;
	
	public Sprite(SpriteSheet spritesheet, int x, int y, int numC, int numR, Vector2 scaledSize)
	{
		//Gets the sections of the spritesheet for the image we want
		int sizeX = spritesheet.getWidth() / numC;
		int sizeY = spritesheet.getHeight() / numR;
		this.image = spritesheet.getTexture().getImage().getSubimage(
				(x * sizeX) - sizeX, 
				(y * sizeY) - sizeY, 
				sizeX, sizeY);
		scale(scaledSize);
	}
	
	public void setImage(BufferedImage img)
	{
		this.image = img;
	}

	public void scale(Vector2 scaledSize)
	{
		image = toBufferedImage(image.getScaledInstance((int)scaledSize.x, (int) scaledSize.y, Image.SCALE_SMOOTH));
	}

	public BufferedImage scale3(Vector2 scaledSize)
	{
		return toBufferedImage(image.getScaledInstance((int)scaledSize.x, (int) scaledSize.y, Image.SCALE_SMOOTH));
	}

	public void scale2(double scale)
	{
		BufferedImage before = image;
		int w = before.getWidth();
		int h = before.getHeight();
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scale, scale);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(before, after);
		image = after;
	}
	
	public float getOpacity() {return opacity;}
	public void setOpacity(float opacity) {this.opacity = opacity;}

	public int getWidth()
	{
		return image.getWidth();
	}
	
	public int getHeight()
	{
		return image.getHeight();
	}

	public void render(Graphics2D g, double x, double y)
	{
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(image, (int) x, (int) y, null);
		g.setComposite(AlphaComposite.Clear);
	}
	
	public BufferedImage getImage()
	{
		return image;
	}

	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	
}
