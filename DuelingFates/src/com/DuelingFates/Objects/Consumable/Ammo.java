package com.DuelingFates.Objects.Consumable;

import com.DuelingFates.Objects.Player;
import com.DuelingFates.TileMap.TileMap;

import java.awt.event.KeyEvent;

public class Ammo extends Consumable{

    private int ammoQty;

    public Ammo(TileMap tileMap, int ammoQty) {
        super(tileMap);
        this.ammoQty = ammoQty;
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

    /*
     * Implementation of getters and setters
     * */
    public int getAmmoQty() {
        return ammoQty;
    }

    public void setAmmoQty(int ammoQty) {
        this.ammoQty = ammoQty;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
