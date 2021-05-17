package com.DuelingFates.Objects.Consumable;

import com.DuelingFates.Objects.Player;
import com.DuelingFates.TileMap.TileMap;

import java.awt.event.KeyEvent;
import java.util.Random;

public class HealthPotion extends Consumable {

    private int healthPoints;
    private int healthScore;

    public HealthPotion(TileMap tileMap) {

        super(tileMap);
        healthPoints = 30;

        objectHeight = 36;
        objectWidth = 27;

        healthScore = 5;


    }
    public HealthPotion(TileMap tileMap, int x, int y) {
        super(tileMap,x,y);
        healthPoints = 30;

        objectHeight = 36;
        objectWidth = 27;

        healthScore = 5;
    }


    @Override
    public Consumable spawnConsumable(TileMap tileMap) {
        //TODO :kiválasztunk egy random pozíciót a mapon és ott példányosítjuk
        //megadjuk, hogy hova lehet spawnolni, mert tudjuk a map layoutját
        //és azok közül random választunk
        //az időpont amilyen sebeséggel spawnol, pedig szintén lehet random, vagy minden 20 secenként
      HealthPotion h=new HealthPotion(tileMap);
      return h;
    }

    @Override
    public void useConsumable(Player player) {

        player.setPlayerHealth(player.getPlayerHealth() + healthPoints);
        player.setPlayerScore(player.getPlayerScore() + healthScore);

    }

    /*
     * Implementation of getters and setters
     * */

    @Override
    public int getObjectHeight() {
        return objectHeight;
    }

    @Override
    public int getObjectWidth() {
        return objectWidth;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
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
