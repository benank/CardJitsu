package audio;

import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class MusicPlayer implements Runnable
{
	private ArrayList<String> musicFiles;
	private int currentSongIndex;
	private Clip clip;
	private int loop = 99999;
	
	public MusicPlayer(String... files)
	{
		musicFiles = new ArrayList<String>();
		for(String file : files)
		{
			musicFiles.add("/audio/" + file + ".wav");
		}
	}
	
	private void playSound(String fileName)
	{
		try{
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(fileName));
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			clip.start();
			clip.loop(loop);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setVolume(int v)
	{
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(v);
	}
	
	public String getPlaying()
	{
		return musicFiles.get(currentSongIndex);
	}
	
	public void stop()
	{
		clip.stop();
	}
	
	@Override
	public void run()
	{
		loop = 999999;
		playSound(musicFiles.get(currentSongIndex));
	}
	public void run2()
	{
		loop = 0;
		playSound(musicFiles.get(currentSongIndex));
	}
	
	
	
}
