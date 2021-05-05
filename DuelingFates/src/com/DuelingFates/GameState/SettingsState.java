package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SettingsState extends GameState implements ActionListener, MouseListener {

    //Gombok
    private final JButton buttonBack = new JButton("Back");
    private final JButton buttonSave = new JButton("Save");

    //Háttér
    private final ImageIcon background = new ImageIcon("DuelingFates/Sources/background_MainAndSettings.png");
    private final JLabel backgroundLabel = new JLabel(background);

    //Név megadása inputmező és a hozzá tartozó label
    private final JTextField playerName = new JTextField();
    private final JLabel playerNameLabel = new JLabel();

    //JRadioButton images beolvasása: default és selected állapot
    private final ImageIcon pirateSelectedImg = new ImageIcon("DuelingFates/Sources/char_PirateDeckhand/PirateDeckhand_jumpingAndFalling3x.png");
    private final ImageIcon pirateImg = new ImageIcon("DuelingFates/Sources/char_PirateDeckhand/PirateDeckhand_standing3x.png");
    private final ImageIcon possessedSelectedImg = new ImageIcon("DuelingFates/Sources/char_PossessedArmor/PossessedArmor_jumpingAndFalling3x.png");
    private final ImageIcon possessedImg = new ImageIcon("DuelingFates/Sources/char_PossessedArmor/PossessedArmor_standing3x.png");

    //JRadioButtons
    private final JRadioButton pirateJRadioButton = new JRadioButton(pirateImg);
    private final JRadioButton possessedJRadioButton = new JRadioButton(possessedImg);

    //ButtonGroup és a hozzá tartozó label
    private final ButtonGroup characterSelection = new ButtonGroup();
    private final JLabel characterSelectionLabel = new JLabel();

    //a kiválasztott charactert tároljuk benne
    public static String selectedChar;

    public SettingsState(StateManager stateManager){

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
    public void updateSwingUI(JFrame duelingFates, JLayeredPane layeredPane) {

        layeredPane.removeAll();

        backgroundLabel.setBounds(0,0,background.getIconWidth(), background.getIconHeight());

        MainMenuState.setButtonStyle(buttonSave);
        MainMenuState.setButtonStyle(buttonBack);

        playerName.setToolTipText("Enter only 16 characters!");
        playerName.setBackground(Color.darkGray);
        playerName.setFont(MainProcess.balooThambiFont);
        playerName.setForeground(Color.WHITE);
        playerName.setCaretColor(Color.WHITE);
        playerName.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        playerName.setText(MainProcess.getPlayerNameTemp());
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerName.setBounds(MainProcess.getGameWidth()/2+75,(int)(MainProcess.getGameHeight()*0.30),400,80);

        playerNameLabel.setFont(MainProcess.balooThambiFont);
        playerNameLabel.setText("Player name:");
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setBounds(MainProcess.getGameWidth()/2-370,(int)(MainProcess.getGameHeight()*0.32),350,52);

        characterSelectionLabel.setFont(MainProcess.balooThambiFont);
        characterSelectionLabel.setText("Select character");
        characterSelectionLabel.setForeground(Color.WHITE);
        characterSelectionLabel.setBounds(MainProcess.getGameWidth()/2-205,(int)(MainProcess.getGameHeight()*0.42),450,50);

        pirateJRadioButton.setIcon(pirateImg);
        pirateJRadioButton.setName("PirateDeckhand");
        pirateJRadioButton.addActionListener(this);
        characterSelection.add(pirateJRadioButton);
        pirateJRadioButton.setBounds((int)(MainProcess.getGameWidth()*0.52),(int)(MainProcess.getGameHeight()*0.5),pirateImg.getIconWidth(),pirateImg.getIconHeight());
        pirateJRadioButton.setOpaque(false);
        pirateJRadioButton.setSelectedIcon(pirateSelectedImg);

        possessedJRadioButton.setIcon(possessedImg);
        possessedJRadioButton.setName("PossessedArmor");
        possessedJRadioButton.addActionListener(this);
        characterSelection.add(possessedJRadioButton);
        possessedJRadioButton.setBounds((int)(MainProcess.getGameWidth()*0.43),(int)(MainProcess.getGameHeight()*0.5),possessedImg.getIconWidth(),possessedImg.getIconHeight());
        possessedJRadioButton.setOpaque(false);
        possessedJRadioButton.setSelectedIcon(possessedSelectedImg);


        //az eltárolt érték alapján megjelenítjük az éppen kiválasztott karaktert
        switch (MainProcess.getCharacterTemp()){

            case "PirateDeckhand": pirateJRadioButton.setSelected(true);
                break;
            case "PossessedArmor": possessedJRadioButton.setSelected(true);
                break;

        }

        layeredPane.setBounds(0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight());
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(characterSelectionLabel,JLayeredPane.POPUP_LAYER);
        layeredPane.add(possessedJRadioButton,JLayeredPane.POPUP_LAYER);
        layeredPane.add(pirateJRadioButton,JLayeredPane.POPUP_LAYER);
        layeredPane.add(playerNameLabel, JLayeredPane.POPUP_LAYER);
        layeredPane.add(playerName, JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonSave,JLayeredPane.POPUP_LAYER);
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

        buttonSave.setBounds((int)(MainProcess.getGameWidth()*0.95)-200, (int)(MainProcess.getGameHeight()*0.89), 200,50);
        buttonSave.addActionListener(this);
        buttonSave.addMouseListener(this);

        duelingFates.repaint();
        StateManager.setStateChangedFalse();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == buttonSave) {

            if(pirateJRadioButton.isSelected()){

                selectedChar = pirateJRadioButton.getName();

            }
            else if(possessedJRadioButton.isSelected()){

                selectedChar = possessedJRadioButton.getName();

            }
            else if (!pirateJRadioButton.isSelected() & !possessedJRadioButton.isSelected()){            //ha egyik sincs kiválasztva

                selectedChar = MainProcess.getCharacterTemp();

            }
            stateManager.setState(StateManager.States.MAINMENUSTATE);

            //ha 16 karakternél hosszabb akor nem mentjük el
            if(playerName.getText().length() <= 16) {
                MainProcess.setPlayerNameTemp(playerName.getText());
            }


            MainProcess.setCharacterTemp(selectedChar);

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

        if(e.getSource() == buttonBack){

            buttonBack.setForeground(MainMenuState.darkYellow);

        }

        if(e.getSource() == buttonSave){

            buttonSave.setForeground(MainMenuState.darkYellow);

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if(e.getSource() == buttonBack){

            buttonBack.setForeground(MainMenuState.darkRed);

        }

        if(e.getSource() == buttonSave){

            buttonSave.setForeground(MainMenuState.darkRed);

        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

        if(e.getSource() == buttonBack){

            buttonBack.setForeground(Color.WHITE);

        }

        if(e.getSource() == buttonSave){

            buttonSave.setForeground(Color.WHITE);

        }

    }

}
