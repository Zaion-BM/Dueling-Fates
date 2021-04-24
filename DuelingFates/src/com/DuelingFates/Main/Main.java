package com.DuelingFates.Main;

import javax.swing.*;

public class Main {

    public static void main(String[] args){

        JFrame duelingFates = new JFrame("DuelingFates");           //új Frame

        //fullscreen
        //duelingFates.setUndecorated(true);                              //nincs keret
        //duelingFates.setExtendedState(JFrame.MAXIMIZED_BOTH);           //max Vertical & Horizontal

        duelingFates.add(new MainProcess());                            //a Frame tartalma
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //kilépünk a Frame zárásával
        duelingFates.pack();                                            //tartalomhoz igazodik a Frame mérete
        duelingFates.setResizable(false);                               //nem lehet átméretezni
        duelingFates.setVisible(true);                                  //default esetben hidden lenne

    }
}
