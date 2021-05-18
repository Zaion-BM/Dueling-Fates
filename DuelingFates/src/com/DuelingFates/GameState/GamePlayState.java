package com.DuelingFates.GameState;

import com.DuelingFates.HUDs.DynamicHUD;
import com.DuelingFates.HUDs.EscapeMenu;
import com.DuelingFates.HUDs.HUD;
import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.*;
import com.DuelingFates.Objects.Consumable.Ammo;
import com.DuelingFates.Objects.Consumable.HealthPotion;
import com.DuelingFates.TileMap.TileMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


import static com.DuelingFates.Objects.Player.*;

public class GamePlayState extends GameState implements KeyListener {

    private BufferedImage background;
    private TileMap tileMap;
    @SuppressWarnings("FieldCanBeLocal")
    private final int tileSize = 64;

    public static Player player;
    private PlayerAnimation animation;
    public static Player enemyPlayer;
    private PlayerAnimation enemyAnimation;
    private int timerMax;
    private int timerCount;

    private HUD hud;
    private DynamicHUD dynamicHUD;

    private final EscapeMenu escapeMenu = new EscapeMenu();
    public boolean escapePressed = false;
    public boolean escapeBefore = false;

    private final GameObjectRenderer gameObjectRenderer = new GameObjectRenderer();
    private static long seconds;
    private static long millis;
    private static int minutes;

    private static String hostPlayerName;
    private static int hostPlayerScore;
    private static int hostPlayerAmmo;

    public static String clientPlayerName;
    private static int clientPlayerScore;

    private int runTimer = 0;

    public static ArrayList<HealthPotion> healthPotions = new ArrayList<>();
    public static ArrayList<Ammo> ammos = new ArrayList<>();

    public GamePlayState(StateManager stateManager){

        super(stateManager);
        StateManager.setStateChangedTrue();

        initialization();

    }

