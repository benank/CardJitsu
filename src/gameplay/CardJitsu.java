package gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;

import audio.MusicPlayer;
import rendering.Buttons;
import rendering.Image;
import rendering.Names;
import rendering.Particles;
import rendering.Positions;
import rendering.Screen;
import rendering.Sprite;
import rendering.SpriteSheet;
import rendering.Texture;
import rendering.WinImages;


@SuppressWarnings("serial")
public class CardJitsu extends Canvas implements MouseListener, Runnable
{

	//private static final long serialVersionUID = 5102403758998611765L;
	private static final String TITLE = "Card Jitsu - Nico Edition";
	private Vector2 screen_size = Screen.getSize();
	//private int FPS;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int id = 0;
	private int handSize = 5;
	private boolean running;
	private boolean gameOver;
	private static MusicPlayer musicPlayer;
	private Names names = new Names();
	private WinImages winImages = new WinImages();
	private Buttons buttons = new Buttons();
	private Image title;
	private Image background;
	private Image background2;
	private Image loading;
	private ArrayList<Particles> particles = new ArrayList<Particles>();
	private long delay = 1;
	private long d = 0;
	private int state = 1; //1 = menu, 2 = game, 3 = loading
	private Player winner;
	private Image superButton;
	private MusicPlayer voicePlayer;
	private MusicPlayer soundPlayer;
	

	public CardJitsu()
	{
		
    	setBackground(Color.GRAY);
		setVisible(true);
		
		new Thread(this).start();
		addMouseListener(this);
		
		Sprite titleSprite = new Sprite(
				new SpriteSheet(new Texture("Title"), 1364, 1000),
				1,1,1,1,Positions.getTitleSize());
		title = new Image(titleSprite, Positions.getTitlePos(titleSprite));

		background = new Image(new Sprite(
				new SpriteSheet(new Texture("Background"), 2560, 1600),
				1,1,1,1, screen_size), new Vector2());

		background2 = new Image(new Sprite(
						new SpriteSheet(new Texture("NicoBackground"), 1920, 1080),
						1,1,1,1, screen_size), new Vector2());

		Sprite loadingSprite = new Sprite(
				new SpriteSheet(new Texture("Loading"), 610, 500),
				1,1,1,1, new Vector2(screen_size.x * 0.3, screen_size.x * 0.3 * 0.82));
		loading = new Image(loadingSprite, new Vector2(
				screen_size.x * 0.5 - loadingSprite.getWidth() * 0.5,
				screen_size.y * 0.5 - loadingSprite.getHeight() * 0.5));
		
		superButton = new Image(new Sprite(new SpriteSheet(new Texture("ButtonSheet"), 1200, 400),1,1,3,2,new Vector2(screen_size.x * 0.12, screen_size.x * 0.12)),
				new Vector2(screen_size.x * 0.29, screen_size.y * 0.37));
		
	}
	
	private Player createPlayer()
	{
		Player player = new Player(handSize, id);
		id++;
		return player;
	}
	
	private void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		// Do all drawing here
		if(state == 2 && AI.getDifficulty() == 3)
		{
			background2.render(g2d);
		}
		else
		{
			background.render(g2d);
		}
		
		d--;
		d = (d < 0) ? 0 : d;
		
		if(state == 2)
		{
			
			for(Particles ps : particles)
			{
				ps.render(g2d);
			}
			
			for(Player p : players)
			{
				p.render(g2d);
			}
			names.render(g2d);
			
			if(gameOver && !checkMovingCards() && d == 0)
			{
				loadMainMenu();
			}
			
			winImages.render(g2d);
			
			if(players.size() > 0 && !players.get(0).getCanGo() && players.get(1).getCanGo() && !gameOver) //if the human played
			{
				if(d == 0)
				{
					players.get(1).go();
					//d = 10;
				}
			}
			else if(players.size() > 0 && !players.get(0).getCanGo() && !players.get(1).getCanGo() && players.get(1).getPlayedCard().getBack()) //if they both played
			{
				if(!checkMovingCards() && d == 0)
				{
					players.get(1).showCard();
					players.get(0).hand.generateNextCard();
					players.get(1).hand.generateNextCard();
					d = 90; //delay before card is destroyed
				}
				
			}
			else if(players.size() > 0 && !players.get(0).getCanGo() && !players.get(1).getCanGo() && !players.get(1).getPlayedCard().getBack())
			{
				if(!checkMovingCards() && d == 0 && !gameOver)
				{
					//createParticles();
					determineWinner();
				}
				else if(!checkMovingCards() && d == 30 && !gameOver)
				{
					createParticles();
					d = 25;
				}
			}
		}
		else if(state == 1)
		{
			d = 0;
			title.render(g2d);
			buttons.render(g2d);
		}
		else if(state == 3)
		{
			loading.render(g2d);
		}

