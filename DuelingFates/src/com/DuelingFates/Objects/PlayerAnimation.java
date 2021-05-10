package com.DuelingFates.Objects;

import java.awt.image.BufferedImage;

public class PlayerAnimation {

    private BufferedImage[] runningSprite;
    private BufferedImage[] jumpingAndFallingSprite;
    private BufferedImage[] standingSprite;

    public PlayerAnimation(Player player){
        //az playerCharacter függvényében más sprite-ot töltünk be

    }

    public void shooting(){
        //lövés fegyver megjelenítés, eltüntetés, ammo csökkentés

    }

    //ha futunk
    public void running(int direction){
        //input event??
        //left: 0 right: 1
        switch(direction) {
            case 0:
                //runningSprite left
                break;
            case 1:
                //runningSprite right
        }
    }

    //ha állunk
    public void standing(Player player){
            //standingSprite
    }

    //ha ugrunk vagy esünk
    public void jumpingAndFalling(Player player){
        //jumpingAndFallingSprite
    }
    public void damageAnimation(){
        //vörös tinta ha projectile-lal találkozunk


    }

}
