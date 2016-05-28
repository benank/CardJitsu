package gameplay;

public enum Element 
{
	Fire, Ice, Water;
	
	public static boolean beats(Element e1, Element e2)
	{
		if(e1 == Element.Fire && e2 == Element.Ice) {return true;}
		else if(e1 == Element.Ice && e2 == Element.Water) {return true;}
		else if(e1 == Element.Water && e2 == Element.Fire) {return true;}
		else {return false;}
	}
}
