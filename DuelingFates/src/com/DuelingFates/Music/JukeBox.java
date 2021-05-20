package com.DuelingFates.Music;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;

public class JukeBox {

	private static HashMap<String, Clip> clips;						//Clip: beépített audioformátum, HasMap: store items in "key/value" pairs,

	public static void init() {

		clips = new HashMap<>();

	}
	
	public static void load(String location, String name) {

		if(clips.get(name) != null) return;							//Ha már létezik

		Clip clip;													//Clip létrehozása
		try {			
			AudioInputStream audio =								//fájlok beolvasása
				AudioSystem.getAudioInputStream(
					JukeBox.class.getResourceAsStream(location)
				);
			AudioFormat baseFormat = audio.getFormat();				//Audióformátum
			AudioFormat decodeFormat = new AudioFormat(				//dekódolás formátuma
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,				//Sztereó miatt
				baseFormat.getSampleRate(),
				false
			);

			AudioInputStream decode = AudioSystem.getAudioInputStream(decodeFormat, audio);

			clip = AudioSystem.getClip();
			clip.open(decode);

			clips.put(name, clip);									//elhelyezése a listában

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void play(String name) {

		Clip clip = clips.get(name);					//Clip megkeresése

		if(clip == null){ 								//Ha nincs ilyen
			return;
		}

		if(clip.isRunning()){							//Ha fut, megállítjuk

			clip.stop();

		}

		clip.setFramePosition(0);						//Beállítjuk a kezdőframet

		while(!clip.isRunning()){ 						//Ha nem megy, indítjuk

			clip.start();

		}
	}
	
	public static void stop(String name) {

		if(clips.get(name) == null) {
			return;                   					 //Ha nincs ilyen klip
		}

		if(clips.get(name).isRunning()){

			clips.get(name).stop();	  					 //ha éppen fut, leállítjuk

		}
	}

	public static void loop(String name, int start, int end)	 {

		stop(name);											//megállítjuk
		clips.get(name).setLoopPoints(start, end);			//beállítjuk a loop kezdetét és végét
		clips.get(name).setFramePosition(0);				//éppen hol állunk
		clips.get(name).loop(Clip.LOOP_CONTINUOUSLY);		//végtelenszer loopol

	}

	public static int getFrames(String name){ 				//Clip hosszának lekérdezése

		return clips.get(name).getFrameLength();

	}

}