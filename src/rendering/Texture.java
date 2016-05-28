package rendering;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import managers.TextureManager;

public class Texture 
{
	private final static Map<String, TextureManager> texMap = new HashMap<String, TextureManager>();
	private TextureManager manager;
	private String fileName;
	
	//All this only loads a texture once and it is remove from memory once we are done using it
	
	public Texture(String fileName)
	{
		this.fileName = fileName;
		TextureManager oldTexture = texMap.get(fileName);
		if(oldTexture != null)
		{
			manager = oldTexture;
			manager.addReference();
		}
		else
		{
			try {
				manager = new TextureManager(ImageIO.read(getClass().getClassLoader().getResource(("textures/" + fileName + ".png"))));
				texMap.put(fileName, manager);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	protected void finalize() throws Throwable
	{
		if(manager.removeReference() && !fileName.isEmpty())
		{
			texMap.remove(fileName);
		}
		super.finalize();
	}
	
	public void render(Graphics2D g, double x, double y)
	{
		g.drawImage(manager.getImage(), (int) x, (int) y, null);
	}
	
	public BufferedImage getImage()
	{
		return manager.getImage();
	}
	
}
