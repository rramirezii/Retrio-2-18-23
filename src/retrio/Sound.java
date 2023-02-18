package retrio;

import java.net.URL;

import javax.sound.sampled.*;

public class Sound {
	
	boolean musicMute = false;
	boolean soundMute = false;
	
	Clip clipBGM, clipSFX; 
	
	//For playing background music for the game
	public void backgroundMusic(URL soundFileLocation, int val) { 
		
		try {
			
			if(musicMute) {
				
				soundFileLocation = null;
				
			}
			
			if(val <= 0) { //start new sound
				
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFileLocation); //brings the music from the file to the program
				clipBGM = AudioSystem.getClip(); //gets the audio
				clipBGM.open(audioInputStream);  //opens the clip with format and audio data
				clipBGM.start();                 //plays the clip
				
				if(val == -1) {
					
					clipBGM.loop(Clip.LOOP_CONTINUOUSLY);
					
				}
				
			} else if (val == 1) { //stop current sound
				
				clipBGM.stop();
				
			}
			
		} catch(Exception e) {
			
			System.out.println(" ");
			
		}
	}
	
	//For playing sound effects for the game
	public void soundEffect(URL soundFileLocation, int val) { 
		
		try {
			
			if(soundMute) {
				
				soundFileLocation = null;
				
			}
			
			if(val <= 0) { //start new sound
				
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFileLocation); //brings the music from the file to the program
				clipSFX = AudioSystem.getClip(); //gets the audio
				clipSFX.open(audioInputStream);  //opens the clip with format and audio data
				clipSFX.start();                 //plays the clip
				
				if(val == -1) {
					
					clipSFX.loop(Clip.LOOP_CONTINUOUSLY);
					
				}
				
			} else if (val == 1) { //stop current sound
				
				clipSFX.stop();
				
			}
			
		} catch(Exception e) {
			
			System.out.println(" ");
			
		}
	}

}
