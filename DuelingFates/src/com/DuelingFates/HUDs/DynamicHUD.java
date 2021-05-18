package com.DuelingFates.HUDs;

import com.DuelingFates.GameState.MainMenuState;
import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.Player;

import java.awt.*;

public class DynamicHUD {

    public DynamicHUD(){

    }

    public void draw(Graphics2D graphics, Player player, Player enemyPlayer){

        //player adatok
        String playerNameUI = player.getPlayerName();
        String enemyNameUI = enemyPlayer.getPlayerName();

        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        //Player adatok
        graphics.setFont(MainProcess.balooThambiFontVerySmall);
        graphics.setColor(Color.BLACK);
        FontMetrics fm = graphics.getFontMetrics();
        graphics.drawString(playerNameUI,(int)(player.getPositionX() - fm.stringWidth(playerNameUI) / 2), (int)(player.getPositionY())-65);

        graphics.setColor(MainMenuState.darkRed);
        graphics.fillRect((int)(player.getPositionX())-50,
                (int)(player.getPositionY())-57,
                player.getPlayerHealth(), 10);

        graphics.setColor(Color.BLACK);
        float borderSize = 2;
        graphics.setStroke(new BasicStroke(borderSize));
        graphics.drawRect((int)(player.getPositionX())-50,
                (int)(player.getPositionY())-57,
                100,
                10);

        //Enemy adatok
        graphics.setFont(MainProcess.balooThambiFontVerySmall);
        graphics.setColor(Color.BLACK);
        graphics.drawString(enemyNameUI,
                (int)(enemyPlayer.getPositionX() - fm.stringWidth(enemyNameUI) / 2),
                (int)(enemyPlayer.getPositionY())-65);

        graphics.setColor(MainMenuState.darkRed);
        graphics.fillRect((int)(enemyPlayer.getPositionX())-50,
                (int)(enemyPlayer.getPositionY())-57,
                enemyPlayer.getPlayerHealth(), 10);

        graphics.setColor(Color.BLACK);
        graphics.setStroke(new BasicStroke(borderSize));
        graphics.drawRect((int)(enemyPlayer.getPositionX())-50,
                (int)(enemyPlayer.getPositionY())-57,
                100,
                10);

    }

}
