package com.DuelingFates.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainMenuState extends GameState{

    public final int gameWidth = 1920;
    public final int gameHeight = 1080;

    private BufferedImage background;
    private BufferedImage logoImage;

    public MainMenuState(StateManager stateManager){
        super(stateManager);                                    //őskonstruktor meghívása
        //System.out.println(stateManager.currentState);
        try{
            background = ImageIO.read(new File("DuelingFates/Sources/background_MainAndSettings.png"));
            logoImage = ImageIO.read(new File("DuelingFates/Sources/logo_DuelingFates.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialization() {

    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(50, 50, 16, 9);

        graphics.drawImage(background,0,0, gameWidth, gameHeight,null);
        graphics.drawImage(logoImage, 40, 870, logoImage.getWidth()/3, logoImage.getHeight()/3, null);


    }

    @Override
    public void update() {

    }
}
