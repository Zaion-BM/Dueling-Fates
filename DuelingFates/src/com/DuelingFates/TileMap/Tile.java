package com.DuelingFates.TileMap;

import java.awt.image.BufferedImage;

public class Tile {

    private final BufferedImage tileImage;

    public Tile(BufferedImage tileImage){

        this.tileImage = tileImage;

    }

    public BufferedImage getTileImage(){

        return tileImage;

    }
}
