package com.DuelingFates.GameState;

import com.DuelingFates.Main.MainProcess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainMenuState extends GameState implements ActionListener {

    public JPanel jPanel = new JPanel();
    public JButton buttonJoin = new JButton("Join");
    public JPanel contentPane = new JPanel();

    private BufferedImage background;
    private BufferedImage logoImage;

    /*
    private final String join = "Join";
    private final String host = "Host";
    private final String settings = "Settings";
    private final String quit = "Quit";
    */
    //private Font BalooThambi;
    //private final String fontLocation = "DuelingFates/Sources/Font/BalooThambi-Regular.ttf";


    public MainMenuState(StateManager stateManager){
        super(stateManager);                                    //őskonstruktor meghívása
        StateManager.stateChanged = true;
        //stateManager.setState(StateManager.States.GAMEPLAYSTATE);
        //System.out.println(stateManager.currentState);
        /*try{
            background = ImageIO.read(new File("DuelingFates/Sources/background_MainAndSettings.png"));
            logoImage = ImageIO.read(new File("DuelingFates/Sources/logo_DuelingFates.png"));

            BalooThambi = Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)).deriveFont(55f);                   //font létrehozása
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();                 //fontok leírására használt GraphicsEnvironment
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontLocation)));
        }
        catch (IOException | FontFormatException e){                                                                    // or: |
            e.printStackTrace();
        }*/
    }

    @Override
    public void initialization() {

    }

    /*
    public void drawStringToCenter(Graphics2D graphics, String string, int heightPosition){
        FontMetrics fm = graphics.getFontMetrics();                                            //adott font renderelési tulajdonságait tartalmazza
        graphics.drawString(string,(MainProcess.gameWidth - fm.stringWidth(string)) / 2, heightPosition);        //lekérdezzük a szöveg szélességét, a font ismeretében és középre igazítjuk
    }*/

    @Override
    public void draw(Graphics2D graphics) {

        //duelingFates.add(menuPanel);

        //images
        //graphics.drawImage(background,0,0, gameWidth, gameHeight,null);
        //graphics.drawImage(logoImage, 40, 870, logoImage.getWidth()/3, logoImage.getHeight()/3, null);

        //buttons
        /*graphics.setFont(BalooThambi);
        drawStringToCenter(graphics,join,680-3*80);
        drawStringToCenter(graphics,host,680-2*80);
        drawStringToCenter(graphics,settings,680-80);
        drawStringToCenter(graphics,quit,680);
        */

    }

    @Override
    public void update() {

    }

    @Override
    public void updateSwingUI(JFrame duelingFates) {

        jPanel.setPreferredSize(new Dimension(40, 40));
        duelingFates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.add(jPanel, BorderLayout.CENTER);
        contentPane.add(buttonJoin, BorderLayout.PAGE_END);
        duelingFates.setContentPane(contentPane);
        duelingFates.setPreferredSize(new Dimension(MainProcess.gameWidth, MainProcess.gameHeight));
        buttonJoin.setBackground(Color.green);
        jPanel.setBackground(Color.BLUE);
        jPanel.setOpaque(true);

        duelingFates.setResizable(true);
        duelingFates.pack();
        duelingFates.setLocationRelativeTo(null);
        duelingFates.setVisible(true);

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
