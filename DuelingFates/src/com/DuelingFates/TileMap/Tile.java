package com.DuelingFates.TileMap;

import java.awt.image.BufferedImage;

public class Tile {

    private final BufferedImage tileImage;          //A Tile típus egyetlen képből áll

    public Tile(BufferedImage tileImage){

        this.tileImage = tileImage;

    }

    public BufferedImage getTileImage(){

        return tileImage;

    }
}
