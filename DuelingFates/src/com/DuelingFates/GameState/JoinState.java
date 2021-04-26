package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JoinState extends GameState{

    public JPanel jPanel = new JPanel();
    public JButton buttonJoin = new JButton("Back");
    public JPanel contentPane = new JPanel();

    public JoinState(StateManager stateManager){
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
    public void updateSwingUI(JFrame duelingFates) {

        duelingFates.getContentPane().removeAll();
        jPanel.setPreferredSize(new Dimension(40, 40));
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.add(jPanel, BorderLayout.CENTER);
        contentPane.add(buttonJoin, BorderLayout.PAGE_START);
        duelingFates.setContentPane(contentPane);
        duelingFates.setPreferredSize(new Dimension(MainProcess.gameWidth, MainProcess.gameHeight));
        buttonJoin.setBackground(Color.BLACK);
        jPanel.setBackground(Color.YELLOW);
        jPanel.setOpaque(true);

        duelingFates.setResizable(true);
        duelingFates.pack();
        duelingFates.setLocationRelativeTo(null);
        duelingFates.setVisible(true);
        duelingFates.repaint();


        buttonJoin.addActionListener(this);
        StateManager.stateChanged = false;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        jPanel.setBackground(Color.cyan);
        stateManager.setState(StateManager.States.GAMEPLAYSTATE);
        System.out.println("Megnyomtam " + stateManager.currentState);

    }
}
