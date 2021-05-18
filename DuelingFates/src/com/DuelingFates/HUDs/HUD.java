package com.DuelingFates.HUDs;

import com.DuelingFates.GameState.GamePlayState;
import com.DuelingFates.GameState.MainMenuState;
import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class HUD {

    //ammo image
    private BufferedImage ammoImg;

    public HUD(){

        try{

            ammoImg = ImageIO.read(new File("DuelingFates/Sources/gui_Ammo.png"));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D graphics, Player player, Player enemyPlayer){

        //frissülő adatok lekérdezése
        String enemyScoreLabel = enemyPlayer.getPlayerName() + "'s score";
        
        String playerScoreUI = String.valueOf(player.getPlayerScore());
        String playerAmmoUI = String.valueOf(player.getPlayerAmmoQty());

        //enemy adatok
        String enemyScoreUI = String.valueOf(enemyPlayer.getPlayerScore());

        int timer = (int)GamePlayState.getSeconds();

        //egyéb adatok
        String timerValue;

        if (GamePlayState.getSeconds() <= 9){

            timerValue = GamePlayState.getMinutes() + ":0" + timer;

        }
        else {
            timerValue = GamePlayState.getMinutes() + ":" + timer;
        }

        //Ha elértük a célt, (de mondjuk azonos az állás és nincs vége)
        if (GamePlayState.getMinutes() >= MainProcess.getMatchDurationTemp()){
            timerValue = MainProcess.getMatchDurationTemp() + ":00";
        }

        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        graphics.drawImage(ammoImg,(int)(MainProcess.getGameWidth()*0.01),(int)(MainProcess.getGameHeight()*0.86),ammoImg.getWidth(),ammoImg.getHeight(),null);
        graphics.setFont(MainProcess.balooThambiFontSmall);
        graphics.setColor(MainMenuState.darkYellow);
        String playerScoreLabel = "Your score:";
        graphics.drawString(playerScoreLabel,(int)(MainProcess.getGameWidth()*0.01),(int)(MainProcess.getGameHeight()*0.07));       //0.04 7
        graphics.drawString(playerScoreUI,(int)(MainProcess.getGameWidth()*0.20),(int)(MainProcess.getGameHeight()*0.07));          //0.04 7

        graphics.setColor(Color.WHITE);
        graphics.drawString(enemyScoreLabel,(int)(MainProcess.getGameWidth()*0.01),(int)(MainProcess.getGameHeight()*0.11));        //0.08 11
        graphics.drawString(enemyScoreUI,(int)(MainProcess.getGameWidth()*0.20),(int)(MainProcess.getGameHeight()*0.11));           //0.08 11

        graphics.setFont(MainProcess.balooThambiFont);

        //Az utolsó percben vörösre vált
        if (GamePlayState.getMinutes() + 1 >= MainProcess.getMatchDurationTemp())
        {
            graphics.setColor(MainMenuState.darkRed);

        }
        graphics.drawString(timerValue,(int)(MainProcess.getGameWidth()*0.92),(int)(MainProcess.getGameHeight()*0.08));             //0.05 8

        graphics.setColor(MainMenuState.darkYellow);
        graphics.setFont(MainProcess.balooThambiFontBig);
        graphics.drawString(playerAmmoUI,(int)(MainProcess.getGameWidth()*0.05),(int)(MainProcess.getGameHeight()*0.97));


    }

}
