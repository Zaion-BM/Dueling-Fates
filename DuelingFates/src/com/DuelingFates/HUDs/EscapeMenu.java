package com.DuelingFates.HUDs;

import com.DuelingFates.Main.MainProcess;

import java.awt.*;

public class EscapeMenu{

    @SuppressWarnings("FieldCanBeLocal")
    private final String escapeMenu = "Do you want to quit?";
    @SuppressWarnings("FieldCanBeLocal")
    private final String optionYes  = "Yes! (ENTER)";
    @SuppressWarnings("FieldCanBeLocal")
    private final String optionNo   = "No! (ESCAPE)";

    public EscapeMenu(){

    }

    public void drawStringToCenter(Graphics2D graphics, String string, int heightPosition) {

        //adott font renderelési tulajdonságait tartalmazza
        FontMetrics fm = graphics.getFontMetrics();

        //lekérdezzük a szöveg szélességét, a font ismeretében és középre igazítjuk
        graphics.drawString(string, (MainProcess.getGameWidth() - fm.stringWidth(string)) / 2, heightPosition);

    }

    public void draw(Graphics2D graphics){

        //BETTER GRAPHICS, AntiAliasing bekapcsolásával
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        //háttér
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRoundRect(MainProcess.getGameWidth()/2-(int)(MainProcess.getGameWidth()*0.2),
                               MainProcess.getGameHeight()/2-(int)(MainProcess.getGameHeight()*0.2),
                                (int)(MainProcess.getGameWidth()*0.4),
                                (int)(MainProcess.getGameHeight()*0.35),40,40);

        //border effect egy másik rectangle-lel
        float borderSize = 10;
        graphics.setStroke(new BasicStroke(borderSize));

        graphics.setColor(Color.BLACK);
        graphics.drawRoundRect(MainProcess.getGameWidth()/2-(int)(MainProcess.getGameWidth()*0.2),
                               MainProcess.getGameHeight()/2-(int)(MainProcess.getGameHeight()*0.2),
                                (int)(MainProcess.getGameWidth()*0.4),
                                (int)(MainProcess.getGameHeight()*0.35),40,40);

        //szövegek
        graphics.setFont(MainProcess.balooThambiFont);
        graphics.setColor(Color.WHITE);

        drawStringToCenter(graphics,escapeMenu,MainProcess.getGameHeight()/2-(int)(MainProcess.getGameHeight()*0.09));
        graphics.setColor(new Color(220,20,60));
        graphics.drawString(optionNo, (int)(MainProcess.getGameWidth()*0.32),MainProcess.getGameHeight()/2+(int)(MainProcess.getGameHeight()*0.09));
        graphics.setColor(new Color(34,139,34));
        graphics.drawString(optionYes,(int)(MainProcess.getGameWidth()*0.52),MainProcess.getGameHeight()/2+(int)(MainProcess.getGameHeight()*0.09));


    }

}
