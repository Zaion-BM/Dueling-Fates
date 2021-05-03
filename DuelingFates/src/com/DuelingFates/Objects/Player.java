package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;

public class Player extends GameObject{

    //majd kellenek setterek és getterek
    private String playerName;
    private int playerHealth;
    private int playerScore;
    private Weapon playerWeapon;
    private int playerAmmoCount;

    //public static enum playerCharacter{PIRATE_DECKHAND, POSSESSED_ARMOR};

    //mozgási paraméterek
    private float moveSpeed;
    private float maxSpeed;
    private float maxFallingSpeed;
    private float jumpOffset;
    private float playerGravity;

    private TileMap tileMap;        //amin a collisiont érzékeljük

    public Player(TileMap tileMap, String playerName){      //ahol a playername a MainProcess.playerNameTemp

        this.playerName = playerName;
        //playerCharacter beállítása, az animation ez alapján történik, mert a spriteok is ott töltődnek be

    }

    public String getPlayerName(){

        return playerName;

    }

    public void setPlayerName(String newName){

        playerName = newName;

    }





}
