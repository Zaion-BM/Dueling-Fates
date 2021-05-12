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
            2, 2, 14, 2
    };
    private final int[] FRAMEWIDTHS_P = {
            32, 32, 32, 32
    };
    private final int[] FRAMEHEIGHTS_P = {
            44, 44, 44, 44
    };
    private final int[] SPRITEDELAYS_P = {
            -1, 2, 1, 6
    };

    BufferedImage possessedWalkImg;
    BufferedImage possessedStandImg;
    BufferedImage possessedJumpImg;

    private BufferedImage[] walkingSprite;
    private BufferedImage[] jumpingAndFallingSprite;
    private BufferedImage[] standingSprite;


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

                possessedJumpImg = ImageIO.read(new File("DuelingFates/Sources/char_PossessedArmor/PossessedArmor_jumpingAndFalling2.png"));
                possessedStandImg = ImageIO.read(new File("DuelingFates/Sources/char_PossessedArmor/PossessedArmor_standing2.png"));
                possessedWalkImg = ImageIO.read(new File("DuelingFates/Sources/char_PossessedArmor/PossessedArmor_walking.png"));


            walkingSprite = new BufferedImage[14];
            //461 pixel, 1 pixel between each sprite, 461-13=448 / 14 = 32
            for (int i = 0; i < walkingSprite.length; i++) {
                walkingSprite[i] = possessedWalkImg.getSubimage(
                            i * (player.spriteWidth+1),
                            0,
                            player.spriteWidth,
                            player.spriteHeight
                    );
                }

            jumpingAndFallingSprite = new BufferedImage[2];
            for (int i = 0; i < jumpingAndFallingSprite.length; i++) {
                jumpingAndFallingSprite[i] = possessedJumpImg.getSubimage(
                        i * (player.spriteWidth+1),
                        0,
                        player.spriteWidth,
                        player.spriteHeight
                );
            }

            standingSprite = new BufferedImage[2];
            for (int i = 0; i < standingSprite.length; i++) {
                standingSprite[i] = possessedStandImg.getSubimage(
                        i * (player.spriteWidth+1),
                        0,
                        player.spriteWidth,
                        player.spriteHeight
                );
            }

            } catch (Exception e ){
                e.printStackTrace();
            }

            setAnimationPossessed(Player.IDLE,player);
            player.animation.setFrames(standingSprite);



        }

    }


    //TODO POSSESSED ANIMATION 2.0 - WITHOUT WEAPON
    private void setAnimationPossessed(int i, Player player) {
        player.currentAction = i;
        player.animation.setDelay(SPRITEDELAYS_P[player.currentAction]);
        player.spriteWidth = FRAMEWIDTHS_P[player.currentAction];
        player.spriteHeight = FRAMEHEIGHTS_P[player.currentAction];

    }

    public void updateAnimationPossessed(Player player){


        if(player.blinkRed) {
            player.blinkCount++;
            if(player.blinkCount > 120) {
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
        if(player.playerHealth == 0) {
            if (player.currentAction != Player.DEAD) {
                setAnimation(Player.DEAD, player);
                player.animation.setFrames(jumpingAndFallingSprite);
            }
        }
        else if(player.shooting){
            if(player.currentAction!=Player.SHOOTING){
                //setAnimation(Player.SHOOTING, player);
                //player.animation.setFrames(jumpingAndFallingSprite);
            }


        }
        else if(player.deltaY<0){                                               //jumping
            if(player.currentAction != Player.JUMPINGANDFALLING){
                player.animation.setFrames(jumpingAndFallingSprite);
               setAnimationPossessed(Player.JUMPINGANDFALLING,player);
            }
        }
        else if(player.deltaY>0){                                               //falling
            if(player.currentAction != Player.JUMPINGANDFALLING){
                player.animation.setFrames(jumpingAndFallingSprite);
                setAnimationPossessed(Player.JUMPINGANDFALLING,player);
            }

        }
        else if(player.left || player.right){                                   //running
            if(player.currentAction!=Player.RUNNING){
                setAnimationPossessed(Player.RUNNING,player);
                player.animation.setFrames(walkingSprite);
            }
        }
        else if(player.currentAction!=Player.IDLE){                             //standing
            setAnimationPossessed(Player.IDLE,player);
            player.animation.setFrames(standingSprite);
        }

        player.animation.update();                                              //update animation of player

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
        else if(player.deltaY<0){                                      //jumping
            if(player.currentAction != Player.JUMPINGANDFALLING){
                setAnimation(Player.JUMPINGANDFALLING,player);
            }
        }
        else if(player.deltaY>0){                                      //falling
            if(player.currentAction != Player.JUMPINGANDFALLING){
                setAnimation(Player.JUMPINGANDFALLING,player);
            }

        }
        else if(player.left || player.right){                                 //running
            if(player.currentAction!=Player.RUNNING){
                setAnimation(Player.RUNNING,player);
            }
        }
        else if(player.currentAction!=Player.IDLE){                           //standing
            setAnimation(Player.IDLE,player);
        }
        player.animation.update();                                     //update animation of player

    }

}
