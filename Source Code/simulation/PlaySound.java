package simulation;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Gets a audio file and generates a audio system to
 * play sounds.
 * @author Rahmi
 *
 */
public class PlaySound {
	Clip clip;
	AudioInputStream audioIn;
	
	/**
	 * constructor
	 */
	public PlaySound() {
		
	}
	
	/**
	 * Create a audio file to play sound
	 * @param filename
	 */
	public PlaySound(String filename) {
	      
	      try {
	         // Open an audio input stream.
	         URL url = this.getClass().getResource(filename);
	         audioIn = AudioSystem.getAudioInputStream(url);
	         
	         // Get a sound clip resource.
	         clip = AudioSystem.getClip();
	         
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);

	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
	
	/**
	 * sound play method
	 */
	public void play(){
		clip.start();
		
	}
	
	/**
	 * sound stop method
	 */
	public void stop(){
		clip.stop();
		clip.close();
	}
}
