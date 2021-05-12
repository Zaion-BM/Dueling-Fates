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

        objectHeight = 39;
        objectWidth = 30;

        healthScore = 5;

        //define spawn bounds
        Random random = new Random();
        int availableSpawnsY = random.nextInt(680);
        availableSpawnsY+= 200;
        int availableSpawnsX = random.nextInt(1700);
        availableSpawnsX+= 110;

        this.setPosition(availableSpawnsX,availableSpawnsY);

    }


    @Override
    public void spawnConsumable() {
        //kiválasztunk egy random pozíciót a mapon és ott példányosítjuk
        //megadjuk, hogy hova lehet spawnolni, mert tudjuk a map layoutját
        //és azok közül random választunk
        //az időpont amilyen sebeséggel spawnol, pedig szintén lehet random, vagy minden 20 secenként


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
