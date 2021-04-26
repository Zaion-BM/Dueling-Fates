package com.DuelingFates.GameState;

import javax.swing.*;
import java.awt.*;

public class StateManager {

    public enum States{MAINMENUSTATE, JOINSTATE, SETTINGSTATE,
                       SCORESTATE, GAMEPLAYSTATE}                               //állapotok definiálása, sorrend nem változik -> ordinal() OK
    private Graphics2D graphFromMain;
    public States currentState;                                                 //az aktuális állapot
    private final GameState[] gameStates;                                       //GameState állapotokat tároló tömb
    public static boolean stateChanged = false;

    public StateManager(Graphics graphics){

        gameStates = new GameState[States.values().length];                     //tömb definiálása állapotok száma alapján
        currentState = States.MAINMENUSTATE;                                    //főmenü a default state
        loadState(currentState);                                                //ezt be is töltjük

        //System.out.println(States.values().length + "   " + currentState.ordinal());
    }

    //állapotok alapján új példányok létrehozása
    public void loadState(States enumStates){

        switch (enumStates){
            case MAINMENUSTATE:
                //ordinal(), enum számértéke, így a tömbben címezhető, hardcoding elkerülve
                gameStates[States.MAINMENUSTATE.ordinal()] = new MainMenuState(this);
                break;
            case JOINSTATE:
                gameStates[States.JOINSTATE.ordinal()] = new JoinState(this);
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

    //az aktuális állapot törlése, és a új betöltése
    public void setState(States enumStates){

        gameStates[currentState.ordinal()] = null;                              //az aktuális állapot sorszámának nullázása a tömbben
        currentState = enumStates;                                              //új állapot beállítása
        loadState(currentState);                                                //új állapot betöltése
    }

    public void draw(Graphics2D graphics){                                      //az aktuális állapot kirajzolása: csak GAMEPLAY
        //if(currentState != null) {
            //*********************Ha ezt kikommenteled, crash, de csak ha pl, MainMenuState-ből hívod meg az állapotváltást.
            //StateManager konstruktorban kezdetben GAMEPLAY, akkor jó
            gameStates[currentState.ordinal()].draw(graphics);
            System.out.println("draw");

        //}
    }

    public void update(){                                                        //az aktuális állapot frissítése ha épp van aktív állapot: csak GAMEPLAY
        //if(currentState != null) {
        //*********************Ha ezt kikommenteled, crash
        System.out.println("update " + currentState.ordinal());
        gameStates[currentState.ordinal()].update();
        //}
    }

    public void updateSwingUI(JFrame duelingFates){                             //Az aktuális swing ablak frissítése
        gameStates[currentState.ordinal()].updateSwingUI(duelingFates);
    }
}
