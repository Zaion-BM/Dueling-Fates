package com.DuelingFates.Main;

import com.DuelingFates.GameState.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainProcess extends JPanel implements Runnable{

    private StateManager stateManager;                                       //állapotgép melyen keresztül az állapotokat elérjük

    public final int gameWidth = 1920;
    public final int gameHeight = 1080;
    public final int FPS = 60;                                              // 1/60 = 16.67 millisec
    public boolean gameIsRunning = false;

    BufferedImage gameWindow;
    Graphics2D graphics;

    @SuppressWarnings("CommentedOutCode")
    public MainProcess(){
        setPreferredSize(new Dimension(gameWidth, gameHeight));             //méret megadása, csak Dimension típust értelmez
        //Mivel mindkettő true alapértelmezetten, ezért nincs szükség rá
        //setFocusable(true);
        //requestFocus();                                                   //alkalmazás fókuszt kap
        startThread();
    }

    public synchronized void startThread(){                                 //synchronized: multi-threaded esetben, egyszerre 1 szál fér az objektumhoz
        Thread threadMain = new Thread(this);
        threadMain.start();
    }

    public void run(){                                                      //Runnable miatt automatikusan meghívódik
        gameWindow = new BufferedImage(gameWidth,gameHeight,BufferedImage.TYPE_INT_RGB);    //a kép melyre rajzolunk
        graphics = gameWindow.createGraphics();                             //grafika amit kirajzolunk
        gameIsRunning = true;                                               //játék már fut
        stateManager = new StateManager();                                  //állapotgép példányosítása

        final long oneFrameDuration = 1000/FPS;                             //(1/60)*1000, csak a long miatt úgy 0 lesz

        while (gameIsRunning){                                              //"végtelen" ciklus
            //meghívjük a függvényeket
            updateGame();
            updateScreen();
            renderScreen();

            try{
                synchronized (this) {
                    this.wait(oneFrameDuration);                            //Thread.sleep(oneFrameDuration); az éppen futó threadet megszakítja, millisec ideig
                    //System.out.println("I'm waiting");
                }                                                           //de warningot ad, így ezzel a megoldással elkerülhető
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    void updateGame(){
        stateManager.update();                                                      //állapotgép adoot állapotának frissítése
    }

    void updateScreen(){                                                            //állapotgép adott képének kirajzolása
        stateManager.draw(graphics);
    }

    void renderScreen(){                                                            //kép kirenderelése a képernyőre

    }
}
