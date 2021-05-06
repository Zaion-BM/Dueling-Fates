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


    /*
     * Implementation of getters and setters
     * */
    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getObjectWidth() {
        return objectWidth;
    }

    public void setObjectWidth(int objectWidth) {
        this.objectWidth = objectWidth;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public void setObjectHeight(int objectHeight) {
        this.objectHeight = objectHeight;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getDeltaPosX() {
        return deltaPosX;
    }

    public void setDeltaPosX(float deltaPosX) {
        this.deltaPosX = deltaPosX;
    }

    public float getDeltaPosY() {
        return deltaPosY;
    }

    public void setDeltaPosY(float deltaPosY) {
        this.deltaPosY = deltaPosY;
    }

    public float getTempPosX() {
        return tempPosX;
    }

    public void setTempPosX(float tempPosX) {
        this.tempPosX = tempPosX;
    }

    public float getTempPosY() {
        return tempPosY;
    }

    public void setTempPosY(float tempPosY) {
        this.tempPosY = tempPosY;
    }
}
