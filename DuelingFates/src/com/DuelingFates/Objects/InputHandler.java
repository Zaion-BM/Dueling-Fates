package com.DuelingFates.Objects;

import com.DuelingFates.GameState.GamePlayState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    //Csak a gameplay-nél figyeljük a keyboardot
    GamePlayState gamePlayState;

    public InputHandler(GamePlayState gamePlayState){

        this.gamePlayState = gamePlayState;

    }

    //meghívjük az ott definiált metódusokat
    @Override
    public void keyTyped(KeyEvent e) {
        gamePlayState.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePlayState.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gamePlayState.keyReleased(e);
    }
}