package gameplay;

public class Vector2
{
	public double x;
	public double y;
	
	public Vector2()
	{
		
	}
	
	public Vector2(double d, double e)
	{
		this.x = d;
		this.y = e;
	}
	
	public Vector2 sub(Vector2 v)
	{
		return new Vector2(x - v.x, y - v.y);
	}
	
	public Vector2 add(Vector2 v)
	{
		return new Vector2(x + v.x, y + v.y);
	}
	
	public Vector2 div(double d)
	{
		return new Vector2(x / d, y / d);
	}
	
	public Vector2 mult(double d)
	{
		return new Vector2(x * d, y * d);
	}
	
	public double dist(Vector2 v)
	{
		return Math.sqrt(Math.pow(v.x - x, 2) + Math.pow(v.y - y, 2));
	}
	
	public boolean equals(Vector2 v)
	{
		return v.x == x && v.y == y;
	}
	
	public String toString()
	{
		return x + ", " + y;
	}
	
}