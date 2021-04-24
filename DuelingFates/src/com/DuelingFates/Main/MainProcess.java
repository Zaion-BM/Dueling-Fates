package com.DuelingFates.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainProcess extends JPanel implements Runnable{

    public final int gameWidth = 1920;
    public final int gameHeight = 1080;
    public final int FPS = 60;                                              // 1/60 = 16.67 millisec
    public boolean gameIsRunning = false;

    BufferedImage gameWindow;
    Graphics2D graphics;

    @SuppressWarnings("CommentedOutCode")
    MainProcess(){
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
        graphics = gameWindow.createGraphics();                                             //grafika amit kirajzolunk
        gameIsRunning = true;                                                               //játék már fut

        final long oneFrameDuration = 1000/FPS;                             //(1/60)*1000, csak a long miatt úgy 0 lesz

        while (gameIsRunning){                                              //végtelen ciklus, időszámítás, sleep
            //meghívjük a függvényeket
            updateGame();
            updateScreen();
            renderScreen();

            try{
                synchronized (this) {
                    this.wait(oneFrameDuration);                //Thread.sleep(oneFrameDuration); az éppen futó threadet megszakítja, milisec ideig
                    //System.out.println("I'm waiting");
                }                                               //így a warning elkerülhető
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    void updateGame(){

    }

    void updateScreen(){

    }
    void renderScreen(){

    }
}
