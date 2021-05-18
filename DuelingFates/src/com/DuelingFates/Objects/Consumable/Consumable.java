package com.DuelingFates.Objects.Consumable;

import com.DuelingFates.Objects.GameObject;
import com.DuelingFates.Objects.Player;
import com.DuelingFates.TileMap.TileMap;

import java.util.Random;

public abstract class Consumable extends GameObject {

    public Consumable(TileMap tileMap) {
        super(tileMap);

        //define spawn bounds
        Random random = new Random();
        int availableSpawnsY = random.nextInt(680);
        availableSpawnsY+= 200;
        int availableSpawnsX = random.nextInt(1700);
        availableSpawnsX+= 110;

        this.setPosition(availableSpawnsX,availableSpawnsY);

    }

    public Consumable(TileMap tileMap, int x, int y) {
        super(tileMap,x,y);
    }


    public abstract Consumable spawnConsumable(TileMap tileMap);
    public abstract void useConsumable(Player player);
    public abstract int getObjectHeight();
    public abstract int getObjectWidth();

    /*public void getNextPosition(){
        //ZB: If we are falling
        if(falling)

            {
                deltaY += fallSpeed;
                if (deltaY > maxFallSpeed) deltaY = maxFallSpeed;
            }

    }*/

    public void update(){
        /*
         * Update consumable's position
         * */
        //getNextPosition();
        checkTileMapCollision();
        setPosition(tempX,tempY);
    }

}
