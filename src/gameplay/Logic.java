package gameplay;

import java.util.ArrayList;

public class Logic 
{
	public static ArrayList<Card> wins = new ArrayList<>();

	public static Player determineWinner(Player p1, Player p2)
	{
		Card c1 = p1.getPlayedCard();
		Card c2 = p2.getPlayedCard();
		if(Element.beats(c1.getElement(), c2.getElement())) {return p1;}
		else if(Element.beats(c2.getElement(), c1.getElement())) {return p2;}
		else if(c1.getElement() == c2.getElement())
		{
			if(c1.getValue() > c2.getValue()) {return p1;}
			else if(c2.getValue() > c1.getValue()) {return p2;}
		}
		return (Player) null;
	}
	
	public static ArrayList<Card> getWinningCards()
	{
		return wins;
	}
	
	public static void clearWins()
	{
		wins.clear();
	}
	
	public static boolean determineMatchWinner(ArrayList<ArrayList<Card>> list)
	{
		int cnt = list.get(0).size() + list.get(1).size() + list.get(2).size();
		if(cnt < 3) {return false;}
		if(list.get(0).size() > 0 && list.get(1).size() > 0 && list.get(2).size() > 0)
		{
			for(Card c1 : list.get(0))
			{
				for(Card c2 : list.get(1))
				{
					for(Card c3 : list.get(2))
					{
						String s1 = c1.getColor();
						String s2 = c2.getColor();
						String s3 = c3.getColor();
						if(!s1.equals(s2) && !s2.equals(s3) && !s3.equals(s1))
						{
							wins.add(c1);
							wins.add(c2);
							wins.add(c3);
							return true; //checks horizontally
						}
					}
				}
			}
		}
		for(ArrayList<Card> cards : list)
		{
			if(cards.size() > 2)
			{
				for(int i = 0; i < cards.size(); i++)
				{
					for(int j = 0; j < cards.size(); j++)
					{
						for(int k = 0; k < cards.size(); k++)
						{
							String c1 = cards.get(i).getColor();
							String c2 = cards.get(j).getColor();
							String c3 = cards.get(k).getColor();
							if(!c1.equals(c2) && !c2.equals(c3) && !c3.equals(c1))
							{
								wins.add(cards.get(i));
								wins.add(cards.get(j));
								wins.add(cards.get(k));
								return true; //checks horizontally
							}
							
						}
					}
				}
			}
		}
		return false;
	}
	
	
}
