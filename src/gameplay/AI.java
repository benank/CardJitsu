package gameplay;

import java.util.ArrayList;

public class AI 
{
	private static int difficulty = 0; //from 0-3
	private static Card playedCard;
	private static ArrayList<ArrayList<Card>> wins = new ArrayList<ArrayList<Card>>();
	
	public static void setDifficulty(int d) {difficulty = d;}
	public static void setPlayedCard(Card c) {playedCard = c;}
	public static void setWins(ArrayList<ArrayList<Card>> win) {wins = win;}
	public static int getDifficulty() {return difficulty;}
	
	public static int getNumWins()
	{
		int cnt = 0;
		for(ArrayList<Card> cards : wins)
		{
			cnt += cards.size();
		}
		return cnt;
	}
	
	public static ArrayList<ArrayList<Card>> getWins() {return wins;}
	public static void clearWins()
	{
		for(ArrayList<Card> cards : wins)
		{
			cards.clear();
		}
	}
	
	public static Card getCardToPlay(ArrayList<Card> cards)
	{
		if(difficulty == 0) {return levelOne(cards);}
		else if(difficulty == 1) {return levelTwo(cards);}
		else {return levelThree(cards);}
	}

	public static Card levelOne(ArrayList<Card> cards)
	{
		Card c = getCardThatWins(cards);
		if(c == null)
		{
			if(Math.random() >= 0.25f)
			{
				c = getCardThatLoses(cards);
				if(c == null)
				{
					c = getRandomCard(cards);
				}
			}
			else
			{
				c = getRandomCard(cards);
			}
		}
		return c;
	}
	
	public static Card getRandomCard(ArrayList<Card> cards)
	{
		return cards.get((int) (Math.random() * cards.size()));
	}

	public static Card levelTwo(ArrayList<Card> cards)
	{
		double random = Math.random();
		if(random < 0.1d)
		{
			Card c = getCardThatBeats(cards);
			if(c == null)
			{
				return getRandomCard(cards);
			}
			else
			{
				return c;
			}
		}
		else if(random < 0.6d)
		{
			Card c = getCardThatWins(cards);
			if(c == null)
			{
				return getRandomCard(cards);
			}
			else
			{
				return c;
			}
		}
		else if(random < 0.7d)
		{
			Card c = getCardThatLoses(cards);
			if(c == null)
			{
				return getRandomCard(cards);
			}
			else
			{
				return c;
			}
		}
		else
		{
			return getRandomCard(cards);
		}
	}
	
	public static Card getCardThatBeats(ArrayList<Card> cards)
	{

		ArrayList<Card> elementBeats = new ArrayList<Card>();
		ArrayList<Card> valueBeats = new ArrayList<Card>();
		for(Card c : cards)
		{
			if(Element.beats(c.getElement(), playedCard.getElement()))
			{
				elementBeats.add(c);
			}
			if(c.getValue() >= playedCard.getValue())
			{
				valueBeats.add(c);
			}
		}
		if(elementBeats.size() > 0)
		{
			Card lowestValue = elementBeats.get(0);
			for(Card c : elementBeats)
			{
				if(c.getValue() > lowestValue.getValue())
				{
					lowestValue = c;
				}
			}
			return lowestValue;
		}
		else if(valueBeats.size() > 0)
		{
			Card highestValue = valueBeats.get(0);
			for(Card c : valueBeats)
			{
				if(c.getValue() > playedCard.getValue())
				{
					highestValue = c;
				}
			}
			return highestValue;
		}
		else
		{
			return null;
		}
	}
	
	public static Card getCardThatWins(ArrayList<Card> cards)
	{
		ArrayList<Card> winCards = new ArrayList<Card>();
		for(ArrayList<Card> row : wins)
		{
			for(Card c : row)
			{
				for(Card c2 : row)
				{
					if(!c.getColor().equals(c2.getColor()))
					//if we have 2 of the same element but different colors
					{
						for(Card c3 : cards)
						{
							if(c3.getElement() == c2.getElement() && !c3.getColor().equals(c2) 
									&& !c3.getColor().equals(c.getColor()))
							{
								winCards.add(c3);
							}
						}
					}
				}
			}
		}
		if(wins.size() > 0 && wins.get(0).size() > 0 && wins.get(1).size() > 0 && wins.get(2).size() > 0)
		{
			for(Card c : wins.get(0)) //ice
			{
				for(Card c2 : wins.get(1)) //fire
				{
					for(Card c3 : wins.get(2)) //water
					{
						if(c != null && c2 != null 
								&& !c.getColor().equals(c2) && findElement(cards, Element.Water) != null)
						{
							winCards.add(findElement(cards, Element.Water));
						}
						else if(c2 != null && c3 != null 
								&& !c2.getColor().equals(c3) && findElement(cards, Element.Ice) != null)
						{
							winCards.add(findElement(cards, Element.Ice));
						}
						else if(c3 != null && c != null 
								&& !c3.getColor().equals(c) && findElement(cards, Element.Fire) != null)
						{
							winCards.add(findElement(cards, Element.Ice));
						}
					}
				}
			}
		}
		if(winCards.size() > 0)
		{
			return getRandomCard(winCards);
		}
		else
		{
			return null;
		}
		
	}
	
	public static Card findElement(ArrayList<Card> cards, Element e)
	{
		for(Card c : cards)
		{
			if(c.getElement() == e)
			{
				return c;
			}
		}
		return null;
	}

	public static Card getCardThatLoses(ArrayList<Card> cards)
	{
		for(Card c : cards)
		{
			if(!Element.beats(c.getElement(), playedCard.getElement()))
			{
				return c;
			}
			if(c.getValue() < playedCard.getValue())
			{
				return c;
			}
		}
		return null;
	}

	public static Card levelThree(ArrayList<Card> cards)
	{
		Card c = getCardThatWins(cards);
		double random = Math.random();
		if(random <= 0.7)
		{
			if(c == null)
			{
				c = getCardThatBeats(cards);
				if(c == null)
				{
					c = getRandomCard(cards);
				}
			}
		}
		else if(random <= 0.85)
		{
			c = getCardThatWins(cards);
		}
		else if(random <= 0.9)
		{
			if(c == null)
			{
				c = getCardThatLoses(cards);
			}
		}
		if(c == null)
		{
			c = getRandomCard(cards);
		}
		return c;
	}
	
	
	
}
