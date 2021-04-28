package com.DuelingFates.TileMap;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage tileImage;

    public Tile(BufferedImage tileImage){

        this.tileImage = tileImage;

    }

    public Tile getTile(){

        return this;

    }

    public BufferedImage getTileImage(){

        return tileImage;

    }
}
