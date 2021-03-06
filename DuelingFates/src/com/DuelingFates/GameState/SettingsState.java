package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Music.JukeBox;
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

    //Rules
    private final JTextArea rulesTooltip = new JTextArea();
    private final JLabel rulesInfo = new JLabel();


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

        rulesInfo.setText(("Do you know the rules?"));
        rulesInfo.setFont(MainProcess.balooThambiFontVerySmall);
        rulesInfo.setBounds(10,10,540,30);
        rulesInfo.setForeground(Color.WHITE);
        rulesInfo.addMouseListener(this);

        rulesTooltip.setText("Do you know the rules?\nMove with the Arrow Keys and switch weapons with 1 2 3!" +
                "\nShoot with Space and press O and P for taunts!\n" +
                "If you die you don't lose points, but if you fall you lose 50!\n"+
                "Ammo pickups give you 10 points and bullets, Health pickups give you 5 points and 30 HP!\n" +
                "Each damage done to enemy gives you 1 point. Default Weapon costs 1 bullet, Undertaker: 2, Magnum: 5!\n" +
                "Default Weapon does 10 damage, the Undertaker does 20 and the Magnum does 40!");

        rulesTooltip.setFont(MainProcess.balooThambiFontVerySmall);
        rulesTooltip.setEditable(false);
        rulesTooltip.setOpaque(false);
        rulesTooltip.setFocusable(false);
        rulesTooltip.setVisible(false);
        rulesTooltip.setForeground(Color.WHITE); //MainMenuState.darkYellow);
        rulesTooltip.setBounds(10,10,1000,600);

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
        layeredPane.add(rulesTooltip,JLayeredPane.POPUP_LAYER);
        layeredPane.add(rulesInfo,JLayeredPane.POPUP_LAYER);

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

        if(e.getSource() == rulesInfo){

            rulesInfo.setVisible(false);
            rulesTooltip.setVisible(true);
        }

        if(e.getSource() == buttonBack){

            JukeBox.play("menuselect");
            buttonBack.setForeground(MainMenuState.darkYellow);

        }

        if(e.getSource() == buttonSave){

            JukeBox.play("menuselect");
            buttonSave.setForeground(MainMenuState.darkYellow);

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(e.getSource() == rulesInfo){

            rulesInfo.setVisible(true);
            rulesTooltip.setVisible(false);
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {


        JukeBox.play("menuoption");
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
