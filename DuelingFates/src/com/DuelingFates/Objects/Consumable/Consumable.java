package com.DuelingFates.Objects.Consumable;

import com.DuelingFates.Objects.GameObject;
import com.DuelingFates.Objects.Player;
import com.DuelingFates.TileMap.TileMap;

public abstract class Consumable extends GameObject {

    public Consumable(TileMap tileMap) {
        super(tileMap);

    }

    public abstract void spawnConsumable();
    public abstract void useConsumable(Player player);
    public abstract int getObjectHeight();
    public abstract int getObjectWidth();

}
