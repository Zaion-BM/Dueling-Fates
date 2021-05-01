package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HostState extends GameState implements ActionListener, MouseListener{

    //Gombok
    private final JButton buttonBack = new JButton("Back");
    private final JButton buttonStart = new JButton("Start");

    //Háttér
    private final ImageIcon background = new ImageIcon("DuelingFates/Sources/background_JoinAndHost.png");
    private final JLabel backgroundLabel = new JLabel(background);

    //Slider az meccs időtartamának megadásához
    private final JSlider matchDuration = new JSlider();
    private final JLabel matchDurationLabel = new JLabel();

    //JRadioButton images beolvasása
    private final ImageIcon cloudyForestImg = new ImageIcon("DuelingFates/Sources/BG_Widescreen/CloudyForestMap.png");
    private final ImageIcon crimsonImg = new ImageIcon("DuelingFates/Sources/BG_Widescreen/CrimsonMap.png");
    private final ImageIcon snowyMountainImg = new ImageIcon("DuelingFates/Sources/BG_Widescreen/SnowyMountainMap.png");

    //JRadioButtons
    private final JRadioButton mapCloudyForest = new JRadioButton(cloudyForestImg);
    private final JRadioButton mapCrimson = new JRadioButton(crimsonImg);
    private final JRadioButton mapSnowyMountain = new JRadioButton(snowyMountainImg);

    //ButtonGroup és a hozzá tartozó label
    private final ButtonGroup mapSelection = new ButtonGroup();
    private final JLabel mapSelectionLabel = new JLabel();

    //a kiválasztott mapot tároljuk benne
    static public String selectedMap;

    public HostState(StateManager stateManager){

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

        MainMenuState.setButtonStyle(buttonStart);
        MainMenuState.setButtonStyle(buttonBack);

        mapSelectionLabel.setFont(MainProcess.BalooThambiFont);
        mapSelectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mapSelectionLabel.setText("Select map");
        mapSelectionLabel.setForeground(Color.WHITE);
        mapSelectionLabel.setBounds(MainProcess.getGameWidth()/2-150,(int)(MainProcess.getGameHeight()*0.20),300,50);

        mapCloudyForest.setIcon(cloudyForestImg);
        mapCloudyForest.setName("CloudyForest");
        mapCloudyForest.setHorizontalAlignment(SwingConstants.CENTER);
        mapCloudyForest.addActionListener(this);
        mapCloudyForest.setBounds((int)(MainProcess.getGameWidth()*0.5-2*cloudyForestImg.getIconWidth()),(int)(MainProcess.getGameHeight()*0.30),cloudyForestImg.getIconWidth(),cloudyForestImg.getIconHeight());
        mapCloudyForest.setOpaque(false);
        mapCloudyForest.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
        mapCloudyForest.setBorderPainted(true);
        mapCloudyForest.addActionListener(this);
        mapSelection.add(mapCloudyForest);

        mapCrimson.setIcon(crimsonImg);
        mapCrimson.setName("Crimson");
        mapCrimson.setHorizontalAlignment(SwingConstants.CENTER);
        mapCrimson.addActionListener(this);
        mapCrimson.setBounds((int)(MainProcess.getGameWidth()*0.5-crimsonImg.getIconWidth()/2),(int)(MainProcess.getGameHeight()*0.30),crimsonImg.getIconWidth(),crimsonImg.getIconHeight());
        mapCrimson.setOpaque(false);
        mapCrimson.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
        mapCrimson.setBorderPainted(true);
        mapCrimson.addActionListener(this);
        mapSelection.add(mapCrimson);

        mapSnowyMountain.setIcon(snowyMountainImg);
        mapSnowyMountain.setName("SnowyMountain");
        mapSnowyMountain.setHorizontalAlignment(SwingConstants.CENTER);
        mapSnowyMountain.addActionListener(this);
        mapSnowyMountain.setBounds((int)(MainProcess.getGameWidth()*0.5+snowyMountainImg.getIconWidth()),(int)(MainProcess.getGameHeight()*0.30),snowyMountainImg.getIconWidth(),snowyMountainImg.getIconHeight());
        mapSnowyMountain.setOpaque(false);
        mapSnowyMountain.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
        mapSnowyMountain.setBorderPainted(true);
        mapSnowyMountain.addActionListener(this);
        mapSelection.add(mapSnowyMountain);

        matchDuration.setOrientation(SwingConstants.HORIZONTAL);
        matchDuration.setBounds(MainProcess.getGameWidth()/2+50,(int)(MainProcess.getGameHeight()*0.55),500,60);
        matchDuration.setPaintTrack(true);
        matchDuration.setForeground(Color.WHITE);
        matchDuration.setMajorTickSpacing(1);
        matchDuration.setFont(new Font("Impact",Font.PLAIN,30));
        matchDuration.setPaintLabels(true);
        matchDuration.setOpaque(false);
        matchDuration.setMaximum(10);
        matchDuration.setValue(5);
        matchDuration.setMinimum(1);

        matchDurationLabel.setFont(MainProcess.BalooThambiFont);
        matchDurationLabel.setText("Match duration:");
        matchDurationLabel.setForeground(Color.WHITE);
        matchDurationLabel.setBounds(MainProcess.getGameWidth()/4,(int)(MainProcess.getGameHeight()*0.55),500,50);

        layeredPane.setBounds(0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight());
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(mapCloudyForest,JLayeredPane.POPUP_LAYER);
        layeredPane.add(mapCrimson,JLayeredPane.POPUP_LAYER);
        layeredPane.add(mapSnowyMountain,JLayeredPane.POPUP_LAYER);
        layeredPane.add(matchDuration, JLayeredPane.POPUP_LAYER);
        layeredPane.add(mapSelectionLabel, JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonStart,JLayeredPane.POPUP_LAYER);
        layeredPane.add(buttonBack,JLayeredPane.POPUP_LAYER);
        layeredPane.add(matchDurationLabel,JLayeredPane.POPUP_LAYER);

        duelingFates.setCursor(MainProcess.gameCursor);
        duelingFates.add(layeredPane);
        duelingFates.setPreferredSize(new Dimension(MainProcess.getGameWidth(), MainProcess.getGameHeight()));
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        duelingFates.setResizable(false);
        duelingFates.pack();
        duelingFates.setVisible(true);

        buttonBack.setBounds((int)(MainProcess.getGameWidth()*0.05), (int)(MainProcess.getGameHeight()*0.86), 200,50);
        buttonBack.addActionListener(this);
        buttonBack.addMouseListener(this);

        buttonStart.setBounds((int)(MainProcess.getGameWidth()*0.95)-200, (int)(MainProcess.getGameHeight()*0.86), 200,50);
        buttonStart.setForeground(Color.green);
        buttonStart.addActionListener(this);
        buttonStart.addMouseListener(this);

        duelingFates.repaint();

        StateManager.setStateChangedFalse();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == mapCloudyForest){

            if(mapCloudyForest.isSelected()){

                mapCloudyForest.setBorder(BorderFactory.createLineBorder(MainMenuState.darkGreen,4));
                mapCrimson.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
                mapSnowyMountain.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));

            }

        }

        if (e.getSource() == mapCrimson){

            if(mapCrimson.isSelected()){

                mapCrimson.setBorder(BorderFactory.createLineBorder(MainMenuState.darkGreen,4));
                mapCloudyForest.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
                mapSnowyMountain.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));

            }

        }

        if (e.getSource() == mapSnowyMountain){

            if(mapSnowyMountain.isSelected()){

                mapSnowyMountain.setBorder(BorderFactory.createLineBorder(MainMenuState.darkGreen,4));
                mapCloudyForest.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
                mapCrimson.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));

            }

        }

        if(e.getSource() == buttonStart) {

            if(mapCloudyForest.isSelected()){

                selectedMap = mapCloudyForest.getName();

            }
            else if(mapCrimson.isSelected()){

                selectedMap = mapCrimson.getName();

            }
            else if(mapSnowyMountain.isSelected()){

                selectedMap = mapSnowyMountain.getName();

            }
            System.out.println(matchDuration.getValue());
            System.out.println(selectedMap);
            //TODO stateManager.setState(StateManager.States.GAMEPLAYSTATE);
            stateManager.setState(StateManager.States.SCORESTATE);
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

        if(e.getSource() == buttonStart){

            buttonStart.setForeground(MainMenuState.darkYellow);

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

        if(e.getSource() == buttonStart){

            buttonStart.setForeground(MainMenuState.darkGreen);

        }

        if(e.getSource() == buttonBack){

            buttonBack.setForeground(MainMenuState.darkRed);

        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

        if(e.getSource() == buttonStart){

            buttonStart.setForeground(Color.green);

        }

        if(e.getSource() == buttonBack){

            buttonBack.setForeground(Color.WHITE);

        }

    }

}
