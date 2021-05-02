package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;

public abstract class GameObject {

    //a leszármazottakban elérjük, ha protected
    protected int tileWidth;
    protected int tileHeight;

    protected int objectWidth;
    protected int objectHeight;

    protected float posX;
    protected float posY;
    protected float deltaPosX;
    protected float deltaPosY;

    //elvielg kellene egy temp változó is, hogy csak szinkronizáltan update során frissítsük a karaktert és ne kétszer.
    protected float tempPosX;
    protected float tempPosY;

    protected TileMap tileMap;

    public GameObject(){
        //this.tileMap = tileMap;

    }


}
