package com.DuelingFates.Objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class PlayerAnimation {

    //Player's sprite animations
    private ArrayList<BufferedImage[]> sprites;
    private final int[] NUMFRAMES = {
            2, 2, 14, 1, 2, 1
    };
    private final int[] FRAMEWIDTHS = {
            41, 39, 43, 46, 41, 41
    };
    private final int[] FRAMEHEIGHTS = {
            44, 44, 44, 44, 44, 44
    };
    private final int[] SPRITEDELAYS = {
            -1, 3, 1, 6, 3, 10
    };

    //Posessed
    private final int[] NUMFRAMES_P = {
            2, 2, 14, 1, 2, 1
    };
    private final int[] FRAMEWIDTHS_P = {
            32, 32, 33, 32, 32, 32
    };
    private final int[] FRAMEHEIGHTS_P = {
            44, 44, 44, 44, 44, 44
    };
    private final int[] SPRITEDELAYS_P = {
            -1, 3, 1, 6, 3, 10
    };

    public PlayerAnimation(Player player){

        //a playerCharacter függvényében más sprite-ot töltünk be
        if(player.getPlayerCharacter().equals(Player.PIRATE)) {
            try {

                BufferedImage spriteSheet = ImageIO.read(new File("DuelingFates/Sources/char_PirateDeckhand/PlayerSprite_gun_v2.png"));

                int count = 0;
                sprites = new ArrayList<>();

                for (int i = 0; i < NUMFRAMES.length; i++) {
                    BufferedImage[] bi = new BufferedImage[NUMFRAMES[i]];
                    for (int j = 0; j < NUMFRAMES[i]; j++) {
                        bi[j] = spriteSheet.getSubimage(
                                j * FRAMEWIDTHS[i],
                                count,
                                FRAMEWIDTHS[i],
                                FRAMEHEIGHTS[i]
                        );
                    }
                    sprites.add(bi);
                    count += FRAMEHEIGHTS[i];
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            setAnimation(Player.IDLE,player);

        }

        if(player.getPlayerCharacter().equals(Player.POSSESSED)) {

            try {

                BufferedImage spriteSheet = ImageIO.read(new File("DuelingFates/Sources/char_PossessedArmor/PossessedArmor.png"));

                int count = 0;
                sprites = new ArrayList<>();

                for (int i = 0; i < NUMFRAMES_P.length; i++) {
                    BufferedImage[] bi = new BufferedImage[NUMFRAMES_P[i]];
                    for (int j = 0; j < NUMFRAMES_P[i]; j++) {
                        bi[j] = spriteSheet.getSubimage(
                                j * FRAMEWIDTHS_P[i],
                                count,
                                FRAMEWIDTHS_P[i],
                                FRAMEWIDTHS_P[i]
                        );
                    }
                    sprites.add(bi);
                    count += FRAMEWIDTHS_P[i];
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            setAnimation(Player.IDLE,player);

        }

    }

    private void setAnimationPossessed(int i, Player player) {
        player.currentAction = i;
        player.animation.setFrames(sprites.get(player.currentAction));
        player.animation.setDelay(SPRITEDELAYS_P[player.currentAction]);
        player.spriteWidth = FRAMEWIDTHS_P[player.currentAction];
        player.spriteHeight = FRAMEHEIGHTS_P[player.currentAction];
    }

    //TODO POSSESSED ANIMATION 2.0 - WITHOUT WEAPON MÉG NEM MEGY TRY AT OWN RISK
    public void updateAnimationPossessed(Player player){

        //check blinking finished
        if(player.blinkRed) {
            player.blinkCount++;
            if (player.blinkCount > 30) {
                player.blinkRed = false;
            }
        }

        // check attack finished
        if(player.currentAction == Player.SHOOTING) {
            if(player.animation.hasPlayedOnce()) {
                player.shooting = false;
            }
        }

        // set animation, ordered by priority
        if(player.blinkRed && !player.dead) {
            if (player.currentAction != Player.BLINKING) {
                setAnimationPossessed(Player.BLINKING, player);
            }
        }
        else if(player.playerHealth == 0) {
            if (player.currentAction != Player.DEAD) {
                setAnimationPossessed(Player.DEAD, player);
            }
        }
        else if(player.shooting){
            if(player.currentAction!=Player.SHOOTING){
                setAnimationPossessed(Player.SHOOTING, player);
            }

        }
        else if(player.deltaY<0){                                                   //jumping
            if(player.currentAction != Player.JUMPINGANDFALLING){
                setAnimationPossessed(Player.JUMPINGANDFALLING,player);
            }
        }
        else if(player.deltaY>0){                                                   //falling
            if(player.currentAction != Player.JUMPINGANDFALLING){
                setAnimationPossessed(Player.JUMPINGANDFALLING,player);
            }

        }
        else if(player.left || player.right){                                       //running
            if(player.currentAction!=Player.RUNNING){
                setAnimationPossessed(Player.RUNNING,player);
            }
        }
        else if(player.currentAction!=Player.IDLE && !player.keyUpPressed){         //TODO test standing
            setAnimationPossessed(Player.IDLE,player);
        }
        player.animation.update();                                                  //update animation of player

    }

    private void setAnimation(int i, Player player) {
        player.currentAction = i;
        player.animation.setFrames(sprites.get(player.currentAction));
        player.animation.setDelay(SPRITEDELAYS[player.currentAction]);
        player.spriteWidth = FRAMEWIDTHS[player.currentAction];
        player.spriteHeight = FRAMEHEIGHTS[player.currentAction];
    }

    public void updateAnimation(Player player){

        //check blinking finished
        if(player.blinkRed) {
                player.blinkCount++;
                if (player.blinkCount > 30) {
                    player.blinkRed = false;
                }
        }

        // check attack finished
        if(player.currentAction == Player.SHOOTING) {
            if(player.animation.hasPlayedOnce()) {
                player.shooting = false;
            }
        }

        // set animation, ordered by priority
        if(player.blinkRed && !player.dead) {
            if (player.currentAction != Player.BLINKING) {
                setAnimation(Player.BLINKING, player);
            }
        }
        else if(player.playerHealth == 0) {
            if (player.currentAction != Player.DEAD) {
                setAnimation(Player.DEAD, player);
            }
        }
        else if(player.shooting){
            if(player.currentAction!=Player.SHOOTING){
                setAnimation(Player.SHOOTING, player);
            }

        }
        else if(player.deltaY<0){                                               //jumping
            if(player.currentAction != Player.JUMPINGANDFALLING){
                setAnimation(Player.JUMPINGANDFALLING,player);
            }
        }
        else if(player.deltaY>0){                                               //falling
            if(player.currentAction != Player.JUMPINGANDFALLING){
                setAnimation(Player.JUMPINGANDFALLING,player);
            }

        }
        else if(player.left || player.right){                                   //running
            if(player.currentAction!=Player.RUNNING){
                setAnimation(Player.RUNNING,player);
            }
        }
        else if(player.currentAction!=Player.IDLE && !player.keyUpPressed){     //TODO test standing
            setAnimation(Player.IDLE,player);
        }
        player.animation.update();                                              //update animation of player

    }

}
