package com.DuelingFates.Objects.Consumable;

import com.DuelingFates.Objects.Player;
import com.DuelingFates.TileMap.TileMap;

public class Ammo extends Consumable{

    private int ammoQuantity;

    public Ammo(TileMap tileMap, int ammoQuantity) {

        super(tileMap);
        this.ammoQuantity = ammoQuantity;

    }

    @Override
    public void spawnConsumable(Consumable consumable) {
        //kiválasztunk egy random pozíciót a mapon és ott példányosítjuk
        //megadjuk, hogy hova lehet spawnolni, mert tudjuk a map layoutját
        //és azok közül random választunk
        //az időpont amilyen sebeséggel spawnol, pedig szintén lehet random, vagy minden 20 secenként

    }

    @Override
    public void useConsumable(Consumable consumable, Player player) {
        //Használjuk az ammo pickupot, azaz a player ammo számát ammoQuantity-vel növeljük
        //collision intersect függvénnyel érzékeljük, hogy a player tile-ja érinti-e már
        //a consumable tile-ját.
        //ekkor eltüntetjük, azaz nullba állítjuk az adott példányt, kirajzolni, meg ha null
        //akkor nem kell (ezt külön elenőrizzük az exception elkerülése miatt


    }

}
