package com.DuelingFates.Main;

import com.DuelingFates.GameState.StateManager;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;

public class MainProcess extends JFrame implements Runnable{

    JFrame duelingFates;
    ImageIcon GameLogo;

    private StateManager stateManager;                                       //állapotgép melyen keresztül az állapotokat elérjük

    public static final int gameWidth = 1920;
    public static final int gameHeight = 1080;
    public final int FPS = 60;                                               // 1/60 = 16.67 millisec
    public boolean gameIsRunning = false;

    public static BufferedImage gameWindow;
    public static Graphics2D graphics;
    /*ImageIcon imageIcon;
    /JLabel jLabel;*/


    @SuppressWarnings("CommentedOutCode")
    public MainProcess(){

        duelingFates = new JFrame("DuelingFates");                           //új Frame

        // Frame Icon beállítása
        try {
            GameLogo = new ImageIcon("DuelingFates/Sources/logoicon_DuelingFatesDF.png");     //Frame logo betöltése
            duelingFates.setIconImage(GameLogo.getImage());                      //Icon beállítása - csak Image lehet az argumentum, ezért kell getImage()
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //majd fullscreen esetben
        //duelingFates.setUndecorated(true);                                     //nincs keret
        //duelingFates.setExtendedState(JFrame.MAXIMIZED_BOTH);                  //max Vertical & Horizontal

        duelingFates.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //kilépünk a Frame zárásával
        duelingFates.pack();                                                     //tartalomhoz igazodik a Frame mérete
        duelingFates.setResizable(true);                                         //nem lehet átméretezni
        duelingFates.setVisible(true);                                           //default esetben hidden lenne
        duelingFates.setLayout(null);
        duelingFates.setSize(new Dimension(gameWidth, gameHeight));              //méret megadása, csak Dimension típust értelmez
        duelingFates.setLocationRelativeTo(null);                                //null: az ablak a képernyőn közepén lesz
        //Mivel mindkettő true alapértelmezetten, ezért nincs szükség rá
        /*setFocusable(true);
        requestFocus(); */                                                      //alkalmazás fókuszt kap
        startThread();
    }

    public synchronized void startThread(){                                      //synchronized: multi-threaded esetben, egyszerre 1 szál fér az objektumhoz
        Thread threadMain = new Thread(this);
        threadMain.start();
    }


    public void run(){                                                      //Runnable miatt automatikusan meghívódik
        gameWindow = new BufferedImage(gameWidth,gameHeight,BufferedImage.TYPE_INT_RGB);    //a kép melyre rajzolunk
        /*imageIcon = new ImageIcon(gameWindow); jLabel = new JLabel(imageIcon);*/
        graphics = gameWindow.createGraphics();                             //grafika amit kirajzolunk
        gameIsRunning = true;                                               //játék már fut
        stateManager = new StateManager(graphics);                                  //állapotgép példányosítása

        final long oneFrameDuration = 1000/FPS;                             //(1/60)*1000, csak a long miatt úgy 0 lesz

        while (gameIsRunning){     //"végtelen" ciklus
            System.out.println(stateManager.currentState == StateManager.States.GAMEPLAYSTATE);
            if(stateManager.currentState == StateManager.States.GAMEPLAYSTATE){
                updateGame();
                updateScreen(graphics);
                renderScreen();
                    try{
                        synchronized (this) {
                            this.wait(oneFrameDuration);                            //Thread.sleep(oneFrameDuration); az éppen futó threadet megszakítja, millisec ideig
                            System.out.println("I'm waiting");
                        }                                                           //de warningot ad, így ezzel a megoldással elkerülhető
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                        }
            }

            if(StateManager.stateChanged){
                stateManager.updateSwingUI(duelingFates);
                System.out.println("TRUEEEEE");
            }
        }
    }

    void updateGame(){
        stateManager.update();                                               //állapotgép adott állapotának frissítése - logika
    }

    void updateScreen(Graphics2D graphics){                                                     //állapotgép adott képének frissítése - megjelenítés
        stateManager.draw(graphics);
    }

    //kép kirenderelése a képernyőre
    void renderScreen(){
            Graphics gScreen = duelingFates.getGraphics();                              //JPanel miatt lehetséges
            gScreen.drawImage(gameWindow, 0, 0, 1920, 1080, null);   //gameWindow-ra renderelünk, a bal felső saroktól
            gScreen.dispose();                                                              //törlés, hogy újból ki tudjuk rajzolni

    }


}
