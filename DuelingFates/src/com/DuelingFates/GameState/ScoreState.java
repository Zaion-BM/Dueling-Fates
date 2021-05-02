package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreState extends GameState implements MouseListener, ActionListener {

    //Gombok
    private final JButton buttonQuitToMenu = new JButton("Quit To Menu");
    private final JButton buttonRematch = new JButton("Rematch");
    private final JButton buttonSaveToFile = new JButton("Save Scores");

    //Háttér
    private final ImageIcon background = new ImageIcon("DuelingFates/Sources/background_Score2.png");
    private final JLabel backgroundLabel = new JLabel(background);

    //Labels
    private final JLabel winnerLabel = new JLabel("THE WINNER IS");
    private final JLabel player1Label = new JLabel();
    private final JLabel player2Label = new JLabel();

    //player adatok
    private String player1Score;
    private String player2Score;

    //file adatok
    //nem a legjobb megoldás, Windows API-t használva biztonságosabb lenne
    private final static File resultsTxt = new File("C:/Users/" + System.getProperty("user.name") + "/Desktop/results.txt");

    //formátum a fájl kiírásához
    SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public ScoreState(StateManager stateManager){

        super(stateManager);
        StateManager.setStateChangedTrue();

    }

    @Override
    public void initialization() {

    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void update() {

    }

    public void printPlayersAndScores(String results){

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(resultsTxt, true));

            writer.write(results);
            writer.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSwingUI(JFrame duelingFates,JLayeredPane layeredPane) {

        layeredPane.removeAll();

        backgroundLabel.setBounds(0,0,background.getIconWidth(), background.getIconHeight());

        MainMenuState.setButtonStyle(buttonRematch);
        MainMenuState.setButtonStyle(buttonQuitToMenu);
        MainMenuState.setButtonStyle(buttonSaveToFile);

        winnerLabel.setFont(new Font("Arial Black",Font.BOLD, 70));
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setForeground(Color.WHITE);
        winnerLabel.setBounds((MainProcess.getGameWidth()/2)-370, (int)(MainProcess.getGameHeight()*0.13),700,70);

        JLabel winnerName = new JLabel("getWinnerPlayer()");
        winnerName.setFont(new Font("Arial Black",Font.BOLD, 60));
        winnerName.setHorizontalAlignment(SwingConstants.CENTER);
        winnerName.setForeground(MainMenuState.darkYellow);
        winnerName.setBounds((MainProcess.getGameWidth()/2)-370, (int)(MainProcess.getGameHeight()*0.25),700,90);

        player1Score = "getName(): + getScore()";
        player1Label.setText(player1Score);

        player2Score = "getName(): + getScore()";
        player2Label.setText(player2Score);

        player1Label.setHorizontalAlignment(SwingConstants.CENTER);
        player1Label.setFont(MainProcess.BalooThambiFont);
        player1Label.setForeground(Color.BLACK);
        player1Label.setBounds((MainProcess.getGameWidth()/2)-370, (int)(MainProcess.getGameHeight()*0.49),700,60);

        player2Label.setHorizontalAlignment(SwingConstants.CENTER);
        player2Label.setFont(MainProcess.BalooThambiFont);
        player2Label.setForeground(Color.BLACK);
        player2Label.setBounds((MainProcess.getGameWidth()/2)-370, (int)(MainProcess.getGameHeight()*0.56),700,60);


        layeredPane.setBounds(0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight());
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonQuitToMenu,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonRematch,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonSaveToFile,JLayeredPane.POPUP_LAYER);
        layeredPane.add(winnerLabel, JLayeredPane.POPUP_LAYER);
        layeredPane.add(winnerName,JLayeredPane.POPUP_LAYER);
        layeredPane.add(player1Label,JLayeredPane.POPUP_LAYER);
        layeredPane.add(player2Label,JLayeredPane.POPUP_LAYER);


        duelingFates.setCursor(MainProcess.gameCursor);
        duelingFates.add(layeredPane);
        duelingFates.setPreferredSize(new Dimension(MainProcess.getGameWidth(), MainProcess.getGameHeight()));
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        duelingFates.setResizable(false);
        duelingFates.pack();
        duelingFates.setVisible(true);

        buttonQuitToMenu.setBounds((int)(MainProcess.getGameWidth()*0.05), (int)(MainProcess.getGameHeight()*0.91), 370,50);
        buttonQuitToMenu.addActionListener(this);
        buttonQuitToMenu.addMouseListener(this);

        buttonSaveToFile.setBounds((int)(MainProcess.getGameWidth()*0.5-165), (int)(MainProcess.getGameHeight()*0.91), 350,50);
        buttonSaveToFile.addActionListener(this);
        buttonSaveToFile.addMouseListener(this);

        buttonRematch.setBounds((int)(MainProcess.getGameWidth()*0.91)-200, (int)(MainProcess.getGameHeight()*0.91), 300,50);
        buttonRematch.setForeground(Color.green);
        buttonRematch.addActionListener(this);
        buttonRematch.addMouseListener(this);

        duelingFates.repaint();

        StateManager.setStateChangedFalse();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == buttonRematch) {

            stateManager.setState(StateManager.States.GAMEPLAYSTATE);

        }

        if(e.getSource() == buttonQuitToMenu) {

            stateManager.setState(StateManager.States.MAINMENUSTATE);

        }

        if(e.getSource() == buttonSaveToFile) {

            Date time = new Date();
            String results = (dateStyle.format(time) + "\t" + player1Score + " \t" + player2Score + "\n");
            printPlayersAndScores(results);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() == buttonRematch){

            buttonRematch.setForeground(MainMenuState.darkYellow);

        }

        if(e.getSource() == buttonQuitToMenu){

            buttonQuitToMenu.setForeground(MainMenuState.darkYellow);

        }

        if(e.getSource() == buttonSaveToFile){

            buttonSaveToFile.setForeground(MainMenuState.darkYellow);

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == buttonSaveToFile){

            buttonSaveToFile.setForeground(Color.WHITE);

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if(e.getSource() == buttonRematch){

            buttonRematch.setForeground(MainMenuState.darkGreen);

        }

        if(e.getSource() == buttonQuitToMenu){

            buttonQuitToMenu.setForeground(MainMenuState.darkRed);

        }

        if(e.getSource() == buttonSaveToFile){

            buttonSaveToFile.setForeground(MainMenuState.darkRed);

        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

        if(e.getSource() == buttonRematch){

            buttonRematch.setForeground(Color.GREEN);

        }

        if(e.getSource() == buttonQuitToMenu){

            buttonQuitToMenu.setForeground(Color.WHITE);

        }

        if(e.getSource() == buttonSaveToFile){

            buttonSaveToFile.setForeground(Color.WHITE);

        }

    }

}
