package com.DuelingFates.GameState;

import com.DuelingFates.HUDs.EscapeMenu;
import com.DuelingFates.HUDs.HUD;
import com.DuelingFates.Handlers.Keys;
import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.InputHandler;
import com.DuelingFates.Objects.Player;
import com.DuelingFates.Objects.Projectile;
import com.DuelingFates.TileMap.TileMap;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


public class GamePlayState extends GameState implements KeyListener {

    private BufferedImage background;
    private TileMap tileMap;
    @SuppressWarnings("FieldCanBeLocal")
    private final int tileSize = 64;

    private Player hostPlayer;  //TODO: miért kell két külön player? gombok nyomkodásánál mi lesz?
    private Player clientPlayer;

    private HUD hud;

    //egyellőre külön kezeljük, mert a threadelés nem syncronizált alapból
    private ArrayList<Projectile> hostBullets;
    private ArrayList<Projectile> clientBullets;

    private final EscapeMenu escapeMenu = new EscapeMenu();

    public boolean escapePressed = false;
    public boolean escapeBefore = false;

    public GamePlayState(StateManager stateManager){

        super(stateManager);
        StateManager.setStateChangedTrue();

        initialization();

    }

    @Override
    public void initialization() {

        //TODO load different maps ha működik egy darab
        tileMap = new TileMap(tileSize);

        if(MainProcess.getMapTemp().equals("CloudyForest")) {
            try {

                background = ImageIO.read(new File("DuelingFates/Sources/BG_Widescreen/WS_CloudyForest_Background.png"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            tileMap.loadTilesFormFiles("DuelingFates/Sources/Tiles_Brown/");
            tileMap.loadTilesToMap("DuelingFates/Sources/Maps/CloudyForest.txt");
        }

        if(MainProcess.getMapTemp().equals("Crimson")) {
            try {

                background = ImageIO.read(new File("DuelingFates/Sources/BG_Widescreen/WS_Crimson_Background.png"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            tileMap.loadTilesFormFiles("DuelingFates/Sources/Tiles_Sand/");
            tileMap.loadTilesToMap("DuelingFates/Sources/Maps/Crimson.txt");
        }

        if(MainProcess.getMapTemp().equals("SnowyMountain")) {
            try {

                background = ImageIO.read(new File("DuelingFates/Sources/BG_Widescreen/WS_Snow_Background.png"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            tileMap.loadTilesFormFiles("DuelingFates/Sources/Tiles_Brown/");
            tileMap.loadTilesToMap("DuelingFates/Sources/Maps/SnowyMountain.txt");
        }

        hud = new HUD(hostPlayer);

        //TODO player location setter, egy külön metódusban, melyet egy játékos halálakor majd szintén meghívunk
        //TODO start timer, ami lehet metódus, itt végtelen ciklusban várunk a kliens csatlakozására és utána indul a meccs
        //TODO projectile tömb létrehozása
        //Player init
        hostPlayer= new Player(tileMap);

    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(background,0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight(),null);
        tileMap.draw(graphics);
        //System.out.println("Game graphics has been updated!");

        hud.draw(graphics);

        if (escapePressed) {
            escapeMenu.draw(graphics);
            escapeBefore = true;            //engedélyezzük az eltüntetés lehetőségét
        }

        //TODO projectile, kirajzolás for ciklussal, arraylisten végiglépkedünk
        //TODO Objectrenderer meghívása amiben a kirajzolást definiáltuk

        //Player draw
        hostPlayer.draw(graphics);
    }

    @Override
    public void update() {
        //check keys
       // handleInput();

        //Player update
        hostPlayer.update();

        //TODO inputkezelés, kérdés, hogy állapotgépesen, vagy adott keyboard lenyomása esetén x-et lépünk
        //TODO player mozgás, projectile update for ciklusban, ha már nem releváns, akkor helyére NULL
        // ha intersects vagyis érintkezik valamivel és ha nincs a mapon,

        //System.out.println("Game has been updated!");

    }


    //TODO match start, match finish, player death esemény,
    //TODO ezenkívűl itemek és fegyverek spawnolása

    @Override
    public void updateSwingUI(JFrame duelingFates,JLayeredPane layeredPane) {

        duelingFates.addKeyListener(new InputHandler(this));        //Listener a keyboard érzékeléséért

        layeredPane.removeAll();                                                //GamePlay-nél nincs szükség a Swing elemekre
        //duelingFates.setCursor(MainProcess.hiddenCursor);
        duelingFates.repaint();                                                 //üres Frame-et hagyunk
        StateManager.setStateChangedFalse();

    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        //ha épp nincs kirajzolva és ESC-et nyomtak
        if (key == KeyEvent.VK_ESCAPE & !escapeBefore) {

            escapePressed = true;

        }

        //ha épp ki van rajzolva akkor vissza az alap állapotba és elrejtés
        if(key == KeyEvent.VK_ESCAPE & escapeBefore) {

            escapePressed = false;
            escapeBefore = false;

        }
        /*
         * Moving players or doing actions if key is pressed
         * */
        switch(key){    //TODO: Tesztelni kell hogy client és host most külön mozog vagy egyszerre vagy most mi van?
            case(KeyEvent.VK_LEFT): hostPlayer.setLeft(true); break;
            case(KeyEvent.VK_RIGHT): hostPlayer.setRight(true); break;
            case(KeyEvent.VK_UP): hostPlayer.setJumping(true); break;
            case(KeyEvent.VK_SPACE): hostPlayer.setShooting(); break;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        //Enter esetén kilépünk
        if(key ==KeyEvent.VK_ENTER & escapeBefore){

            stateManager.setState(StateManager.States.MAINMENUSTATE);

        }
        /*
         * Stop Moving players or doing actions if key is released
         * */
        switch(key){
            case(KeyEvent.VK_LEFT): hostPlayer.setLeft(false); break;
            case(KeyEvent.VK_RIGHT): hostPlayer.setRight(false); break;
            case(KeyEvent.VK_UP): hostPlayer.setJumping(false); break;
            case(KeyEvent.VK_SPACE): hostPlayer.setShooting(); break;
        }

    }

    public void handleInput() {
        hostPlayer.setJumping(Keys.keyState[Keys.UP]);
        hostPlayer.setLeft(Keys.keyState[Keys.LEFT]);
        hostPlayer.setRight(Keys.keyState[Keys.RIGHT]);
        if(Keys.isPressed(Keys.SPACE)) hostPlayer.setShooting();
    }
}
