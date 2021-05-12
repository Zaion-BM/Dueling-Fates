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

    //TODO list: elvileg ha csak a player saját adatait definiáljuk
    // és majd a Server ezeket megjeleníti, akkor nincs szükség az enemy UI-ra
    // ha nem, akkor csak két főre kell limitálnunk, és implementálni
    //TODO: lehet enemy és player helyett first place és second place kell, ezáltal egyszerűbb lehet

    //player adatok
    private String playerNameUI;
    private String playerAmmoUI;
    private String playerScoreUI;

    //enemy adatok
    private String enemyNameUI;
    private String enemyScoreUI;

    //egyéb adatok
    private String timerValue;
    private final String playerScoreLabel = "Your score:";
    private final String enemyScoreLabel;

    //ammo image
    private BufferedImage ammoImg;

    public HUD(Player player){

        //session során nem frissülő adatok
        enemyScoreLabel = GamePlayState.getClientPlayerName() + "'s score";
        enemyNameUI = GamePlayState.getClientPlayerName();

        try{

            ammoImg = ImageIO.read(new File("DuelingFates/Sources/gui_Ammo.png"));


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D graphics, Player player){

        //frissülő adatok lekérdezése
        int timer = (int)GamePlayState.getSeconds();

        if (GamePlayState.getSeconds() <= 9){

            timerValue = GamePlayState.getMinutes() + ":0" + timer;

        }
        else {
            timerValue = GamePlayState.getMinutes() + ":" + timer;
        }

        playerNameUI = player.getPlayerName();

        playerScoreUI = String.valueOf(player.getPlayerScore());    //"250 pts";
        playerAmmoUI = String.valueOf(player.getPlayerAmmoQty());

        enemyScoreUI = "getFromClient";

        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        graphics.drawImage(ammoImg,(int)(MainProcess.getGameWidth()*0.01),(int)(MainProcess.getGameHeight()*0.86),ammoImg.getWidth(),ammoImg.getHeight(),null);
        graphics.setFont(MainProcess.balooThambiFontSmall);
        graphics.setColor(MainMenuState.darkYellow);
        graphics.drawString(playerScoreLabel,(int)(MainProcess.getGameWidth()*0.01),(int)(MainProcess.getGameHeight()*0.04));       //0.04 7
        graphics.drawString(playerScoreUI,(int)(MainProcess.getGameWidth()*0.20),(int)(MainProcess.getGameHeight()*0.04));          //0.04 7

        graphics.setColor(Color.WHITE);
        graphics.drawString(enemyScoreLabel,(int)(MainProcess.getGameWidth()*0.01),(int)(MainProcess.getGameHeight()*0.08));        //0.08 11
        graphics.drawString(enemyScoreUI,(int)(MainProcess.getGameWidth()*0.20),(int)(MainProcess.getGameHeight()*0.08));           //0.08 11

        graphics.setFont(MainProcess.balooThambiFont);
        graphics.drawString(timerValue,(int)(MainProcess.getGameWidth()*0.92),(int)(MainProcess.getGameHeight()*0.07));             //0.05 8

        graphics.setColor(MainMenuState.darkYellow);
        graphics.setFont(MainProcess.balooThambiFontBig);
        graphics.drawString(playerAmmoUI,(int)(MainProcess.getGameWidth()*0.05),(int)(MainProcess.getGameHeight()*0.97));

        //Player adatok
        graphics.setFont(MainProcess.balooThambiFontVerySmall);
        graphics.setColor(Color.BLACK);
        FontMetrics fm = graphics.getFontMetrics();
        graphics.drawString(playerNameUI,(int)(player.getPositionX()- fm.stringWidth(playerNameUI) / 2), (int)(player.getPositionY())-65);

        graphics.setColor(MainMenuState.darkRed);
        graphics.fillRect((int)(player.getPositionX())-50, (int)(player.getPositionY())-57, player.getPlayerHealth(), 10);

        graphics.setColor(Color.BLACK);
        float borderSize = 2;
        graphics.setStroke(new BasicStroke(borderSize));
        graphics.drawRect((int)(player.getPositionX())-50, (int)(player.getPositionY())-57, 100, 10);


        /***Enemy adatok
        graphics.setFont(MainProcess.balooThambiFontVerySmall);
        graphics.setColor(Color.BLACK);
        graphics.drawString(enemyNameUI,(int)(player.getPositionX()- fm.stringWidth(playerNameUI) / 2), (int)(player.getPositionY())-65);

        graphics.setColor(MainMenuState.darkRed);
        graphics.fillRect((int)(player.getPositionX())-50, (int)(player.getPositionY())-57, player.getPlayerHealth(), 10);

        graphics.setColor(Color.BLACK);
        float borderSize = 2;
        graphics.setStroke(new BasicStroke(borderSize));
        graphics.drawRect((int)(player.getPositionX())-50, (int)(player.getPositionY())-57, 100, 10);
        **/

    }

}
