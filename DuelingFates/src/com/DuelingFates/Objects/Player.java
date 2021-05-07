package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;

public class Player extends GameObject{

    //Player parameters
    private String playerName;
    private int playerHealth;
    private int playerScore;
    private Weapon playerWeapon;
    private int playerAmmoQty;
    private boolean dead;
    private boolean blinkRed;
    private boolean blinkTimer;
    private boolean shooting;
    //Player animation actions
    private static final int IDLE=0;
    private static final int RUNNING=1;
    private static final int JUMPINGANDFALLING=2;
    private static final int SHOOTING=3;

    //public static enum playerCharacter{PIRATE_DECKHAND, POSSESSED_ARMOR};

    private TileMap tileMap;        //amin a collisiont érzékeljük

    public Player(TileMap tileMap){      //ahol a playername a MainProcess.playerNameTemp
        super(tileMap);
        //Player sprite dimensions
        spriteWidth=40;
        spriteHeight=80;
        //Physical dimensions
        objectWidth=30;
        objectHeight=70;
        //Specify movement parameters
        moveSpeed=(float)0.3;
        maxSpeed=(float)1.6;
        stopSpeed=(float)0.4;
        fallSpeed=(float)0.15;
        maxFallSpeed=(float)4.0;
        jumpStart=(float)-4.8;
        stopJumpSpeed=(float)0.3;
        facingRight=true;
        //gravity=(float)5;  //tutorialban más volt, még nem látom át hogy lesz ez számolva

        //Specify player parameters
        setPlayerHealth(100);
        setPlayerScore(0);

        //TODO: playerCharacter beállítása, az animation ez alapján történik, mert a spriteok is ott töltődnek be


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
    /*
    *
    * */
    public void setShooting(){
        shooting=true;
    }
    /*
     *
     * */
    public void update(){
        /*
        * Update player's position
        * */
        //getNextPosition();
        checkTileMapCollision();
        setPosition(tempX,tempY);
        /*
         * Set player's animation
         * */
        if(shooting){//shooting
            if(currentAction!=SHOOTING){
                currentAction=SHOOTING;
               // playerAnimation.setFrames(sprites.get(SHOOTING));
               // playerAnimation.setDelay(50);
                spriteWidth=60;
            }
        }
        else if(deltaY>0){//falling
            if(currentAction != JUMPINGANDFALLING){
                currentAction=JUMPINGANDFALLING;
                //playerAnimation.setFrames(sprites.get(JUMPINGANDFALLING));
               // playerAnimation.setDelay(100);
                spriteWidth=40;
            }

        }
        else if(deltaY<0){//jumping
            if(currentAction != JUMPINGANDFALLING){
                currentAction=JUMPINGANDFALLING;
               // playerAnimation.setFrames(sprites.get(JUMPINGANDFALLING));
              //  playerAnimation.setDelay(-1);
                spriteWidth=40;
            }

        }
        else if(left || right){//running
            if(currentAction!=RUNNING){
                currentAction=RUNNING;
               // playerAnimation.setFrames(sprites.get(RUNNING));
              //  playerAnimation.setDelay(40);
                spriteWidth=40;
            }
        }
        else{//standing
            if(currentAction!=IDLE){
                currentAction=IDLE;
              //  playerAnimation.setFrames(sprites.get(IDLE));
             //   playerAnimation.setDelay(400);
                spriteWidth=40;
            }
        }
        //update animation of player
       // playerAnimation.update();
        //Set direction of facing
        if(currentAction!=SHOOTING){
            if(right) facingRight=true;
            if(left) facingRight=false;
        }
    }
    /*
     *
     * */
    /*
     *
     * */
    /*
     *
     * */

}
