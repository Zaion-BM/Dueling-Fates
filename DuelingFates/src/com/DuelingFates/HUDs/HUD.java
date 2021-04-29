package com.DuelingFates.HUDs;

import com.DuelingFates.GameState.MainMenuState;
import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class HUD {

    //TODO list: evlileg ha csak a player saját adatait definiáljuk
    //és majd a erver ezeket megjleneíti, akkor nincs szükség az enemy UI-ra
    //ha nem, akkor csak két főre kell limitálnunk, és implementálni

    //player adatok
    private String playerNameUI;
    private String playerAmmoUI;
    private String playerScoreUI;


    //egyéb adatok
    private String timerValue;
    private final String playerScoreLabel = new String("Your score:");

    //ammo image
    private BufferedImage ammoImg;

    public HUD(Player p){

        this.timerValue = "getTimerValue()";
        this.playerNameUI = "getPlayerName()";
        this.playerScoreUI = "getPlayerScore()";
        this.playerAmmoUI = "getPlayerAmmo()";

        try{

            ammoImg = ImageIO.read(new File("DuelingFates/Sources/gui_Ammo.png"));


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //TODO: Ha a playert, mindig átadjuk argumentumként akkor az adatok könnyen lekérdezhetők
    public void draw(Graphics2D graphics){

        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        graphics.drawImage(ammoImg,(int)(MainProcess.getGameWidth()*0.05),(int)(MainProcess.getGameHeight()*0.90),ammoImg.getWidth(),ammoImg.getHeight(),null);
        graphics.setFont(MainProcess.BalooThambiFont);
        graphics.setColor(MainMenuState.darkYellow);
        graphics.drawString(playerAmmoUI,(int)(MainProcess.getGameWidth()*0.10),(int)(MainProcess.getGameHeight()*0.90));
        graphics.drawString(playerScoreLabel,(int)(MainProcess.getGameWidth()*0.05),(int)(MainProcess.getGameHeight()*0.05));
        graphics.drawString(playerScoreUI,(int)(MainProcess.getGameWidth()*0.25),(int)(MainProcess.getGameHeight()*0.05));

        graphics.setColor(Color.WHITE);
        graphics.drawString(timerValue,(int)(MainProcess.getGameWidth()*0.05),(int)(MainProcess.getGameHeight()*0.95));

        /**graphics.drawString(playerNameUI,(int)getPlayerPositionX(), (int)getPlayerPositionY()-80);

         graphics.setColor(MainMenuState.darkRed);
         graphics.fillRect(getPlayerHP(), 40, (int)getPlayerPositionX(), (int)getPlayerPositionY-50());

         graphics.setColor(Color.BLACK);
         graphics.drawRect((int)(MainProcess.getGameWidth()*0.06), 40, (int)getPlayerPositionX(), (int)getPlayerPositionY-50());

         **/

    }

}
