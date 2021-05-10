package com.DuelingFates.Objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInputHandler implements KeyListener {

    //Playernél figyeljük az inputot
    Player player;

    public PlayerInputHandler(Player player){

        this.player = player;

    }

    //meghívjük az ott definiált metódusokat
    @Override
    public void keyTyped(KeyEvent e) {
        player.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
}
