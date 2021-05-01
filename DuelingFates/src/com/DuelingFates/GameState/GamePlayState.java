package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.TileMap.TileMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GamePlayState extends GameState implements KeyListener {

    private BufferedImage background;
    private TileMap tileMap;
    @SuppressWarnings("FieldCanBeLocal")
    private final int tileSize = 64;

    //private final EscapeMenu escapeMenu = new EscapeMenu();

    private boolean escapePressed = false;

    public GamePlayState(StateManager stateManager){

        super(stateManager);
        StateManager.setStateChangedTrue();

        initialization();

    }

    @Override
    public void initialization() {

        tileMap = new TileMap(tileSize);

        try {

            background = ImageIO.read(new File("DuelingFates/Sources/BG_Widescreen/WS_CloudyForest_Background.png"));

        }catch (Exception e){
            e.printStackTrace();
        }

        tileMap.loadTilesFormFiles("DuelingFates/Sources/Tiles_Blue/");
        tileMap.loadTilesToMap("DuelingFates/Sources/Maps/CloudyForest.txt");

    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(background,0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight(),null);
        tileMap.draw(graphics);
        //System.out.println("Game graphics has been updated!");

        //TODO Escape Menu ESC lenyomásának érzékelése Keylistenerrel
        //escapeMenu.draw(graphics);

    }

    @Override
    public void update() {

        //inputkezelés

        //System.out.println("Game has been updated!");

    }

    @Override
    public void updateSwingUI(JFrame duelingFates,JLayeredPane layeredPane) {

        layeredPane.removeAll();                                                //GamePlay-nél nincs szükség a Swing elemekre
        duelingFates.setCursor(MainProcess.hiddenCursor);
        duelingFates.repaint();                                                 //üres Frame-et hagyunk
        StateManager.setStateChangedFalse();

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println("Escape Pressed");
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){

            escapePressed = true;

        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE & escapePressed){

            escapePressed = false;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
