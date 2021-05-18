package com.DuelingFates.Music;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;

public class JukeBox {

	//NEM SAJÁT KÓD, csak felhasználtuk

	private static HashMap<String, Clip> clips;						//Clip: audioformátum
	private static int gap;
	private static final boolean mute = false;
	
	public static void init() {
		clips = new HashMap<>();
		gap = 0;
	}
	
	public static void load(String s, String n) {
		if(clips.get(n) != null) return;
		Clip clip;
		try {			
			AudioInputStream ais =									//fájlok beolvasása
				AudioSystem.getAudioInputStream(
					JukeBox.class.getResourceAsStream(s)
				);
			AudioFormat baseFormat = ais.getFormat();				//Audióformátum
			AudioFormat decodeFormat = new AudioFormat(				//dekódolás
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			clips.put(n, clip);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void play(String s) {
		play(s, gap);
	}
	
	public static void play(String s, int i) {
		if(mute) return;
		Clip c = clips.get(s);
		if(c == null) return;
		if(c.isRunning()) c.stop();
		c.setFramePosition(i);
		while(!c.isRunning()) c.start();
	}
	
	public static void stop(String s) {
		if(clips.get(s) == null) return;
		if(clips.get(s).isRunning()) clips.get(s).stop();
	}

	public static void loop(String s, int frame, int start, int end) {
		stop(s);
		if(mute) return;
		clips.get(s).setLoopPoints(start, end);
		clips.get(s).setFramePosition(frame);
		clips.get(s).loop(Clip.LOOP_CONTINUOUSLY);
	}

	public static int getFrames(String s) { return clips.get(s).getFrameLength(); }

	public static void loop(String s, int start, int end) {
		loop(s, gap, start, end);
	}
	
}