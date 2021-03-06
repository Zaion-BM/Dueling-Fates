package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Music.JukeBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuState extends GameState implements ActionListener, MouseListener {

    //Gombok
    private final JButton buttonJoin = new JButton("Join");
    private final JButton buttonHost = new JButton("Host");
    private final JButton buttonSettings = new JButton("Settings");
    private final JButton buttonQuit = new JButton("Quit");

    //Háttér
    private final ImageIcon background = new ImageIcon("DuelingFates/Sources/background_MainAndSettings.png");
    private final JLabel backgroundLabel = new JLabel(background);

    //Logo
    private final ImageIcon logoImage = new ImageIcon("DuelingFates/Sources/logo_DuelingFates.png");
    private final JLabel logoImageLabel = new JLabel(new ImageIcon(logoImage.getImage().getScaledInstance(
                                          (int)(logoImage.getIconWidth()/3.5),(int)(logoImage.getIconHeight()/3.5), Image.SCALE_SMOOTH)));

    //színek statikusan definiálva, hogy elérjük őket a többi állapotból
    public static final Color darkRed = new Color(160,28,36);
    public static final Color darkYellow = new Color(255,193,8);
    public static final Color darkGreen = new Color(0,128,0);

    public MainMenuState(StateManager stateManager){

        super(stateManager);                                    //őskonstruktor meghívása
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
        logoImageLabel.setBounds((int)(MainProcess.getGameWidth()*-0.055),
                                 (int)(MainProcess.getGameHeight()*0.75),
                                 logoImage.getIconWidth()/2,
                                 logoImage.getIconHeight()/2);      //0.775

        setButtonStyle(buttonJoin);
        setButtonStyle(buttonHost);
        setButtonStyle(buttonSettings);
        setButtonStyle(buttonQuit);

        layeredPane.setBounds(0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight());
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(logoImageLabel, JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonJoin,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonHost,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonSettings,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonQuit,JLayeredPane.POPUP_LAYER);

        //majd fullscreen esetben
        duelingFates.setCursor(MainProcess.gameCursor);
        duelingFates.add(layeredPane);
        duelingFates.setPreferredSize(new Dimension(MainProcess.getGameWidth(), MainProcess.getGameHeight()));
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        duelingFates.setResizable(true);
        duelingFates.pack();
        duelingFates.setVisible(true);
        duelingFates.setFocusable(true);
        duelingFates.requestFocus();
        duelingFates.requestFocusInWindow();

        buttonJoin.setBounds((MainProcess.getGameWidth()/2)-100, (int)(MainProcess.getGameHeight()*0.32), 200,50);
        buttonJoin.addActionListener(this);
        buttonJoin.addMouseListener(this);

        buttonHost.setBounds((MainProcess.getGameWidth()/2)-100, (int)(MainProcess.getGameHeight()*0.39), 200,50);
        buttonHost.addActionListener(this);
        buttonHost.addMouseListener(this);

        buttonSettings.setBounds((MainProcess.getGameWidth()/2)-150, (int)(MainProcess.getGameHeight()*0.46), 300,53);
        buttonSettings.addActionListener(this);
        buttonSettings.addMouseListener(this);

        buttonQuit.setBounds((MainProcess.getGameWidth()/2)-100, (int)(MainProcess.getGameHeight()*0.53), 200,50);
        buttonQuit.addActionListener(this);
        buttonQuit.addMouseListener(this);

        duelingFates.repaint();

        StateManager.setStateChangedFalse();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == buttonJoin) {

            stateManager.setState(StateManager.States.JOINSTATE);

        }

        if(e.getSource() == buttonHost){

            stateManager.setState(StateManager.States.HOSTSTATE);
        }

        if(e.getSource() == buttonSettings){

            stateManager.setState(StateManager.States.SETTINGSTATE);

        }

        if(e.getSource() == buttonQuit){

            System.out.println("System.exit(0) has been called");
            System.exit(0);

        }

    }

    //a többi állapotból elérhető, mely a buttonok formázását leegyszerűsíti
    public static void setButtonStyle(JButton button){

        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setBackground(null);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(MainProcess.balooThambiFont);
        button.setForeground(Color.WHITE);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() == buttonJoin){

            JukeBox.play("menuselect");
            buttonJoin.setForeground(darkYellow);

        }

        if(e.getSource() == buttonHost){

            JukeBox.play("menuselect");
            buttonHost.setForeground(darkYellow);

        }

        if(e.getSource() == buttonSettings){

            JukeBox.play("menuselect");
            buttonSettings.setForeground(darkYellow);

        }

        if(e.getSource() == buttonQuit) {

            JukeBox.play("menuselect");
            buttonQuit.setForeground(darkYellow);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        JukeBox.play("menuoption");

        if(e.getSource() == buttonJoin){

            buttonJoin.setForeground(darkRed);

        }

        if(e.getSource() == buttonHost){

            buttonHost.setForeground(darkRed);

        }

        if(e.getSource() == buttonSettings){

            buttonSettings.setForeground(darkRed);

        }

        if(e.getSource() == buttonQuit) {

            buttonQuit.setForeground(darkRed);

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {


        if(e.getSource() == buttonJoin){

            buttonJoin.setForeground(Color.WHITE);

        }

        if(e.getSource() == buttonHost){

            buttonHost.setForeground(Color.WHITE);

        }

        if(e.getSource() == buttonSettings){

            buttonSettings.setForeground(Color.WHITE);

        }

        if(e.getSource() == buttonQuit){

            buttonQuit.setForeground(Color.WHITE);

        }

    }

}
