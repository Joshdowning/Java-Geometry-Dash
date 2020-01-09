import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import java.io.File;

public class MusicHandler implements Runnable{
	private ArrayList<String>songList;
	private int songIndex;	
	public MusicHandler(String... files){
	songList = new ArrayList<String>();
	for(String file:files)
		songList.add("src/res/"+file+".wav");
	}
	
		
	private synchronized void playSong(String fileName){
		try{
	
			File songFile = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(songFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class,format);
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(ais);
			clip.start();
			try{
				this.wait();
			}catch(InterruptedException e){
				clip.stop();
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	@Override
	public void run(){
		playSong(songList.get(songIndex));
	}
		
}
	
