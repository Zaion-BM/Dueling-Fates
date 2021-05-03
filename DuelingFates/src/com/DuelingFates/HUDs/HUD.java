package com.DuelingFates.HUDs;

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
    private final String playerNameUI;
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
        enemyScoreLabel = "getEnemyName()";
        playerNameUI = "getPlayerNamer";    //NEM KELL SZERINTEM
        enemyNameUI = "getEnemyName()";

        try{

            ammoImg = ImageIO.read(new File("DuelingFates/Sources/gui_Ammo.png"));


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //TODO: Ha a playert, mindig átadjuk argumentumként akkor az adatok könnyen lekérdezhetők
    public void draw(Graphics2D graphics){

        //frissülő adatok lekérdezése
        timerValue = "2:30s";

        playerScoreUI = "250 pts";
        playerAmmoUI = "11";

        enemyScoreUI = "140pts";

        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        graphics.drawImage(ammoImg,(int)(MainProcess.getGameWidth()*0.01),(int)(MainProcess.getGameHeight()*0.86),ammoImg.getWidth(),ammoImg.getHeight(),null);
        graphics.setFont(MainProcess.balooThambiFontSmall);
        graphics.setColor(MainMenuState.darkYellow);
        graphics.drawString(playerScoreLabel,(int)(MainProcess.getGameWidth()*0.01),(int)(MainProcess.getGameHeight()*0.04));
        graphics.drawString(playerScoreUI,(int)(MainProcess.getGameWidth()*0.20),(int)(MainProcess.getGameHeight()*0.04));

        graphics.setColor(Color.WHITE);
        graphics.drawString(enemyScoreLabel,(int)(MainProcess.getGameWidth()*0.01),(int)(MainProcess.getGameHeight()*0.08));
        graphics.drawString(enemyScoreUI,(int)(MainProcess.getGameWidth()*0.20),(int)(MainProcess.getGameHeight()*0.08));

        graphics.setFont(MainProcess.balooThambiFont);
        graphics.drawString(timerValue,(int)(MainProcess.getGameWidth()*0.92),(int)(MainProcess.getGameHeight()*0.05));

        graphics.setColor(MainMenuState.darkYellow);
        graphics.setFont(MainProcess.balooThambiFontBig);
        graphics.drawString(playerAmmoUI,(int)(MainProcess.getGameWidth()*0.05),(int)(MainProcess.getGameHeight()*0.97));


        graphics.setColor(MainMenuState.darkRed);
        graphics.fillRect((int)(MainProcess.getGameWidth()*0.3), (int)(MainProcess.getGameHeight()*0.5),50, 12);

        graphics.setColor(Color.BLACK);
        float borderSize = 2;
        graphics.setStroke(new BasicStroke(borderSize));
        graphics.drawRect((int)(MainProcess.getGameWidth()*0.3), (int)(MainProcess.getGameHeight()*0.5),150, 12);


        //Player adatok
        /**graphics.drawString(playerNameUI,(int)(getPlayerPositionX()), (int)(getPlayerPositionY())-80);

         graphics.setColor(MainMenuState.darkRed);
         graphics.fillRect((int)(getPlayerPositionX()), (int)(getPlayerPositionY())-50, getPlayerHealth(), 12);

         graphics.setColor(Color.BLACK);
         float borderSize = 2;
         graphics.setStroke(new BasicStroke(borderSize));
         graphics.drawRect(getPlayerPositionX()), (int)(getPlayerPositionY())-50, 150, 12);

         **/

        //Enemy adatok
        /**graphics.drawString(enemyNameUI,(int)(getEnemyPositionX()), (int)(getEnemyPositionY())-80);

         graphics.setColor(MainMenuState.darkRed);
         graphics.fillRect((int)(getEnemyPositionX()), (int)(getEnemyPositionY())-50, getEnemyHealth(), 12);

         graphics.setColor(Color.BLACK);
         float borderSize = 2;
         graphics.setStroke(new BasicStroke(borderSize));
         graphics.drawRect(getEnemyPositionX()), (int)(getEnemyositionY())-50, 150, 12);


         **/

    }

}
