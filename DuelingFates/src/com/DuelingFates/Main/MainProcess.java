package com.DuelingFates.Main;

import com.DuelingFates.GameState.StateManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;

/*
TODO list: VALAMIÉRT MOST JÓ, dont ask why
    1. A MAIN PROCESS() KONSTRUKTORBAN KELL ELENŐRIZNI, hogy a fájlok beolvashatók-e
    Mert az állapotgép konstruktorában nem tudunk fájlt beolvasni crash nélkül
    Hogy pontosan miért, azt még nem sikerült kiderítenem --- FIXED!, de hogy miért??? : volatile,
    meg a swing kirajzolás a whileloopban előbb van??
    2. Ha a duelingFates static lenne, akkor az egyes állapotokban csak a JLayerPanet kellene
    mindig frissíteni, és továbbadni. Memória és egyszerűség, majd a végén érdemes frissíteni
*/

public class MainProcess extends JPanel implements Runnable{

    JFrame duelingFates;                                                            //Frame amiben fut az alkalmazás
    JLayeredPane layeredPane;                                                       //dimenzióval rendelkező ContentPane változat, amin elhelyezzük a Swing elemeket
    ImageIcon GameLogo;

    //állapotgép melyen keresztül az állapotokat elérjük, volatile mert a while gameloop egy része volt hogy nem futott le (? miért?, mert nem változott?)
    volatile private StateManager stateManager;

    private static final int gameWidth = 1920;
    private static final int gameHeight = 1080;
    public final int FPS = 60;                                                      // 1/60 = 16.67 millisec
    public boolean gameIsRunning = false;

    public static BufferedImage gameWindow;                                         //amire rajzolunk a Frame-en belül GAMEPLAYSTATE-ben
    public static Graphics2D graphics;                                              //amit kirajzolunk a gameWindow-ra
    public Image cursorImage;

    public static Cursor gameCursor;                                                //egyedi cursor
    public static Cursor hiddenCursor;                                              //GamePlay esetén elrejtjük
    public static Font BalooThambiFont;                                             //egyedi font

    public MainProcess(){

        duelingFates = new JFrame("DuelingFates");
        layeredPane = new JLayeredPane();
        String fontLocation = "DuelingFates/Sources/Font/BalooThambi-Regular.ttf";

        //Frame beállítása, Image beolvasása és static változók létrehozása
        try {
            cursorImage = ImageIO.read(new File("DuelingFates/Sources/gui_Spike.png"));
            GameLogo = new ImageIcon("DuelingFates/Sources/logoicon_DuelingFatesDF.png");                       //Frame logo betöltése
            duelingFates.setIconImage(GameLogo.getImage());                                                             //Icon beállítása - csak Image lehet az argumentum, ezért kell getImage()

            BalooThambiFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)).deriveFont(55f);              //font létrehozása a Source mappában lévő .ttf-ből
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();                //fontok leírására használt GraphicsEnvironment
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        gameCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0,0), "gameCursor");
        //láthatatlan egér
        int[] empty = new int[16 * 16];
        Image invisibleImage = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, empty, 0, 16));
        hiddenCursor = Toolkit.getDefaultToolkit().createCustomCursor(invisibleImage, new Point(0,0), "hiddenCursor");

        duelingFates.setSize(new Dimension(getGameWidth(), getGameHeight()));                 //méret megadása, csak Dimension típust értelmez
        duelingFates.setLocationRelativeTo(null);                                   //null: az ablak a képernyőn közepén lesz, focust alapértelmezetten kap

        startThread();
    }

    public static int getGameWidth(){

        return gameWidth;

    }


    public static int getGameHeight(){

        return gameHeight;

    }

    public synchronized void startThread(){                                         //synchronized: multi-threaded esetben, egyszerre 1 szál fér hozzá az objektumhoz

        Thread threadMain = new Thread(this);
        threadMain.start();

    }

    //Runnable miatt automatikusan meghívódik
    public void run(){

        gameWindow = new BufferedImage(getGameWidth(),getGameHeight(),BufferedImage.TYPE_INT_RGB);    //a kép melyre rajzolunk
        graphics = gameWindow.createGraphics();                                             //grafika amit kirajzolunk
        gameIsRunning = true;
        stateManager = new StateManager();                                                  //állapotgép példányosítása


        final long oneFrameDuration = 1000/FPS;                                             // = (1/60)*1000

        while (gameIsRunning){                                                              //"gameloop"

            //System.out.println(stateManager.currentState == StateManager.States.GAMEPLAYSTATE);

            if(StateManager.stateChanged){                                                  //ha állapotot váltunk frissítjük a Swing Frame-et
                stateManager.updateSwingUI(duelingFates, layeredPane);                      //a Frame és a JLayeredPane továbbadásával tudjuk őket frissíteni
                //System.out.println("Swing GUI has been updated!");
            }

            if(stateManager.currentState == StateManager.States.GAMEPLAYSTATE){             //csak a GamePlayState-ben van grafikus kirajzolás (60 FPS-sel)

                updateGame();
                updateScreen(graphics);
                renderScreen();

                    try{
                        synchronized (this) {
                            this.wait(oneFrameDuration);                           //Thread.sleep(oneFrameDuration); az éppen futó threadet megszakítja, millisec ideig
                            //System.out.println("I'm waiting");
                        }                                                          //de warningot ad, így ezzel a megoldással elkerülhető
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                        }
            }

        }

    }

    //állapotgép adott állapotának frissítése - gameplay logika
    void updateGame(){

        stateManager.update();

    }

    //állapotgép grafikai részének frissítése - gameplay design
    void updateScreen(Graphics2D graphics){

        stateManager.draw(graphics);

    }

    //kép kirenderelése a képernyőre
    void renderScreen(){

            Graphics gScreen = duelingFates.getGraphics();                                      //JPanel miatt tudjuk lekérdezni
            gScreen.drawImage(gameWindow, 0, 0, getGameWidth(), getGameHeight(), null);      //gameWindow-ra renderelünk, a bal felső saroktól
            gScreen.dispose();                                                                  //kép törlése a Frame-ről, hogy újból ki tudjuk rajzolni

    }

}
