package com.DuelingFates.GameState;

import javax.swing.*;
import java.awt.*;
import com.DuelingFates.Music.JukeBox;

public class StateManager {

    //állapotok definiálása, sorrend nem változik -> ordinal() OK
    public enum States{MAINMENUSTATE, JOINSTATE, HOSTSTATE, SETTINGSTATE, SCORESTATE, GAMEPLAYSTATE}
    public States currentState;                                                 //az aktuális állapot
    private final GameState[] gameStates;                                       //GameState állapotokat tároló tömb
    public static boolean stateChanged = false;                                 //változó, mely állapotváltáskor játszik szerepet -> Swing UI egyszeri kirajzolása

    public StateManager(){
        JukeBox.init();
        gameStates = new GameState[States.values().length];                     //tömb definiálása állapotok száma alapján
        currentState = States.MAINMENUSTATE;                                    //főmenü a default state
        loadState(currentState);                                                //ezt be is töltjük

        //System.out.println(States.values().length + "   " + currentState.ordinal());

        //Loading musics
        try {
            JukeBox.load("/Music/menu_music.mp3", "menu_music");
            //JukeBox.play("menu_music");
            // load sound fx
            JukeBox.load("/SFX/menuoption.mp3", "menuoption");
            JukeBox.load("/SFX/menuselect.mp3", "menuselect");


            JukeBox.load("/SFX/shoot1.mp3", "shoot1");
            JukeBox.load("/SFX/omaewa.mp3", "omaewa");
            JukeBox.load("/SFX/nani.mp3", "nani");
            JukeBox.load("/Music/bg_music_volumedown.mp3", "bg_music_volumedown");

            JukeBox.load("/Music/score_music_long.mp3","score_music_long");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void setStateChangedTrue(){

        stateChanged = true;

    }

    public static void setStateChangedFalse() {

        stateChanged = false;

    }


    //állapotok alapján új példányok létrehozása
    public void loadState(States enumStates){

        switch (enumStates){
            case MAINMENUSTATE:
                //ordinal() megadja az enum számértékét, így a tömbben címezhető, hardcoding elkerülve
                gameStates[States.MAINMENUSTATE.ordinal()] = new MainMenuState(this);
                //JukeBox.play("menu_music"); //menu music starts playing
                JukeBox.stop("bg_music_volumedown"); //other music stop playing
                JukeBox.stop("score_music_long");
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
                //JukeBox.loop("score_music_long", 600, JukeBox.getFrames("score_music_long") - 2200);
                JukeBox.stop("menu_music");
                JukeBox.stop("bg_music_volumedown");
                break;
            case GAMEPLAYSTATE:
                gameStates[States.GAMEPLAYSTATE.ordinal()] = new GamePlayState(this);
                //JukeBox.loop("bg_music_volumedown", 600, JukeBox.getFrames("bg_music_volumedown") - 2200);
                JukeBox.stop("score_music_long");
                JukeBox.stop("menu_music");
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
            //System.out.println("StateManager DRAW has been called!");
            if (gameStates[currentState.ordinal()] != null) {
                gameStates[currentState.ordinal()].draw(graphics);
            }

        }
    }

    public void update() {                                                       //az aktuális állapot frissítése, ha épp van aktív állapot: csak GAMEPLAY

        if (currentState != null) {
            //System.out.println("StateManager UPDATE has been called!");

            if (gameStates[currentState.ordinal()] != null) {
                gameStates[currentState.ordinal()].update();
            }

        }
    }

    public void updateSwingUI(JFrame duelingFates, JLayeredPane layeredPane){    //az aktuális swing ablak frissítése

        if (gameStates[currentState.ordinal()] != null) {
            gameStates[currentState.ordinal()].updateSwingUI(duelingFates, layeredPane);
        }

    }

}
