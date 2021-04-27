package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;

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
    private final ImageIcon knightSelectedImg = new ImageIcon("DuelingFates/Sources/char_PossessedArmor/PossessedArmor_jumpingAndFalling3x.png");
    private final ImageIcon knightImg = new ImageIcon("DuelingFates/Sources/char_PossessedArmor/PossessedArmor_standing3x.png");

    //JRadioButtons
    private final JRadioButton pirateJRadioButton = new JRadioButton(pirateImg);
    private final JRadioButton knightJRadioButton = new JRadioButton(knightImg);

    //ButtonGroup és a hozzá tartozó label
    private final ButtonGroup characterSelection = new ButtonGroup();
    private final JLabel characterSelectionLabel = new JLabel();

    //a kiválasztott charactert tároljuk benne
    public static String selectedChar;

    public SettingsState(StateManager stateManager){

        super(stateManager);
        StateManager.stateChanged = true;

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
        duelingFates.repaint();

        backgroundLabel.setBounds(0,0,background.getIconWidth(), background.getIconHeight());

        MainMenuState.setButtonStyle(buttonSave);
        MainMenuState.setButtonStyle(buttonBack);

        playerName.setBackground(Color.darkGray);
        playerName.setFont(MainProcess.BalooThambiFont);
        playerName.setForeground(Color.WHITE);
        playerName.setCaretColor(Color.WHITE);
        playerName.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        playerName.setText("Knight Rider");
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerName.setBounds(MainProcess.gameWidth/2+75,(int)(MainProcess.gameHeight*0.30),400,80);

        playerNameLabel.setFont(MainProcess.BalooThambiFont);
        playerNameLabel.setText("Player name:");
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setBounds(MainProcess.gameWidth/2-370,(int)(MainProcess.gameHeight*0.32),350,50);

        characterSelectionLabel.setFont(MainProcess.BalooThambiFont);
        characterSelectionLabel.setText("Select character");
        characterSelectionLabel.setForeground(Color.WHITE);
        characterSelectionLabel.setBounds(MainProcess.gameWidth/2-205,(int)(MainProcess.gameHeight*0.42),410,50);

        pirateJRadioButton.setIcon(pirateImg);
        pirateJRadioButton.setName("PirateDeckhand");
        pirateJRadioButton.addActionListener(this);
        characterSelection.add(pirateJRadioButton);
        pirateJRadioButton.setBounds((int)(MainProcess.gameWidth*0.52),(int)(MainProcess.gameHeight*0.5),pirateImg.getIconWidth(),pirateImg.getIconHeight());
        pirateJRadioButton.setOpaque(false);
        pirateJRadioButton.setSelectedIcon(pirateSelectedImg);

        knightJRadioButton.setIcon(knightImg);
        knightJRadioButton.setName("PossessedArmor");
        knightJRadioButton.addActionListener(this);
        characterSelection.add(knightJRadioButton);
        knightJRadioButton.setBounds((int)(MainProcess.gameWidth*0.43),(int)(MainProcess.gameHeight*0.5),knightImg.getIconWidth(),knightImg.getIconHeight());
        knightJRadioButton.setOpaque(false);
        knightJRadioButton.setSelectedIcon(knightSelectedImg);

        layeredPane.setBounds(0,0, MainProcess.gameWidth, MainProcess.gameHeight);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(characterSelectionLabel,JLayeredPane.POPUP_LAYER);
        layeredPane.add(knightJRadioButton,JLayeredPane.POPUP_LAYER);
        layeredPane.add(pirateJRadioButton,JLayeredPane.POPUP_LAYER);
        layeredPane.add(playerNameLabel, JLayeredPane.POPUP_LAYER);
        layeredPane.add(playerName, JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonSave,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonBack,JLayeredPane.POPUP_LAYER);

        duelingFates.setCursor(MainProcess.gameCursor);
        duelingFates.add(layeredPane);
        duelingFates.setPreferredSize(new Dimension(MainProcess.gameWidth, MainProcess.gameHeight));
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        duelingFates.setResizable(false);
        duelingFates.pack();
        duelingFates.setVisible(true);

        buttonBack.setBounds((int)(MainProcess.gameWidth*0.05), (int)(MainProcess.gameHeight*0.86), 200,50);
        buttonBack.addActionListener(this);
        buttonBack.addMouseListener(this);

        buttonSave.setBounds((int)(MainProcess.gameWidth*0.95)-200, (int)(MainProcess.gameHeight*0.86), 200,50);
        buttonSave.addActionListener(this);
        buttonSave.addMouseListener(this);

        StateManager.stateChanged = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == buttonSave) {

            System.out.println(playerName.getText());

            if(pirateJRadioButton.isSelected()){

                selectedChar = pirateJRadioButton.getName();

            }
            else if(knightJRadioButton.isSelected()){

                selectedChar = knightJRadioButton.getName();

            }
            System.out.println(selectedChar);
            stateManager.setState(StateManager.States.MAINMENUSTATE);

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
