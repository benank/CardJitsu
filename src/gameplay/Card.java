package gameplay;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import rendering.BackSprite;
import rendering.CardSheet;
import rendering.ColorSheet;
import rendering.Positions;
import rendering.Sprite;

public class Card 
{
	private Vector2 pos = new Vector2();
	private Sprite sprite;
	private Element element;
	private Sprite color;
	private Sprite back;
	private Vector2 target = new Vector2();
	private int value;
	private boolean color_only;
	private boolean back_only;
	private boolean enabled = true;
	
	public Card(Element element, int value, Vector2 pos)
	{
		this.element = element;
		this.value = value;
		this.pos = pos;
		initializeCard();
	}
	
	private void initializeCard()
	{
		this.sprite = new Sprite(CardSheet.get(), getCol(), getRow(), 3, 8, Positions.scaled_size);
		this.color = new Sprite(ColorSheet.get(), 
				ColorSheet.getCol(element), 
				ColorSheet.getRow(CardColors.getColor(getRow(), getCol())),
				3, 6, Positions.scaled_color_size);
		
		back = BackSprite.get(Positions.scaled_size.mult(0.5));
		
	}
	
	private void lerp(Vector2 v, double d)
	{
		Vector2 diff = new Vector2((v.x - pos.x) * d, (v.y - pos.y) * d);
		pos = new Vector2(pos.x + diff.x, pos.y + diff.y);
	}
	
	public void setPos(Vector2 pos) {target = pos;}
	public void setX(float x) {target.x = x;}
	public void setY(float y) {target.y = y;}
	
	public Vector2 getPos() {return pos;}
	public double getX() {return pos.x;}
	public double getY() {return pos.y;}
	public int getWidth() {return sprite.getWidth();}
	public int getHeight() {return sprite.getHeight();}
	public int getBackWidth() {return back.getWidth();}
	public int getBackHeight() {return back.getHeight();}
	public int getColorWidth() {return color.getWidth();}
	public int getColorHeight() {return color.getHeight();}
	public int getValue() {return value;}
	public Vector2 getTarget() {return target;}
	public void setEnabled(boolean enabled) {this.enabled = enabled;}
	public String getColor()
	{
		return CardColors.getColor(getRow(), getCol());
	}
	public void setColorOnly(boolean color_only) {this.color_only = color_only;}
	public int getCol() 
	{
		int col;
		if(element == Element.Fire) {col = 2;}
		else if(element == Element.Ice) {col = 1;}
		else {col = 3;}
		return col;
	}
	public int getRow() 
	{
		return (value < 10) ? value - 1 : value - 2;
	}
	public void scaleToPlaySize()
	{
		sprite = new Sprite(CardSheet.get(), getCol(), getRow(), 3, 8, Positions.play_size_v2);
	}
	public void scaleBackToPlaySize()
	{
		back = BackSprite.get(Positions.play_size_v2);
	}
	
	public void scale()
	{
		sprite.scale(Positions.play_size_v2);
	}
	public boolean getBack() {return back_only;}
	public void setBack(boolean b) {back_only = b;}
	public Element getElement() {return element;}
	public Rectangle getBounds()
	{
		return new Rectangle((int) pos.x, (int) pos.y, getWidth(), getHeight());
	}
	
	public void render(Graphics2D g)
	{
		lerp(target, 0.05);
		if(!color_only && !back_only && enabled)
		{
			sprite.render(g, (int) pos.x, (int) pos.y);
		}
		else if(back_only && !color_only && enabled)
		{
			back.render(g, (int) pos.x, (int) pos.y);
		}
		else if(enabled)
		{
			color.render(g, (int) pos.x, (int) pos.y);
		}
		//g.drawRect((int) pos.x, (int) pos.y, getWidth(), getHeight());
	}
	
	public boolean equals(Object o)
	{
		if(o == null) {return false;}
		Card c = (Card) o;
		return (c.element == element && c.pos.equals(pos) && c.value == value);
	}

	public String toString()
	{
		return "" + pos + " " + getWidth() + " " + getHeight() + " " + value + " " + element + " " + color_only + " " + back_only;
	}
	
}
