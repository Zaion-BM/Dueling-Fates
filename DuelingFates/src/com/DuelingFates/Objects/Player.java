package com.DuelingFates.Objects;
import com.DuelingFates.GameState.GamePlayState;
import com.DuelingFates.TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Player extends GameObject implements KeyListener {

    //Player parameters
    private String playerName;
    private int playerHealth;
    private int maxHealth;
    private int playerScore;
    private Weapon playerWeapon;
    private int playerAmmoQty;
    private boolean dead;
    public boolean blinkRed;
    public long blinkCount;
    private boolean shooting;
    private ArrayList<Projectile> bullets;

    //Player animation actions
    private static final int IDLE=0;
    private static final int RUNNING=2;
    private static final int SHOOTING=3;
    private static final int JUMPINGANDFALLING=1;
    private static final int DEAD=4;

    //projectile
    private Rectangle ar;

    //public static enum playerCharacter{PIRATE_DECKHAND, POSSESSED_ARMOR};

    //Player's sprite animations
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
            -1, 3, 1, 6
    };

    //ZB: Implementation of constructors
    public Player(TileMap tileMap) {                        //TODO : ahol a playername a MainProcess.playerNameTemp
        super(tileMap);
        //projectile                                        TODO: ezt nem biztos hogy ide kéne
        ar = new Rectangle(0, 0, 0, 0);
        ar.width = 10;
        ar.height = 2;
        bullets= new ArrayList<Projectile>();
        playerAmmoQty=30;

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
        setPlayerHealth(100);
        setPlayerScore(0);
        setPosition(400, 300);
        //TODO: playerCharacter beállítása, az animation ez alapján történik, mert a spriteok is ott töltődnek be

        try {

            BufferedImage spritesheet = ImageIO.read(new File(
                            "DuelingFates/Sources/char_PirateDeckhand/PlayerSprite_gun.png"
                    )
            );

            int count = 0;
            sprites = new ArrayList<>();

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
       if(playerAmmoQty>0)  shooting=true;
    }
    public void setLeft(boolean b){ left=b; }
    public void setRight(boolean b){ right=b; }
    public void setJumping(boolean b){ jumping=b; }

    //TODO: damage számolása
    public void hit(int damage) {
        if(blinkRed) return;
        //JukeBox.play("playerhit");            //TODO max erre lesz idő, meg valami háttérzenének, ez 2 hangfájl
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
    public void respawn(){
        reset();
        setPosition(400, 300);
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

    private void setAnimation(int i) {
        currentAction = i;
        animation.setFrames(sprites.get(currentAction));
        animation.setDelay(SPRITEDELAYS[currentAction]);
        spriteWidth = FRAMEWIDTHS[currentAction];
        spriteHeight = FRAMEHEIGHTS[currentAction];
    }

    //ZB: Update Player's position and actions
    public void update(){

        //TODO: idő kiiratása képernyőre? Dave:GAMEPLAYBEN!!! //time++;

        /*
         * Update player's position
         * */
        getNextPosition();
        checkTileMapCollision();
        setPosition(tempX,tempY);

        //TODO: ez mire való?   Dave: Azt hiszem valami bugolt neki és ezért írta bele, nem kell szerintem
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
        //projectiles
            if(shooting && currentAction!=SHOOTING){
                if(playerAmmoQty>0){
                    playerAmmoQty--;
                    Projectile p=new Projectile(tileMap,facingRight);
                    p.setPosition(x+spriteWidth,y);
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


        // check attack finished
        if(currentAction == SHOOTING) {
            if(animation.hasPlayedOnce()) {
                shooting = false;
            }
        }

        // check attack TODO: player2 -őt enemynek állítani és checckolni hogy a lövedék eltalálta e
        //TODO: DAVE: EZT GAMEPLAYBEN NEM?
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
        //TODO: teszt  szükséges
        if(playerHealth==0 || y>tileMap.getMapHeight()+objectHeight*5){
            respawn();
        }

        // set animation, ordered by priority
        if(playerHealth == 0) {
            if (currentAction != DEAD) {
                setAnimation(DEAD);
            }
        }
        else if(shooting){
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
        else if(deltaY<0){                                      //jumping
            if(currentAction != JUMPINGANDFALLING){
                setAnimation(JUMPINGANDFALLING);
            }
        }
        else if(deltaY>0){                                      //falling
            if(currentAction != JUMPINGANDFALLING){
                setAnimation(JUMPINGANDFALLING);
            }

        }
        else if(left || right){                                 //running
            if(currentAction!=RUNNING){
                setAnimation(RUNNING);
            }
        }
        else if(currentAction!=IDLE){                           //standing
                setAnimation(IDLE);
        }
        animation.update();                                     //update animation of player

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