		g.dispose();
		bs.show();
		
		
	}
	
	private boolean checkMovingCards()
	{
		//System.out.println(players.get(0).hand.checkMovingCards() + " " + players.get(1).hand.checkMovingCards());
		return players.get(0).hand.checkMovingCards() && players.get(1).hand.checkMovingCards();
	}
	
	private void loadMainMenu()
	{
		state = 1;
		musicPlayer = new MusicPlayer("MainMenuMusic");
		musicPlayer.run();
		background = new Image(new Sprite(
				new SpriteSheet(new Texture("Background"), 2560, 1600),
				1,1,1,1, screen_size), new Vector2());
		
		//resetGame();
	}
	
	private void playEffectSound(Element e)
	{
		if(e == Element.Ice)
		{
			soundPlayer = new MusicPlayer("Ice");
			soundPlayer.run2();
		}
		else if(e == Element.Fire)
		{
			soundPlayer = new MusicPlayer("Fire");
			soundPlayer.run2();
		}
		else
		{
			soundPlayer = new MusicPlayer("Water");
			soundPlayer.run2();
		}
	}
	
	private void createParticles()
	{
		winner = Logic.determineWinner(players.get(0), players.get(1));
		if(winner != null && winner.getId() == 0)
		{
			Card playedCard = players.get(1).getPlayedCard();
			particles.add(new Particles(playedCard, winner.getPlayedCard().getElement()));
			playedCard.setEnabled(false);
			playEffectSound(winner.getPlayedCard().getElement());
		}
		else if(winner != null)
		{
			Card playedCard = players.get(0).getPlayedCard();
			particles.add(new Particles(playedCard, winner.getPlayedCard().getElement()));
			playedCard.setEnabled(false);
			playEffectSound(winner.getPlayedCard().getElement());
		}
		else
		{
			Card playedCard1 = players.get(0).getPlayedCard();
			Card playedCard2 = players.get(1).getPlayedCard();
			particles.add(new Particles(playedCard1, playedCard1.getElement()));
			particles.add(new Particles(playedCard2, playedCard2.getElement()));
			playedCard1.setEnabled(false);
			playedCard2.setEnabled(false);
			playEffectSound(playedCard1.getElement());
		}
	}
	
	private void resetGame()
	{
		id = 0;
		
		players.clear();
		players.add(createPlayer());
		players.add(createPlayer());

		gameOver = false;
		names = new Names();
		Logic.clearWins();
		AI.clearWins();
		winImages.setEnabled(false);
		musicPlayer.stop();
		musicPlayer = new MusicPlayer("BattleMusic");
		musicPlayer.run();
		state = 2;
	}
	
	private void determineWinner() 
	{
		for(Player p : players)
		{
			p.resetRound();
		}
		if(winner != null && d == 0)
		{
			winner.addWin();
			if(winner.getId() == 1)
			{
				AI.setWins(winner.getWins());
			}
			gameOver = Logic.determineMatchWinner(winner.getWins()) && winner.getNumWins() > 2;
			//System.out.println(winner.getNumWins());
			if(gameOver)
			{
				players.get(0).setCanGo(false);
				players.get(1).setCanGo(false);
				System.gc();
				musicPlayer.stop();
				musicPlayer = new MusicPlayer("WinMusic");
				musicPlayer.run2();
				d = 350;
				if(winner.getId() == 0)
				{
					winImages.setWinnerId(0);
					winImages.setEnabled(true);
					int i = 0;
					for(Card c : Logic.getWinningCards())
					{
						c.setPos(Positions.getrightHandWinner(c, i));
						i++;
					}
				}
				else
				{
					winImages.setWinnerId(1);
					winImages.setEnabled(true);
					int i = 0;
					for(Card c : Logic.getWinningCards())
					{
						c.setPos(Positions.getleftHandWinner(c, i));
						i++;
					}
					if(AI.getDifficulty() == 3)
					{
						voicePlayer = new MusicPlayer("win1");
						voicePlayer.run2();
						voicePlayer.setVolume(5);
					}
				}
			}
			else if(winner != null && winner.getId() == 0 && AI.getDifficulty() == 3)
			{
				String fileName = "lose" + ((int) (Math.random() * 2) + 1);
				voicePlayer = new MusicPlayer(fileName);
				voicePlayer.run2();
				voicePlayer.setVolume(5);
			}
			else if(winner != null && AI.getDifficulty() == 3)
			{
				String fileName = "taunt" + ((int) (Math.random() * 10) + 1);
				voicePlayer = new MusicPlayer(fileName);
				voicePlayer.run2();
				voicePlayer.setVolume(5);
			}
		}
		winner = null;
	}

	private void stop()
	{
		System.exit(0);
	}
	
	private void start()
	{
		if(running) {return;}
		running = true;
		new Thread(this).start();
	}
	
	public void run()
	{
		
		double target = 60; //how many times we want to render per second
		double nsPerTick = 1000000000 / target;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double unprocessed = 0;
		//int tps = 0;
		boolean canRender = false;
		while(running)
		{
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / (nsPerTick);
			lastTime = now;
			
			if(unprocessed >= 1)
			{
				//tick();
				unprocessed--;
				//tps++;
				canRender = true;
			}
			else {canRender = false;}
			
			try{
				Thread.sleep(1);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			
			if(canRender && delay == 1)
			{
				render();
			}
			
			delay -= 1000 / target;
			delay = (delay < 1) ? 1 : delay;
			
			if(System.currentTimeMillis() - 1000 > timer)
			{
				timer += 1000;
			}
			
			
		}
	}
	
	public static void main(String[] args)
	{
		CardJitsu game = new CardJitsu();
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.setSize(1000, 1000);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.err.println("Exiting");
				game.stop();
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocus();
		game.start();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		musicPlayer = new MusicPlayer("MainMenuMusic");
		musicPlayer.run();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(state == 1)
		{
			int selected = buttons.select();
			
			if(selected != 6)
			{
				AI.setDifficulty(selected / 2);
				state = 3;
				resetGame();
			}
			else if(superButton.getBounds().contains(MouseInfo.getPointerInfo().getLocation()))
			{
				AI.setDifficulty(3);
				state = 3;
				resetGame();
				
			}
		}
		else if(state == 2 && !musicPlayer.getPlaying().equals("resources/audio/WinMusic.wav") && !gameOver)
		{
			players.get(0).go();
		}
		
	}
}