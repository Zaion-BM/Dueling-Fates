package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;

public abstract class GameObject {

    //a leszármazottakban elérjük, ha protected
    //protected String objectName;
    //protected enum objectAppearance{};

    protected int tileWidth;
    protected int tileHeight;

    protected int objectWidth;
    protected int objectHeight;

    protected float posX;
    protected float posY;
    protected float deltaPosX;
    protected float deltaPosY;

    protected TileMap tileMap;

    public GameObject(){
        //this.tileMap = tileMap;

    }


}