    @Override
    public void initialization() {

        seconds = 0;
        millis = 0;
        minutes = 0;
        hostPlayerScore = 0;
        clientPlayerScore = 0;
        timerMax=500;           //500:60 = 12 SEC múlva spawnol újra
        timerCount=0;

        tileMap = new TileMap(tileSize);

        if(MainProcess.getMapTemp().equals("CloudyForest")) {

            try {

                background = ImageIO.read(new File("DuelingFates/Sources/BG_Widescreen/WS_CloudyForest_Background.png"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            tileMap.loadTilesFormFiles("DuelingFates/Sources/Tiles_Brown/");
            tileMap.loadTilesToMap("DuelingFates/Sources/Maps/CloudyForest.txt");

        }

        if(MainProcess.getMapTemp().equals("Crimson")) {

            try {

                background = ImageIO.read(new File("DuelingFates/Sources/BG_Widescreen/WS_Crimson_Background.png"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            tileMap.loadTilesFormFiles("DuelingFates/Sources/Tiles_Sand/");
            tileMap.loadTilesToMap("DuelingFates/Sources/Maps/Crimson.txt");

        }

        if(MainProcess.getMapTemp().equals("SnowyMountain")) {

            try {

                background = ImageIO.read(new File("DuelingFates/Sources/BG_Widescreen/WS_Snow_Background.png"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            tileMap.loadTilesFormFiles("DuelingFates/Sources/Tiles_Brown/");
            tileMap.loadTilesToMap("DuelingFates/Sources/Maps/SnowyMountain.txt");

        }

        //Client creation
        enemyPlayer = new Player(tileMap,1000,300, MainProcess.getEnemyCharacterTemp());
        enemyAnimation = new PlayerAnimation(enemyPlayer);

        //Player init
        player = new Player(tileMap,1000,300, MainProcess.getCharacterTemp());
        animation = new PlayerAnimation(player);

        setHostPlayerName(player.getPlayerName());
        setClientPlayerName(enemyPlayer.getPlayerName());

        //GAMEPLAY INIT
        //TODO: projectile
        //Init Weapon to player

        Weapon gun = new Weapon(tileMap, Weapon.WeaponModel.DEFAULT);
        Weapon gun2 = new Weapon(tileMap, Weapon.WeaponModel.DEFAULT);

        player.setPlayerWeapon(gun);
        enemyPlayer.setPlayerWeapon(gun2);

        HealthPotion healthPotion1 = new HealthPotion(tileMap);
        healthPotions.add(healthPotion1);

        Ammo ammo1 = new Ammo(tileMap);
        ammos.add(ammo1);

        hud = new HUD();
        dynamicHUD = new DynamicHUD();

    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(background,0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight(),null);
        tileMap.draw(graphics);

        if (MainProcess.getAmIServer()) {
            dynamicHUD.draw(graphics, player, enemyPlayer);
        }
        else{
            dynamicHUD.draw(graphics, enemyPlayer, player);
        }

        hud.draw(graphics, player, enemyPlayer);
        gameObjectRenderer.drawPlayer(graphics, player);
        gameObjectRenderer.drawPlayer(graphics, enemyPlayer);

        //Consumables draw - ENHANCED FOR CIKLUS
        for (HealthPotion healthPotion : healthPotions) {
            gameObjectRenderer.drawHealthPotion(graphics, healthPotion);
        }

        for (Ammo ammo : ammos) {
            gameObjectRenderer.drawAmmoPickup(graphics, ammo);
        }

        if (escapePressed) {

            escapeMenu.draw(graphics);
            escapeBefore = true;            //engedélyezzük az eltüntetés lehetőségét
        }

    }


    @Override
    public void update() {

        runTimer++;

        if (runTimer >= 50) {
            messageQueue.add("ENEMYNAME:".concat(player.getPlayerName()));
            messageQueue.add("ENEMYSCORE:".concat(Integer.toString(player.getPlayerScore())));
            messageQueue.add("ENEMY_X:".concat(Float.toString(player.getPositionX())));
            messageQueue.add("ENEMY_Y:".concat(Float.toString(player.getPositionY())));

            runTimer = 0;
        }

        //Player update
        player.update(1000,300);
        animation.updateAnimation(player);

        enemyPlayer.update(1000,300);
        enemyAnimation.updateAnimation(enemyPlayer);

        //attack enemy player
        player.checkAttack(enemyPlayer);
        enemyPlayer.checkAttack(player);

        //check if health potion and ammo are picked up
        checkPickedUpHealth(player,healthPotions);
        checkPickedUpHealth(enemyPlayer,healthPotions);

        checkPickedUpAmmo(player,ammos);
        checkPickedUpAmmo(enemyPlayer,ammos);

        //random spawn consumables
        timerCount++;

        if(timerCount == timerMax){
            removeHealths(healthPotions);
            healthPotions.add(spawnHealthPotion(tileMap));
            removeAmmos(ammos);
            ammos.add(spawnAmmo(tileMap));

            //CSAK A MÁSODIKTÓL működik
            messageQueue.add("AMMOX:".concat(Float.toString(ammos.get(0).getPositionX())));
            messageQueue.add("AMMOY:".concat(Float.toString(ammos.get(0).getPositionY())));

            messageQueue.add("POTIONX:".concat(Float.toString(healthPotions.get(0).getPositionX())));
            messageQueue.add("POTIONY:".concat(Float.toString(healthPotions.get(0).getPositionY())));

            timerCount=0;
        }

        //Ha a timer elérte a beállított időt, és nincs pontszámegyezés akkor a score state-re váltunk, adatokat mentünk
        if(minutes >= MainProcess.getMatchDurationTemp() && (player.getPlayerScore() != enemyPlayer.getPlayerScore()) ){

            hostPlayerScore = player.getPlayerScore();
            hostPlayerName = player.getPlayerName();
            clientPlayerScore = enemyPlayer.getPlayerScore();
            clientPlayerName = enemyPlayer.getPlayerName();
            stateManager.setState(StateManager.States.SCORESTATE);

        }

        setHostPlayerAmmo(player.getPlayerAmmoQty());

    }

    //check pickups
    public void checkPickedUpHealth(Player player,ArrayList<HealthPotion> healthPotions){
        for(int i=0; i<healthPotions.size(); i++) {
            if (player.objectIntersection(healthPotions.get(i))) {
                healthPotions.get(i).useConsumable(player);
                System.out.println("+30 Health");
                healthPotions.remove(i);
                messageQueue.add("HPADD");
            }
        }
    }

    public void checkPickedUpAmmo(Player player,ArrayList<Ammo> ammos){
        for(int i=0; i<ammos.size(); i++) {
            if (player.objectIntersection(ammos.get(i))) {
                ammos.get(i).useConsumable(player);
                System.out.println("+10 Ammo");
                ammos.remove(i);
                messageQueue.add("AMMOADD");

            }
        }
    }

    public void removeHealths(ArrayList<HealthPotion> h){
        for(int i=0; i<h.size();i++) h.remove(i);
    }

    public void removeAmmos(ArrayList<Ammo> a){
        for(int i=0; i<a.size();i++) a.remove(i);
    }

    public HealthPotion spawnHealthPotion(TileMap tileMap) {
        return new HealthPotion(tileMap);
    }
    public Ammo spawnAmmo(TileMap tileMap) {
        return new Ammo(tileMap);
    }

    /*
     *Time methods
     */
    public static void addMillis(long time){
        millis += time;
    }

    public static void setMillisZero(){
        millis = 0;
    }

    public static long getMillis(){
        return millis;
    }

    public static long getSeconds(){
        return seconds;
    }

    public static void addSeconds(){
        seconds++;
    }

    public static void setSecondsZero(){
        seconds = 0;
    }

    public static int getMinutes(){
        return minutes;
    }

    public static void addMinutes(){
        minutes++;
    }

    /*
     *Player Setters and Getters
     */
    public static int getHostPlayerAmmo(){
        return hostPlayerAmmo;
    }

    public static void setHostPlayerAmmo(int ammo){
        hostPlayerAmmo = ammo;
    }

    public static int getHostPlayerScore(){
        return hostPlayerScore;
    }

    public static int getClientPlayerScore(){
        return clientPlayerScore;
    }

    public static String getHostPlayerName(){
        return hostPlayerName;
    }

    public static String getClientPlayerName(){
        return clientPlayerName;
    }

    public static void setHostPlayerName(String name){
        hostPlayerName = name;
    }

    public static void setClientPlayerName(String name){
        clientPlayerName = name;
    }

    @Override
    public void updateSwingUI(JFrame duelingFates,JLayeredPane layeredPane) {

        duelingFates.addKeyListener(new PlayerInputHandler(player));
        duelingFates.addKeyListener(new InputHandler(this));        //Listener a keyboard érzékeléséért

        layeredPane.removeAll();                                                //GamePlay-nél nincs szükség a Swing elemekre
        //duelingFates.setCursor(MainProcess.hiddenCursor);
        duelingFates.repaint();                                                 //üres Frame-et hagyunk
        StateManager.setStateChangedFalse();

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        //ha épp nincs kirajzolva és ESC-et nyomtak
        if (key == KeyEvent.VK_ESCAPE & !escapeBefore) {

            escapePressed = true;

        }

        //ha épp ki van rajzolva akkor vissza az alap állapotba és elrejtés
        if(key == KeyEvent.VK_ESCAPE & escapeBefore) {

            escapePressed = false;
            escapeBefore = false;

        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        //Enter esetén kilépünk
        if(key ==KeyEvent.VK_ENTER & escapeBefore){

            stateManager.setState(StateManager.States.MAINMENUSTATE);

        }

    }
}
