package com.DuelingFates.Main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){

        JFrame duelingFates;
        ImageIcon GameLogo;

        duelingFates = new JFrame("DuelingFates");                  //új Frame

        //fullscreen
        //duelingFates.setUndecorated(true);                              //nincs keret
        //duelingFates.setExtendedState(JFrame.MAXIMIZED_BOTH);           //max Vertical & Horizontal

        duelingFates.add(new MainProcess());                            //a Frame tartalma
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //kilépünk a Frame zárásával
        duelingFates.pack();                                            //tartalomhoz igazodik a Frame mérete
        duelingFates.setResizable(true);                                //nem lehet átméretezni
        duelingFates.setVisible(true);                                  //default esetben hidden lenne

        //Frame Icon beállítása
        try {
            GameLogo = new ImageIcon("DuelingFates/Sources/logo_DuelingFatesDF.png");     //Frame logo betöltése
            duelingFates.setIconImage(GameLogo.getImage());             //Icon beállítása - csak Image lehet az argumentum, ezért kell getImage()
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
