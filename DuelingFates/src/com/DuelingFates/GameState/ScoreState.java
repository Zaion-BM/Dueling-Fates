package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScoreState extends GameState implements MouseListener, ActionListener {

    //Gombok
    private final JButton buttonQuitToMenu = new JButton("Quit To Menu");
    private final JButton buttonRematch = new JButton("Rematch");
    private final JButton buttonSaveToFile = new JButton("Save Scores");

    //Háttér
    private final ImageIcon background = new ImageIcon("DuelingFates/Sources/background_JoinAndHost.png");
    private final JLabel backgroundLabel = new JLabel(background);

    //Labels
    private final JLabel winnerLabel = new JLabel("THE WINNER IS");



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

    public void showWinnderAndPlayerScores(String player1, String player2, String player3, String score1, String score2, String score3 ){

        //még nem tudom mi lesz itt

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
        winnerLabel.setForeground(MainMenuState.darkYellow);
        winnerLabel.setBounds((MainProcess.getGameWidth()/2)-370, (int)(MainProcess.getGameHeight()*0.15),700,70);

        layeredPane.setBounds(0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight());
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonQuitToMenu,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonRematch,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonSaveToFile,JLayeredPane.POPUP_LAYER);
        layeredPane.add(winnerLabel, JLayeredPane.POPUP_LAYER);

        duelingFates.setCursor(MainProcess.gameCursor);
        duelingFates.add(layeredPane);
        duelingFates.setPreferredSize(new Dimension(MainProcess.getGameWidth(), MainProcess.getGameHeight()));
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        duelingFates.setResizable(false);
        duelingFates.pack();
        duelingFates.setVisible(true);

        buttonQuitToMenu.setBounds((int)(MainProcess.getGameWidth()*0.05), (int)(MainProcess.getGameHeight()*0.86), 370,50);
        buttonQuitToMenu.addActionListener(this);
        buttonQuitToMenu.addMouseListener(this);

        buttonSaveToFile.setBounds((int)(MainProcess.getGameWidth()*0.5-165), (int)(MainProcess.getGameHeight()*0.86), 350,50);
        buttonSaveToFile.addActionListener(this);
        buttonSaveToFile.addMouseListener(this);

        buttonRematch.setBounds((int)(MainProcess.getGameWidth()*0.91)-200, (int)(MainProcess.getGameHeight()*0.86), 300,50);
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

            System.out.println("PrintPlayersAndScores() has been called!");

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() == buttonRematch){

            buttonRematch.setForeground(MainMenuState.darkGreen);

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

            buttonRematch.setForeground(Color.green);

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

            buttonRematch.setForeground(MainMenuState.darkGreen);

        }

        if(e.getSource() == buttonQuitToMenu){

            buttonQuitToMenu.setForeground(Color.WHITE);

        }

        if(e.getSource() == buttonSaveToFile){

            buttonSaveToFile.setForeground(Color.WHITE);

        }

    }

}
