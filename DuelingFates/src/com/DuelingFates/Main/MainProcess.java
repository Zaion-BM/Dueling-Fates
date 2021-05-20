package com.DuelingFates.Main;

import com.DuelingFates.GameState.GamePlayState;
import com.DuelingFates.GameState.StateManager;
import com.DuelingFates.Networking.Server.*;
import com.DuelingFates.Networking.Client.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.time.Duration;
import java.time.Instant;

import static com.DuelingFates.Objects.Player.*;

public class MainProcess extends JPanel implements Runnable{

    private final JFrame duelingFates;                                              //Frame amiben fut az alkalmazás
    private final JLayeredPane layeredPane;                                         //dimenzióval rendelkező ContentPane változat, amin elhelyezzük a Swing elemeket

    //állapotgép melyen keresztül az állapotokat elérjük: volatile mert a while gameloop egy része néha nem futott le
    //valószínűleg a feltétel ellenőrzése miatt, mely a swing menük miatt szükséges
    volatile public static StateManager stateManager;

    public final static int FPS = 60;                                                // 1/60 = 16.67 millisec
    private boolean gameIsRunning = false;

    private static Graphics2D graphics;                                             //amit kirajzolunk a gameWindow-ra
    private static BufferedImage gameWindow;                                        //amire rajzolunk a Frame-en belül GAMEPLAYSTATE-ben
    private Image cursorImage;
    private static final int gameWidth = 1920;
    private static final int gameHeight = 1080;

    public static Cursor gameCursor;                                                //egyedi cursor
    public static Cursor hiddenCursor;                                              //GamePlay esetén elrejtjük
    public static Font balooThambiFont;                                             //egyedi font
    public static Font balooThambiFontSmall;                                        //egyedi font a playerName miatt
    public static Font balooThambiFontBig;                                          //egyedi font az ammo-hoz
    public static Font balooThambiFontVerySmall;                                    //egyedi font a player névhez

    //változók melyek a menüben kapnak értéket, de csak a gameplaynél van szükségünnk rá
    public static String playerNameTemp;                                            //játékos neve kezdetben
    public static String characterTemp;                                             //kiválasztott karakter
    public static String enemyCharacterTemp;                                        //az ellenség karaktere
    public static String mapTemp;                                                   //map kedzetben
    public static int matchDurationTemp;                                            //meccs hossza kezdetben

    public static boolean startServer = false;                                      //Host stateben kap értéket
    public static boolean joinServer = false;                                       //Join stateben kap értéket
    public static boolean amIServer = false;                                        //nézzük, hogy ki a szerver

