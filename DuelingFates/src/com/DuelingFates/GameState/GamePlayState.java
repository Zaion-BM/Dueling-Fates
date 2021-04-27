package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GamePlayState extends GameState {

    private BufferedImage background;

    public GamePlayState(StateManager stateManager){

        super(stateManager);

        try {

            background = ImageIO.read(new File("DuelingFates/Sources/background_MainAndSettings.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        StateManager.stateChanged = true;

    }

    @Override
    public void initialization() {

    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(background,0,0, MainProcess.gameWidth, MainProcess.gameHeight,null);
        System.out.println("Game graphics has been updated!");

    }

    @Override
    public void update() {

        System.out.println("Game has been updated!");

    }

    @Override
    public void updateSwingUI(JFrame duelingFates,JLayeredPane layeredPane) {

        layeredPane.removeAll();                                                //GamePlay-nél nincs szükség a Swing elemekre
        duelingFates.repaint();                                                 //üres Frame-et hagyunk
        StateManager.stateChanged = false;

    }


}
