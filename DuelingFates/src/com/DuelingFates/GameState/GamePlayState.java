package com.DuelingFates.GameState;

import com.DuelingFates.HUDs.EscapeMenu;
import com.DuelingFates.HUDs.HUD;
import com.DuelingFates.Handlers.Keys;
import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.*;
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

    private Player hostPlayer;          //TODO: miért kell két külön player? gombok nyomkodásánál mi lesz?
    private Player clientPlayer;
    private PlayerAnimation hostAnimation;

    private PlayerInputHandler hostInput;
    private HUD hud;

    //egyellőre külön kezeljük, mert a threadelés nem syncronizált alapból
    private ArrayList<Projectile> hostBullets;
    private ArrayList<Projectile> clientBullets;
    private Projectile hostProjectile;

    private final EscapeMenu escapeMenu = new EscapeMenu();
    public boolean escapePressed = false;
    public boolean escapeBefore = false;

    private GameObjectRenderer gameObjectRenderer = new GameObjectRenderer();

    public GamePlayState(StateManager stateManager){

        super(stateManager);
        StateManager.setStateChangedTrue();

        initialization();

    }

    @Override
    public void initialization() {

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

        //Player init
        hostPlayer = new Player(tileMap);
        hostAnimation = new PlayerAnimation(hostPlayer);

        //hostProjectile=new Projectile(tileMap,); //TODO: projectile

        //TODO player location egy játékos halálakor majd szintén meghívunk
        //TODO start timer, ami lehet metódus, itt végtelen ciklusban várunk a kliens csatlakozására és utána indul a meccs
        //TODO projectile tömb létrehozása

    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(background,0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight(),null);
        tileMap.draw(graphics);
        //System.out.println("Game graphics has been updated!");

        hud.draw(graphics, hostPlayer);

        if (escapePressed) {
            escapeMenu.draw(graphics);
            escapeBefore = true;            //engedélyezzük az eltüntetés lehetőségét
        }

        //TODO projectile, kirajzolás for ciklussal, arraylisten végiglépkedünk
        //TODO Objectrenderer meghívása amiben a kirajzolást definiáltuk

        //Player draw
        gameObjectRenderer.drawPlayer(graphics, hostPlayer);
        //Projectile draw
       // gameObjectRenderer.drawProjectile(graphics,hostProjectile);

    }

    @Override
    public void update() {
        //System.out.println("Game has been updated!");

        //Player update
        hostPlayer.update();
        hostAnimation.updateAnimation(hostPlayer);
        //hostAnimation.updateAnimationPossessed(hostPlayer);

       // hostProjectile.update();

        //TODO player mozgás, projectile update for ciklusban, ha már nem releváns, akkor helyére NULL
        // ha intersects vagyis érintkezik valamivel és ha nincs a mapon,

    }

    //TODO match start, match finish, player death esemény,
    //TODO ezenkívűl itemek és fegyverek spawnolása

    @Override
    public void updateSwingUI(JFrame duelingFates,JLayeredPane layeredPane) {

        duelingFates.addKeyListener(new PlayerInputHandler(hostPlayer));
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
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        //Enter esetén kilépünk
        if(key ==KeyEvent.VK_ENTER & escapeBefore){

            stateManager.setState(StateManager.States.MAINMENUSTATE);

        }

    }
}