    public MainProcess(){

        duelingFates = new JFrame("DuelingFates");
        layeredPane = new JLayeredPane();
        ImageIcon GameLogo;
        String fontLocation = "DuelingFates/Sources/Font/BalooThambi-Regular.ttf";

        //Frame beállítása, Image beolvasások és static változók létrehozása
        try {

            //TODO JAR fájl itt errort dob...még meg kell oldani
            cursorImage = ImageIO.read(new File("DuelingFates/Sources/gui_Spike.png"));
            GameLogo = new ImageIcon("DuelingFates/Sources/logoicon_DuelingFatesDF.png");                       //Frame logo betöltése
            duelingFates.setIconImage(GameLogo.getImage());                                                             //Icon beállítása - csak Image lehet az argumentum, ezért kell getImage()

            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();                //fontok leírására használt GraphicsEnvironment

            balooThambiFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)).deriveFont(55f);              //font létrehozása a Source mappában lévő .ttf-ből
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)));

            balooThambiFontSmall = Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)).deriveFont(35f);
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)));

            balooThambiFontVerySmall = Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)).deriveFont(20f);
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)));

            balooThambiFontBig = Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)).deriveFont(80f);
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

        duelingFates.setUndecorated(false);                                                   //van keret
        duelingFates.setSize(new Dimension(getGameWidth(), getGameHeight()));                 //méret megadása, csak Dimension típust értelmez
        duelingFates.setLocationRelativeTo(null);                                             //null: az ablak a képernyőn közepén lesz, focust alapértelmezetten kap

        setMenuDefaults();
        startThread();
    }

    private void setMenuDefaults(){

        playerNameTemp ="HostName";                                                           //Default név beállítása
        characterTemp = "PirateDeckhand";
        enemyCharacterTemp = "PossessedArmor";
        //mapTemp = "SnowyMountain";                                                          //Default map
        mapTemp = "Crimson";
        //mapTemp = "CloudyForest";
        matchDurationTemp = 1;                                                                //Default time

    }

    public synchronized void startThread(){                                         //synchronized: multi-threaded esetben, egyszerre 1 szál fér hozzá az objektumhoz

        Thread threadMain = new Thread(this);
        threadMain.start();

    }

    //Runnable miatt automatikusan meghívódik
    public void run(){
        gameWindow = new BufferedImage(getGameWidth(),getGameHeight(),BufferedImage.TYPE_INT_RGB);      //a kép melyre rajzolunk
        graphics = gameWindow.createGraphics();                                                         //grafika amit kirajzolunk
        gameIsRunning = true;
        stateManager = new StateManager();                                                              //állapotgép példányosítása

        final long oneFrameDuration = 1000/FPS;                                                         // = (1/60)*1000

        Thread serverSender = new Thread(new Server(messageQueue,6868));                            //Server port definiálása
        Thread serverReceiver = new Thread(new Client("Kliens",6869));

        Thread clientSender = new Thread(new Server(messageQueue,6869));                            //Kliens port definiálása
        Thread clientReceiver = new Thread(new Client("Kliens",6868));

        while (gameIsRunning) {

            if (StateManager.stateChanged) {                                                  //ha állapotot váltunk frissítjük a Swing Frame-et
                stateManager.updateSwingUI(duelingFates, layeredPane);                        //a Frame és a JLayeredPane továbbadásával tudjuk őket frissíteni

                if (stateManager.currentState == StateManager.States.JOINSTATE) {
                    amIServer = false;
                    clientSender.start();
                    clientReceiver.start();
                }

            }

            if (startServer && stateManager.currentState!=StateManager.States.GAMEPLAYSTATE) {           //Host stateben állítjuk be
                amIServer = true;
                serverSender.start();
                serverReceiver.start();
                startServer = false;
            }


            if (stateManager.currentState == StateManager.States.GAMEPLAYSTATE) {             //csak a GamePlayState-ben van grafikus kirajzolás (60 FPS-sel)
                Instant start = Instant.now();
                updateGame();
                updateScreen(graphics);
                renderScreen();

                try {
                    synchronized (this) {
                        this.wait(oneFrameDuration);                                    //Thread.sleep(oneFrameDuration); az éppen futó threadet megszakítja, millisec ideig
                    }                                                                   //de warningot ad, így ezzel a megoldással elkerülhető
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Instant end = Instant.now();                                            //Időszámítás
                Duration timeElapsed = Duration.between(start,end);
                GamePlayState.addMillis(timeElapsed.toMillis());

                if(GamePlayState.getMillis() >= 1000 ){
                    GamePlayState.setMillisZero();
                    GamePlayState.addSeconds();
                }
                if(GamePlayState.getSeconds() == 60){
                    GamePlayState.addMinutes();
                    GamePlayState.setSecondsZero();
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

            Graphics gScreen = duelingFates.getGraphics();                                               //JPanel miatt tudjuk lekérdezni
            gScreen.drawImage(gameWindow, 0, 0, getGameWidth(), getGameHeight(), null);      //gameWindow-ra renderelünk, a bal felső saroktól
            gScreen.dispose();                                                                           //kép törlése a Frame-ről, hogy újból ki tudjuk rajzolni

    }

    // Setters and Getters
    public static String getPlayerNameTemp(){ return playerNameTemp; }

    public static void setPlayerNameTemp(String newPlayerNameTemp){ playerNameTemp = newPlayerNameTemp; }

    public static String getCharacterTemp(){ return characterTemp; }

    public static void setCharacterTemp(String newCharacterTemp){ characterTemp = newCharacterTemp; }

    public static String getEnemyCharacterTemp(){ return enemyCharacterTemp; }

    public static void setEnemyCharacterTemp(String newCharacterTemp){ enemyCharacterTemp = newCharacterTemp; }

    public static String getMapTemp(){ return mapTemp; }

    public static void setMapTemp(String newMapTemp){ mapTemp = newMapTemp; }

    public static int getMatchDurationTemp(){ return matchDurationTemp; }

    public static void setMatchDurationTemp(int newMatchDurationTemp){ matchDurationTemp = newMatchDurationTemp; }

    public static int getGameWidth(){ return gameWidth; }

    public static int getGameHeight(){ return gameHeight; }

    public static boolean getAmIServer(){ return  amIServer; }

    public static void setStartServer(){

        startServer = true;
        joinServer = false;

    }

}
