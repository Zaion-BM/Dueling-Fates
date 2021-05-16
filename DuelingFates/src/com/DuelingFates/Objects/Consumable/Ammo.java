package com.DuelingFates.Objects.Consumable;

import com.DuelingFates.Objects.Player;
import com.DuelingFates.TileMap.TileMap;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Ammo extends Consumable{

    private int ammoQty;
    private int ammoScore;

    public Ammo(TileMap tileMap) {
        super(tileMap);
        ammoQty = 10;

        objectHeight = 37;
        objectWidth = 28;

        ammoScore = 10;


    }
    public Ammo(TileMap tileMap,int x, int y) {
        super(tileMap,x,y);
        ammoQty = 10;

        objectHeight = 37;
        objectWidth = 28;

        ammoScore = 10;




    }

    @Override
    public Consumable spawnConsumable(TileMap tileMap) {
        //kiválasztunk egy random pozíciót a mapon és ott példányosítjuk
        //megadjuk, hogy hova lehet spawnolni, mert tudjuk a map layoutját
        //és azok közül random választunk
        //az időpont amilyen sebeséggel spawnol, pedig szintén lehet random, vagy minden 20 secenként
        Ammo a=new Ammo(tileMap);
        return a;
    }

    @Override
    public void useConsumable(Player player) {

        player.setPlayerAmmoQty(player.getPlayerAmmoQty() + ammoQty);
        player.setPlayerScore(player.getPlayerScore() + ammoScore);
    }
    @Override
    public int getObjectHeight() {
        return objectHeight;
    }

    @Override
    public int getObjectWidth() {
        return objectWidth;
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
