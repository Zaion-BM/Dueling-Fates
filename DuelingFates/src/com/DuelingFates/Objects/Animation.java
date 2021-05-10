package com.DuelingFates.Objects;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;                             //képkockák
    private int currentFrame;                                   //aktuális frame
    private int numFrames;                                      //képkockák száma

    private int count;                                          //update-ek számolása
    private int delay;                                          //képek közti késés

    private int timesPlayed;

    public Animation() {
        timesPlayed = 0;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = frames.length;
    }

    public void setDelay(int i) { delay = i; }
    public void setFrame(int i) { currentFrame = i; }
    public void setNumFrames(int i) { numFrames = i; }

    public void update() {

        if(delay == -1) return;                                 //ha állunk nem csinálunk semmit

        count++;

        if(count == delay) {                                    //ha letelt az idő, akkor továbblépünk
            currentFrame++;
            count = 0;                                          //nullázzuk a számlálót
        }
        if(currentFrame == numFrames) {                         //ha a kép végére értünk
            currentFrame = 0;                                   //akkor előről kezdjük
            timesPlayed++;
        }

    }

    public int getFrame() { return currentFrame; }
    public int getCount() { return count; }
    public BufferedImage getImage() { return frames[currentFrame]; }
    public boolean hasPlayedOnce() { return timesPlayed > 0; }
    public boolean hasPlayed(int i) { return timesPlayed == i; }

}
