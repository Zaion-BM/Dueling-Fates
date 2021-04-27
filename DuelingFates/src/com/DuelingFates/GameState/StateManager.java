package com.DuelingFates.GameState;

import javax.swing.*;
import java.awt.*;

public class StateManager {

    //állapotok definiálása, sorrend nem változik -> ordinal() OK
    public enum States{MAINMENUSTATE, JOINSTATE, HOSTSTATE, SETTINGSTATE, SCORESTATE, GAMEPLAYSTATE}
    public States currentState;                                                 //az aktuális állapot
    private final GameState[] gameStates;                                       //GameState állapotokat tároló tömb
    public static boolean stateChanged = false;                                 //változó, mely állapotváltáskor játszik szerepet -> Swing UI egyszeri kirajzolása

    public StateManager(){

        gameStates = new GameState[States.values().length];                     //tömb definiálása állapotok száma alapján
        currentState = States.MAINMENUSTATE;                                    //főmenü a default state
        loadState(currentState);                                                //ezt be is töltjük

        //System.out.println(States.values().length + "   " + currentState.ordinal());
    }

    //állapotok alapján új példányok létrehozása
    public void loadState(States enumStates){

        switch (enumStates){
            case MAINMENUSTATE:
                //ordinal() megadja az enum számértékét, így a tömbben címezhető, hardcoding elkerülve
                gameStates[States.MAINMENUSTATE.ordinal()] = new MainMenuState(this);
                break;
            case JOINSTATE:
                gameStates[States.JOINSTATE.ordinal()] = new JoinState(this);
                break;
            case HOSTSTATE:
                gameStates[States.HOSTSTATE.ordinal()] = new HostState(this);
                break;
            case SETTINGSTATE:
                gameStates[States.SETTINGSTATE.ordinal()] = new SettingsState(this);
                break;
            case SCORESTATE:
                gameStates[States.SCORESTATE.ordinal()] = new ScoreState(this);
                break;
            case GAMEPLAYSTATE:
                gameStates[States.GAMEPLAYSTATE.ordinal()] = new GamePlayState(this);
                break;
        }

    }

    //az aktuális állapot törlése, és az új betöltése
    public void setState(States enumStates){

        gameStates[currentState.ordinal()] = null;                              //az aktuális állapot nullázása a tömbben
        currentState = enumStates;                                              //új állapot beállítása
        loadState(currentState);                                                //új állapot betöltése

    }

    public void draw(Graphics2D graphics){                                      //az aktuális állapot kirajzolása, ha van aktív állapot: csak GAMEPLAY

        if(currentState != null) {
            System.out.println("StateManager DRAW has been called!");
            if (gameStates[currentState.ordinal()] != null) {
                gameStates[currentState.ordinal()].draw(graphics);
            }

        }
    }

    public void update() {                                                       //az aktuális állapot frissítése, ha épp van aktív állapot: csak GAMEPLAY

        if (currentState != null) {
            System.out.println("StateManager UPDATE has been called!");

            if (gameStates[currentState.ordinal()] != null) {
                gameStates[currentState.ordinal()].update();
            }

        }
    }

    public void updateSwingUI(JFrame duelingFates, JLayeredPane layeredPane){    //az aktuális swing ablak frissítése

        if(gameStates[currentState.ordinal()] != null) {
            gameStates[currentState.ordinal()].updateSwingUI(duelingFates, layeredPane);
        }

    }

}
