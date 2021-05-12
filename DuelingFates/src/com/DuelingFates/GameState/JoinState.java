package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JoinState extends GameState implements ActionListener, MouseListener {

    //Gombok
    private final JButton buttonBack = new JButton("Back");
    private final JButton buttonJoin = new JButton("Join");

    //Háttér
    private final ImageIcon background = new ImageIcon("DuelingFates/Sources/background_JoinAndHost.png");
    private final JLabel backgroundLabel = new JLabel(background);

    //IP Address
    private final JTextField inputServerAddress = new JTextField();
    private final JLabel inputServerAddressLabel = new JLabel();


    public JoinState(StateManager stateManager){

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

    @Override
    public void updateSwingUI(JFrame duelingFates,JLayeredPane layeredPane) {

        layeredPane.removeAll();

        backgroundLabel.setBounds(0,0,background.getIconWidth(), background.getIconHeight());

        MainMenuState.setButtonStyle(buttonJoin);
        MainMenuState.setButtonStyle(buttonBack);

        inputServerAddress.setBackground(Color.darkGray);
        inputServerAddress.setFont(MainProcess.balooThambiFont);
        inputServerAddress.setForeground(Color.WHITE);
        inputServerAddress.setCaretColor(Color.WHITE);
        inputServerAddress.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        inputServerAddress.setEditable(false);
        inputServerAddress.setText("localhost:6868");
        inputServerAddress.setHorizontalAlignment(SwingConstants.CENTER);
        inputServerAddress.setBounds(MainProcess.getGameWidth()/2+75,(int)(MainProcess.getGameHeight()*0.30),400,80);

        inputServerAddressLabel.setFont(MainProcess.balooThambiFont);
        inputServerAddressLabel.setText("IP Address:");
        inputServerAddressLabel.setForeground(Color.WHITE);
        inputServerAddressLabel.setBounds(MainProcess.getGameWidth()/2-325,(int)(MainProcess.getGameHeight()*0.32),300,50);

        layeredPane.setBounds(0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight());
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(inputServerAddressLabel, JLayeredPane.POPUP_LAYER);
        layeredPane.add(inputServerAddress, JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonJoin,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonBack,JLayeredPane.POPUP_LAYER);

        duelingFates.setCursor(MainProcess.gameCursor);
        duelingFates.add(layeredPane);
        duelingFates.setPreferredSize(new Dimension(MainProcess.getGameWidth(), MainProcess.getGameHeight()));
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        duelingFates.setResizable(true);
        duelingFates.pack();
        duelingFates.setVisible(true);

        buttonBack.setBounds((int)(MainProcess.getGameWidth()*0.05), (int)(MainProcess.getGameHeight()*0.89), 200,50);
        buttonBack.addActionListener(this);
        buttonBack.addMouseListener(this);

        buttonJoin.setBounds((int)(MainProcess.getGameWidth()*0.95)-200, (int)(MainProcess.getGameHeight()*0.89), 200,50);
        buttonJoin.setForeground(Color.green);
        buttonJoin.addActionListener(this);
        buttonJoin.addMouseListener(this);

        duelingFates.repaint();
        StateManager.setStateChangedFalse();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == buttonJoin) {

            stateManager.setState(StateManager.States.GAMEPLAYSTATE);

        }
        if(e.getSource() == buttonBack) {

            stateManager.setState(StateManager.States.MAINMENUSTATE);

        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() == buttonJoin){

            buttonJoin.setForeground(MainMenuState.darkYellow);

        }

        if(e.getSource() == buttonBack){

            buttonBack.setForeground(MainMenuState.darkYellow);

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if(e.getSource() == buttonJoin){

            buttonJoin.setForeground(MainMenuState.darkGreen);

        }

        if(e.getSource() == buttonBack){

            buttonBack.setForeground(MainMenuState.darkRed);

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if(e.getSource() == buttonJoin){

            buttonJoin.setForeground(Color.green);

        }

        if(e.getSource() == buttonBack){

            buttonBack.setForeground(Color.WHITE);

        }

    }

}
