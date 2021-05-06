package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;

public class Player extends GameObject{

    //Player parameters
    private String playerName;
    private int playerHealth;
    private int playerScore;
    private Weapon playerWeapon;
    private int playerAmmoQty;

    //public static enum playerCharacter{PIRATE_DECKHAND, POSSESSED_ARMOR};

    //Movement parameters
    private float moveSpeed;
    private float maxSpeed;
    private float maxFallingSpeed;
    private float jumpOffset;
    private float playerGravity;

    private TileMap tileMap;        //amin a collisiont érzékeljük

    public Player(TileMap tileMap, String playerName, Weapon playerWeapon){      //ahol a playername a MainProcess.playerNameTemp
        //Specify player parameters
        setPlayerName(playerName);
        setPlayerHealth(100);
        setPlayerScore(0);
        setPlayerWeapon(playerWeapon);
        setPlayerAmmoQty(10);
        //Specify movement parameters
        setMoveSpeed(1);
        setMaxSpeed(5);
        setMaxFallingSpeed(5);
        setJumpOffset(0);
        setPlayerGravity(5);
        //Specify player (object) width and height
        setObjectWidth(40);
        setObjectHeight(80);
        //Spawn point
        setPosX(200);
        setPosY(400);
        //Player is standing still
        setDeltaPosX(0);
        setDeltaPosY(0);
        //Collision to tile under spawn point?


        //playerCharacter beállítása, az animation ez alapján történik, mert a spriteok is ott töltődnek be


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
    public void setPlayerScore(int playerScore){
        this.playerScore = playerScore;
    }
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
     *Get player's movement speed
     *@return float: Player's movement speed
     * */
    public float getMoveSpeed() {
        return moveSpeed;
    }
    /*
     *Set player's movement speed
     *@param float: Add number (movement speed) between [1.00,5.00]
     *@return Nothing
     * */
    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
    /*
     *Get player's maximal speed limit
     *@return float: Player's maximal speed limit
     * */
    public float getMaxSpeed() {
        return maxSpeed;
    }
    /*
     *Set player's maximal speed limit
     *@param float: Add number (maximal speed limit) between [1.00,5.00]
     *@return Nothing
     * */
    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    /*
     *Get player's maximal falling speed
     *@return float: Player's maximal falling speed
     * */
    public float getMaxFallingSpeed() {
        return maxFallingSpeed;
    }
    /*
     *Set player's maximal falling speed
     *@param float: Add number (maximal falling speed) between [1.00,5.00]
     *@return Nothing
     * */
    public void setMaxFallingSpeed(float maxFallingSpeed) {
        this.maxFallingSpeed = maxFallingSpeed;
    }
    /*
     *Get player's jump offset
     *@return float: Player's jump offset
     * */
    public float getJumpOffset() {
        return jumpOffset;
    }
    /*
     *Set player's jump offset
     *@param float: Add number (jump offset) between [1.00,5.00]
     *@return Nothing
     * */
    public void setJumpOffset(float jumpOffset) {
        this.jumpOffset = jumpOffset;
    }
    /*
     *Get player's gravity
     *@return float: Player's gravity
     * */
    public float getPlayerGravity() {
        return playerGravity;
    }
    /*
     *Set player's gravity
     *@param float: Add number (gravity) between [1.00,5.00]
     *@return Nothing
     * */
    public void setPlayerGravity(float playerGravity) {
        this.playerGravity = playerGravity;
    }

}
