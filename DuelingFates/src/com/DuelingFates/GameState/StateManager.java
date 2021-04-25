package com.DuelingFates.GameState;

import java.awt.*;

public class StateManager {

    public enum States{MAINMENUSTATE, JOINSTATE, SETTINGSTATE,
                       SCORESTATE, GAMEPLAYSTATE}                               //állapotok definiálása, sorrend nem változik -> ordinal() OK
    public States currentState;                                                 //az aktuális állapot
    private GameState[] gameStates;                                             //GameState állapotokat tároló tömb

   // new gameStates = new GameState[];

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

    public void draw(Graphics2D graphics){                                       //az aktuális állapot kirajzolása

        gameStates[currentState.ordinal()].draw(graphics);

    }

    public void update(){                                                        //az aktuális állapot frissítése

        gameStates[currentState.ordinal()].update();
    }
}
