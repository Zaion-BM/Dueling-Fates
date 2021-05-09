package com.DuelingFates.Objects;
import com.DuelingFates.TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Player extends GameObject{
    //Player parameters
    private String playerName;
    private int playerHealth;
    private int maxHealth;
    private int playerScore;
    private Weapon playerWeapon;
    private int playerAmmoQty;
    private boolean dead;
    private boolean blinkRed;
    private long blinkCount;
    private boolean shooting;
    //Player animation actions
    private static final int IDLE=0;
    private static final int RUNNING=2;
    private static final int SHOOTING=3;
    private static final int JUMPINGANDFALLING=1;
    //private static final int JUMPING = 3;
   // private static final int FALLING = 4; /TODO: jump és falling sprite-ok külön
    private static final int DEAD=4;
    //projectile
    private Rectangle ar;

    //Player's sprite
    // animations
    private ArrayList<BufferedImage[]> sprites;
    private final int[] NUMFRAMES = {
            2, 2, 14, 1
    };
    private final int[] FRAMEWIDTHS = {
            41, 39, 43, 46
    };
    private final int[] FRAMEHEIGHTS = {
            44, 44, 44, 44
    };
    private final int[] SPRITEDELAYS = {
            -1, 3, 2, 6
    };
    // public static enum playerCharacter{PIRATE_DECKHAND, POSSESSED_ARMOR};
    /*
     * Implementation of constructors
     * */
    public Player(TileMap tileMap) {      //TODO : ahol a playername a MainProcess.playerNameTemp
        super(tileMap);
        //projectile TODO: ezt nem biztos hogy ide kéne
        ar = new Rectangle(0, 0, 0, 0);
        ar.width = 10;
        ar.height = 2;
        //Player sprite dimensions
        spriteWidth = 32;
        spriteHeight = 44;
        //Physical dimensions
        objectWidth = 30;
        objectHeight = 40;
        //Specify movement parameters //TODO: Paraméterek tesztelése
        moveSpeed = (float) 0.3;
        maxSpeed = (float) 1.6;
        stopSpeed = (float) 0.4;
        fallSpeed = (float) 0.15;
        maxFallSpeed = (float) 4.0;
        jumpStart = (float) -4.8;
        stopJumpSpeed = (float) 0.3;
        facingRight = true;
        //gravity=(float)1.8;  //TODO: tutorialban más volt, még nem látom át hogy lesz ez számolva

        //Specify player parameters
        setPlayerMaxHealth(100);
        setPlayerHealth(100);
        setPlayerScore(0);
        setPosition(800, 100);
        //TODO: playerCharacter beállítása, az animation ez alapján történik, mert a spriteok is ott töltődnek be

        try {

            BufferedImage spritesheet = ImageIO.read(new File(
                            "DuelingFates/Sources/char_PirateDeckhand/PlayerSprite_gun.png"
                    )
            );


            int count = 0;
            sprites = new ArrayList<BufferedImage[]>();
            for (int i = 0; i < NUMFRAMES.length; i++) {
                BufferedImage[] bi = new BufferedImage[NUMFRAMES[i]];
                for (int j = 0; j < NUMFRAMES[i]; j++) {
                    bi[j] = spritesheet.getSubimage(
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

        setAnimation(IDLE);
    }
    /*
     * Implementation of getters and setters
     * */
    /*
     *Get player's name
     *@return String: Player's name
     * */
    public String getPlayerName(){
        return playerName;
    }
    /*
     *Set player's name
     *@param String: Add your name
     *@return Nothing
     * */
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
    /*
     *Get player's health
     *@return int: Player's health
     * */
    public int getPlayerHealth(){
        return playerHealth;
    }
    /*
     *Set player's health
     *@param int: Add number (health) between [0,100]
     *@return Nothing
     * */
    public void setPlayerHealth(int playerHealth){
        this.playerHealth = playerHealth;
    }
    /*
     *Get player's score
     *@return int: Player's score
     * */
    public int getPlayerScore(){
        return playerScore;
    }
    /*
     *Set player's score
     *@param int: Add number
     *@return Nothing
     * */
    public void setPlayerScore(int playerScore){ this.playerScore = playerScore; }
    /*
     *Get player's weapon
     *@return Weapon: Player's weapon (object)
     * */
    public Weapon getPlayerWeapon(){
        return playerWeapon;
    }
    /*
     *Add weapon to player
     *@param Weapon: Add Weapon (object)
     *@return Nothing
     * */
    public void setPlayerWeapon(Weapon playerWeapon){
        this.playerWeapon = playerWeapon;
    }
    /*
     *Get player's ammunition quantity
     *@return int:  player's ammunition quantity
     * */
    public int getPlayerAmmoQty(){
        return playerAmmoQty;
    }
    /*
     *Set player's ammunition quantity
     *@param int: Add number (ammunition quantity) between [0,10]
     *@return Nothing
     * */
    public void setPlayerAmmoQty(int playerAmmoQty){
        this.playerAmmoQty = playerAmmoQty;
    }

    public void setPlayerMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }
    /*
     * Implementation of Player's movements and actions
     * */
    public void setShooting(){
        shooting=true;
    }
    public void setLeft(boolean b){ left=b; }
    public void setRight(boolean b){ right=b; }
    public void setJumping(boolean b){ jumping=b; }

    //TODO: damage számolása? ,
    public void hit(int damage) {
        if(blinkRed) return;
        //JukeBox.play("playerhit");
        stop();
        playerHealth -= damage;
        if(playerHealth < 0) playerHealth = 0;
        blinkRed = true;
        blinkCount = 0;
        if(facingRight) deltaX = -1;
        else deltaX = 1;
        deltaY = -3;
        //knockback = true;
        falling = true;
        jumping = false;
    }
    //TODO: játék reset, még át kell alakítani úgy , hogy a meghalt játékos újraéledjen a pályán
    public void reset() {
        playerHealth = maxHealth;
        facingRight = true;
        currentAction = -1;
        stop();
    }

    public void stop() {
        left = right = blinkRed = jumping = false;
    }
    /*
     *Calculate the Player's next position
     * */
    private void getNextPosition(){
        /*
         *If we are moving
         * */
        if(left){//Moving left
            deltaX-=moveSpeed;
            if(deltaX< -maxSpeed) {
                deltaX = -maxSpeed;
            }
        }
        else if(right){//Moving right
            deltaX+=moveSpeed;
            if(deltaX>maxSpeed){
                deltaX=maxSpeed;
            }
        }
        else{//Standing still
            if(deltaX>0){
                deltaX-=stopSpeed;
                if(deltaX<0){
                    deltaX=0;
                }
            }
            else if(deltaX<0){
                deltaX+=stopSpeed;
                if(deltaX>0){
                    deltaX=0;
                }
            }
        }
        /*
         *While we are shooting, we can not move, expect in air
         * *///TODO: Tesztelés szükséges, hogy így jó lesz-e vagy lehessen mozgás közben lőni is
        if(currentAction==SHOOTING && !(jumping||falling)){
            deltaX=0;
        }
        /*
         *If we are jumping
         * */
        if(jumping && !falling){
            deltaY=jumpStart;
            falling=true;
        }
        /*
         *If we are falling
         * */
        if(falling){
            deltaY+=fallSpeed;
            if(deltaY>0) jumping=false;
            if(deltaY<0 && !jumping) deltaY+=stopJumpSpeed; //prevents: longer you hold jumpbutton higher you jump
            if(deltaY>maxFallSpeed) deltaY=maxFallSpeed;
        }
    }
    private void setAnimation(int i) {
        currentAction = i;
        animation.setFrames(sprites.get(currentAction));
        animation.setDelay(SPRITEDELAYS[currentAction]);
        spriteWidth = FRAMEWIDTHS[currentAction];
        spriteHeight = FRAMEHEIGHTS[currentAction];
    }
    /*
     *Update Player's position and actions
     * */
    public void update(){

        //TODO: idő kiiratása képernyőre? ezt lehet nem itt kéne...
        //time++;
        /*
         * Update player's position
         * */
        getNextPosition();
        checkTileMapCollision();
        setPosition(tempX,tempY);
        //TODO: ez mire való?
        //if(deltaX == 0) x = (int)x;
        /*
         * Set player's animation
         * */
        // check done blinking
        if(blinkRed) {
            blinkCount++;
            if(blinkCount > 120) {
                blinkRed = false;
            }
        }
        // check attack finished
        if(currentAction == SHOOTING) {
            if(animation.hasPlayedOnce()) {
                shooting = false;
            }
        }
        // check attack TODO: player2 -őt enemynek állítani és checckolni hogy a lövedék eltalálta e
       /* //Enemy enemy=player2;
        if(currentAction == SHOOTING &&
                animation.getFrame() == 3 && animation.getCount() == 0) {
            if(enemy.intersects(ar)) {
                enemy.hit(damage);
            }
        }
        //TODO: check if got hit by enemy player
        if(!enemy.isDead() && intersects(enemy.getProjectilePosition())) {
            hit(enemy.getDamage());
        }
        */


        // set animation, ordered by priority
        if(playerHealth == 0) {
            if(currentAction != DEAD) {
                setAnimation(DEAD);
            }
        }
        else if(shooting){//shooting
            if(currentAction!=SHOOTING){
                setAnimation(SHOOTING);
                ar.y = (int)y - 6;
                if(facingRight) ar.x = (int)x + 10;
                else ar.x = (int)x - 40;
            }
           /* else { TODO: Projectile-ra ugyanezt megírni
                if(animation.getFrame() == 4 && animation.getCount() == 0) {
                    for(int c = 0; c < 3; c++) {
                        if(facingRight)
                            energyParticles.add(
                                    new EnergyParticle(
                                            tileMap,
                                            ar.x + ar.width - 4,
                                            ar.y + ar.height / 2,
                                            EnergyParticle.RIGHT));
                        else
                            energyParticles.add(
                                    new EnergyParticle(
                                            tileMap,
                                            ar.x + 4,
                                            ar.y + ar.height / 2,
                                            EnergyParticle.LEFT));
                    }}
            }*/


        }
        else if(deltaY<0){//jumping
            if(currentAction != JUMPINGANDFALLING){
                setAnimation(JUMPINGANDFALLING);
            }
        }
        else if(deltaY>0){//falling
            if(currentAction != JUMPINGANDFALLING){
                setAnimation(JUMPINGANDFALLING);
            }

        }
        else if(left || right){//running
            if(currentAction!=RUNNING){
                setAnimation(RUNNING);
            }
        }
        else if(currentAction!=IDLE){//standing
                setAnimation(IDLE);
        }
        //update animation of player
        animation.update();
        //Set direction of facing
        if(!shooting){
            if(right) facingRight=true;
            if(left) facingRight=false;
        }
    }
    /*
     *Draw Player's animation
     * */
    public void draw(Graphics2D g){
        if(blinkRed){//If we get shot, we are blinking red
                if(blinkCount % 10 < 5) return;
        }
        super.draw(g);
    }
}
