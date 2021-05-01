package com.DuelingFates.Objects.Consumable;

import com.DuelingFates.Objects.GameObject;
import com.DuelingFates.Objects.Player;
import com.DuelingFates.TileMap.TileMap;

public abstract class Consumable extends GameObject {

    public Consumable(TileMap tileMap) {
        super();

    }

    public abstract void spawnConsumable(Consumable consumable);
    public abstract void useConsumable(Consumable consumable, Player player);

}
