package gameplay;

import java.util.ArrayList;

import rendering.Positions;

public class Deck 
{
	public ArrayList<Card> cards = new ArrayList<Card>();
	private int id;
	
	public Deck(int id)
	{
		this.id = id;
		
		for(int i = 0; i < 25; i++)
		{
			cards.add(getRandomCard());
		}
		
	}
	
	public Card getRandomCardFromDeck()
	{
		if(cards.size() == 0)
		{
			for(int i = 0; i < 25; i++)
			{
				cards.add(getRandomCard());
			}
		}
		Card c = cards.get((int) (Math.random() * cards.size()));
		cards.remove(c);
		return c;
	}

	private int getRandomValue()
	{
		double random = Math.random();
		int value = 0;
		if(random < .15f) {value = 2;}
		else if(random < .30f) {value = 3;}
		else if(random < .40f) {value = 4;}
		else if(random < .60f) {value = 5;}
		else if(random < .71f) {value = 6;}
		else if(random < .82f) {value = 7;}
		else if(random < .93f) {value = 8;}
		else {value = 10;}
		if(id == 1 && AI.getDifficulty() == 3)
		{
			value = (Math.random() <= 0.5f) ? value : 10;
		}
		return value;
	}

	private Card getRandomCard()
	{
		Element element;
		
		double random = Math.random();
		if(random <= 1/3f) {element = Element.Fire;}
		else if(random <= 2/3f) {element = Element.Ice;}
		else {element = Element.Water;}
		
		int value = getRandomValue();
		Vector2 pos = Positions.getLowerCardSpawn();
		if(id != 0)
		{
			pos = Positions.getUpperCardSpawn();
		}
		
		return new Card(element, value, pos);
	}

	
}
