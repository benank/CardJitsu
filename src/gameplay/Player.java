package gameplay;

import java.awt.Graphics2D;
import java.util.ArrayList;

import rendering.Positions;

public class Player 
{
	public Hand hand;
	private Card playedCard;
	private ArrayList<ArrayList<Card>> wins;
	private boolean canGo = true;
	private int id;
	
	public Player(int size, int id)
	{
		this.id = id;
		hand = new Hand(size, id);
		wins = new ArrayList<ArrayList<Card>>();
		for(int i = 0; i < 3; i++)
		{
			wins.add(new ArrayList<Card>());
		}
	}
	
	public void render(Graphics2D g)
	{
		hand.render(g);
		for(ArrayList<Card> cards : wins)
		{
			for(Card c : cards)
			{
				c.render(g);
			}
		}
	}
	
	public Card getPlayedCard()
	{
		return playedCard;
	}
	
	public void showCard()
	{
		playedCard.setBack(false);
	}
	
	public int getNumWins()
	{
		int cnt = 0;
		for(ArrayList<Card> cards : wins)
		{
			cnt += cards.size();
		}
		return cnt;
	}
	
	public void addWin()
	{
		int index = 0;
		if(playedCard.getElement() == Element.Fire) {index = 1;}
		else if(playedCard.getElement() == Element.Water) {index = 2;}
		wins.get(index).add(playedCard);
		playedCard.setColorOnly(true);
		if(id == 0)
		{
			for(int i = 0; i < wins.get(index).size(); i++)
			{
				playedCard.setPos(Positions.getrightHandWins(playedCard, index, i));
			}
		}
		else
		{
			for(int i = 0; i < wins.get(index).size(); i++)
			{
				playedCard.setPos(Positions.getleftHandWins(playedCard, index, i));
			}
		}
			
	}
	
	public void setCanGo(boolean canGo)
	{
		this.canGo = canGo;
	}
	
	public void resetRound()
	{
		hand.replace(playedCard);
		canGo = true;
	}
	
	public boolean getCanGo() {return canGo;}
	public int getId() {return id;}
	public ArrayList<ArrayList<Card>> getWins() {return wins;}
	
	public void go()
	{
		if(canGo)
		{
			playedCard = hand.select();
			if(playedCard != null) //if they actually selected a card
			{
				canGo = false;
				if(id == 0)
				{
					AI.setPlayedCard(playedCard);
				}
			}
		}
	}
	
}
