package rendering;

public class SpriteSheet 
{
	private Texture texture;
	private int width, height;
	
	public SpriteSheet(Texture texture, int size)
	{
		this(texture, size, size);	
	}
	
	public SpriteSheet(Texture texture, int width, int height)
	{
		this.texture = texture;
		this.width = width;
		this.height = height;
	}
	
	public Texture getTexture()
	{
		return texture;
	}

	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	
	
}
