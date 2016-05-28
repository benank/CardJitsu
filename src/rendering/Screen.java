package rendering;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import gameplay.Vector2;

public abstract class Screen 
{
	public static Vector2 getSize()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		return new Vector2(width, height);
	}
	
}
