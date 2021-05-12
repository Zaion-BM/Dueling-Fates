package com.DuelingFates.Objects;
import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.Consumable.HealthPotion;
import com.DuelingFates.TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Player extends GameObject implements KeyListener {

    //Player parameters - some are public because of animation
    public static String PIRATE = "PirateDeckhand";
    public static String POSSESSED = "PossessedArmor";

    private String playerCharacter;
    private String playerName;

    public int playerHealth;
    private int maxHealth;
    private int playerScore;
    private Weapon playerWeapon;
    public int playerAmmoQty;
    public boolean dead;
    public boolean blinkRed;
    public long blinkCount;
    public boolean shooting;
    public ArrayList<Projectile> bullets;
    public boolean removed; //TODO: lehet ezt gameobject-re kéne és akkor használható a cosumablehez


    //Player actions
    public static final int IDLE                = 0;
    public static final int RUNNING             = 2;
    public static final int SHOOTING            = 3;
    public static final int JUMPINGANDFALLING   = 1;
    public static final int BLINKING            = 4;
    public static final int DEAD                = 5;

    //projectile
    public Rectangle ar;

    //ZB: Implementation of constructors
    public Player(TileMap tileMap) {

        super(tileMap);

        removed=false;
        dead=false;
        playerName = MainProcess.getPlayerNameTemp();
        playerCharacter = MainProcess.getCharacterTemp();

        //projectile                                        TODO: ezt nem biztos hogy ide kéne Dave: hát nem XD
        ar = new Rectangle(0, 0, 0, 0);
        ar.width = 10;
        ar.height = 2;
        bullets = new ArrayList<Projectile>();
        playerAmmoQty = 30;

        //Player sprite dimensions
        spriteWidth = 32;
        spriteHeight = 44;

        //Physical dimensions
        objectWidth = 30;
        objectHeight = 40;

        //Specify movement parameters //TODO: Paraméterek tesztelése
        moveSpeed = (float) 1;        //0.3               //mozgás
        maxSpeed = (float) 5;         //1.6               //max mozgási sebessség
        stopSpeed = (float) 0.7;        //0.4               //mennyire csúszik a karakter

        fallSpeed = (float) 1;        //0.15                //esés
        maxFallSpeed = (float) 6.0;     //4.0               //max esési sebesség

        jumpStart = (float) -17;       //-4.8               //jump boost
        stopJumpSpeed = (float) 0.3;    //0.3               //lassulás a tetején
        facingRight = true;
        //gravity=(float)1.8;  //TODO: tutorialban más volt, még nem látom át hogy lesz ez számolva

        //Specify player parameters
        setPlayerMaxHealth(100);
        setPlayerHealth(50);
        setPlayerScore(0);
        setPosition(300, 400);

    }

    public ArrayList<Projectile> getProjectile(){
        return bullets;
    }

    //ZB: Implementation of getters and setters
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
        if (this.playerHealth > 100){
            this.playerHealth = 100;
        }
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

    public String getPlayerCharacter(){
        return this.playerCharacter;
    }

    /*
     * Implementation of Player's movements and actions
     * */
    public void setShooting(){
       if(playerAmmoQty>0)  shooting=true;
    }
    public void setLeft(boolean b){ left=b; }
    public void setRight(boolean b){ right=b; }
    public void setJumping(boolean b){ jumping=b; }

    //TODO: damage számolása
    public void hit(int damage) {
        if(blinkRed || dead) return;
        //JukeBox.play("playerhit");            //TODO max erre lesz idő, meg valami háttérzenének, ez 2 hangfájl
        stop();
        playerScore+=10;
        playerHealth -= damage;
        if(playerHealth < 0) {
            playerScore+=100;
            System.out.println("Enemy is dead");
            playerHealth = 0;
            dead=true;
        }
        blinkRed = true;

        blinkCount = 0;

        if(facingRight) deltaX = -1;
        else deltaX = 1;

        deltaY = -3;

        //knockback = true;
        //falling = true;
        System.out.println("Hit");
        jumping = false;
    }
    public boolean isDead(){
        return dead;
    }
    public int getDamage(){
        return playerWeapon.getWeaponDmg();
    }

    //check attack TODO: TESZTELÉS
    public void checkAttack(Player player){
        for(int i=0; i<bullets.size();i++) {
            if (bullets.get(i).objectIntersection(player)) {
                player.hit(getDamage());
                bullets.get(i).setHit();
               // blinkRed=true;  TODO: összes sprite esetén kell egy red variáció, hogy esés közben is sebződhessen az enemy
                break;
            }
        }
    }

    //TODO: játék reset, még át kell alakítani úgy , hogy a meghalt játékos újraéledjen a pályán
    public void reset() {
        playerHealth = maxHealth;
        facingRight = true;
        currentAction = -1;
        stop();
    }

    public void stop() {
        left = right = blinkRed = jumping = dead= removed= false;
    }

    public void respawn(){
        reset();
        setPosition(400, 300);
    }
    public void removePlayer() {

        System.out.println("Player removed");

        if(currentAction == DEAD && animation.hasPlayedOnce()) {
            respawn();
            //setPosition(9999, 9999);
            //removed = true;
        }
    }
    //ZB: Calculate the Player's next position
    private void getNextPosition(){

        //TODO: Ha esünk egy ugrásból és balra indulunk, akkor lezuhanunk. Ha nyomva tartjuk a fel nyilat akkor lassabban esünk
        //ZB: If we are moving
        if(left){                                   //Moving left
            deltaX-=moveSpeed;
            if(deltaX< -maxSpeed) {
                deltaX = -maxSpeed;
            }
        }
        else if(right){                             //Moving right
            deltaX+=moveSpeed;
            if(deltaX>maxSpeed){
                deltaX=maxSpeed;
            }
        }
        else{                                       //Standing still
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

        //ZB: While we are shooting, we can not move, expect in air
        //TODO: Tesztelés szükséges, hogy így jó lesz-e vagy lehessen mozgás közben lőni is
        //TODO: DAVE: szerintem taktikusabb lesz ha mozgás közben nem tudsz lőni, mert célozni kell, de a másik is logikus
        if(currentAction==SHOOTING && !(jumping||falling)){
            deltaX=0;
        }

        //ZB: If we are jumping
        if(jumping && !falling){
            deltaY=jumpStart;
            falling=true;
        }

        //ZB: If we are falling
        if(falling){
            deltaY+=fallSpeed;
            if(deltaY>0) jumping=false;
            if(deltaY<0 && !jumping) deltaY+=stopJumpSpeed; //prevents: longer you hold jumpbutton higher you jump
            if(deltaY>maxFallSpeed) deltaY=maxFallSpeed;
        }

    }

    //ZB: Update Player's position and actions
    public void update(){

        /*
         * Update player's position
         * */
        getNextPosition();
        checkTileMapCollision();
        setPosition(tempX,tempY);

        //projectiles
            if(shooting && currentAction!=SHOOTING){
                if(playerAmmoQty>0){
                    playerAmmoQty--;
                    Projectile p=new Projectile(tileMap,facingRight);
                    p.setPosition(x,y);
                    bullets.add(p);
                }
            }

        //update projectiles
        for(int i=0; i<bullets.size();i++){
            bullets.get(i).update();
            if(bullets.get(i).shouldRemove()){
                bullets.remove(i);
                i--;
            }
        }

        //TODO: teszt  szükséges
        if(y>tileMap.getMapHeight()+objectHeight*5){
            respawn();
        }
        if(dead){
            removePlayer();
        }


        if(shooting){
            if(currentAction!=SHOOTING){
                ar.y = (int)y - 6;
                if(facingRight) ar.x = (int)x + 10;
                else ar.x = (int)x - 40;
            }


        }

        //Set direction of facing
        if(!shooting){
            if(right) facingRight=true;
            if(left) facingRight=false;
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //ZB: Moving players or doing actions if key is pressed
        switch(key){    //TODO: Tesztelni kell hogy client és host most külön mozog vagy egyszerre vagy most mi van?
            case(KeyEvent.VK_LEFT): this.setLeft(true); break;
            case(KeyEvent.VK_RIGHT): this.setRight(true); break;
            case(KeyEvent.VK_UP): this.setJumping(true); break;
            case(KeyEvent.VK_SPACE):this.setShooting(); break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        //ZB: Stop Moving players or doing actions if key is released
        switch(key){
            case(KeyEvent.VK_LEFT): this.setLeft(false); break;
            case(KeyEvent.VK_RIGHT): this.setRight(false); break;
            case(KeyEvent.VK_UP): this.setJumping(false); break;
            case(KeyEvent.VK_SPACE): this.setShooting(); break;
        }
    }
}
