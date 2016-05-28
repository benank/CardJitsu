package gameplay;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import rendering.Positions;

public class Hand
{
	private ArrayList<Card> cards;
	private boolean has10Card = false;
	private Card nextCard;
	private int id;
	
	public Hand(int size, int id)
	{
		this.id = id;
		cards = getRandomCards(size);
		arrangeCards();
		hideCards();
	}
	
	public void generateNextCard()
	{
		nextCard = getRandomCard();
	}
	
	private void hideCards()
	{
		if(id != 0)
		{
			for(Card c : cards)
			{
				c.setBack(true);
			}
		}
	}
	
	public void render(Graphics2D g)
	{
		for(Card c : cards)
		{
			c.render(g);
		}
	}
	
	public void arrangeCards()
	{
		checkFor10Card();
		if(id == 0)
		{
			for(int i = 0; i < cards.size(); i++)
			{
				Card card = cards.get(i);
				card.setPos(Positions.getLowerHand(card, i));
			}
		}
		else
		{
			for(int i = 0; i < cards.size(); i++)
			{
				Card card = cards.get(i);
				card.setPos(Positions.getUpperHand(card, i));
			}
		}
		
	}
	
	private int getRandomValue()
	{
		double random = Math.random();
		int value = 0;
		double max = (has10Card) ? 0.07 : 0;
		if(random < .15f) {value = 2;}
		else if(random < .30f) {value = 3;}
		else if(random < .40f) {value = 4;}
		else if(random < .60f) {value = 5;}
		else if(random < .71f) {value = 6;}
		else if(random < .82f + max) {value = 7;}
		else if(random < .93f + max) {value = 8;}
		else if(random < 1f) {value = 10; has10Card = true;}
		else {value = 2;}
		if(id == 1 && AI.getDifficulty() == 3)
		{
			value = (Math.random() >= 0.5f) ? value : 10;
		}
		
		return value;
	}
	
	private void checkFor10Card()
	{
		has10Card = false;
		for(Card c : cards)
		{
			if(c.getValue() == 10)
			{
				has10Card = true;
			}
		}
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

	public void replace(Card c)
	{
		if(!cards.contains(c)) {return;}
		cards.set(cards.indexOf(c), nextCard);
		hideCards();
		arrangeCards();
	}
	
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	private ArrayList<Card> getRandomCards(int size)
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for(int i = 0; i < size; i++)
		{
			cards.add(getRandomCard());
		}
		
		return cards;
	}
	
	public boolean checkMovingCards()
	{
		boolean moving = false;
		for(Card c : cards)
		{
			double dist = c.getPos().dist(c.getTarget());
			if(dist > 0.1)
			{
				moving = true;
			}
		}
		return moving;
	}
	
	public String toString()
	{
		return cards.toString();
	}
	
	public Card select()
	{
		if(id == 0)
		{
			Point clickPoint = MouseInfo.getPointerInfo().getLocation();
			for(Card c : cards)
			{
				if(c.getBounds().contains(clickPoint))
				{
					c.scaleToPlaySize();
					c.setPos(Positions.getPlayCardRight(c));
					return c;
					//c.setBack(!c.getBack());
					//cards.set(cards.indexOf(c), getRandomCard());
					//arrangeCards();
				}
			}
			
		}
		else
		{
			Card c = AI.getCardToPlay(cards);
			//Card c = getRandomCardFromHand();
			c.scaleToPlaySize();
			c.scaleBackToPlaySize();
			c.setPos(Positions.getPlayCardLeft(c));
			return c;
		}
		return (Card) null;
		
	}

}
