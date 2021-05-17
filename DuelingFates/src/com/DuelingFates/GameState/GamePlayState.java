package com.DuelingFates.GameState;

import com.DuelingFates.HUDs.DynamicHUD;
import com.DuelingFates.HUDs.EscapeMenu;
import com.DuelingFates.HUDs.HUD;
import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.*;
import com.DuelingFates.Objects.Consumable.Ammo;
import com.DuelingFates.Objects.Consumable.HealthPotion;
import com.DuelingFates.TileMap.TileMap;
import com.DuelingFates.Music.JukeBox;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


import static com.DuelingFates.Objects.Player.*;
import static com.DuelingFates.Networking.Client.Client.*;

public class GamePlayState extends GameState implements KeyListener {

    private BufferedImage background;
    private TileMap tileMap;
    @SuppressWarnings("FieldCanBeLocal")
    private final int tileSize = 64;

    public static Player player;
    private PlayerAnimation animation;
    public static Player enemyPlayer;
    private PlayerAnimation enemyAnimation;
    private Weapon gun;
    private Weapon gun2;
    private int timerMax;
    private int timerCount;

    private HUD hud;
    private DynamicHUD dynamicHUD;

    //egyellőre külön kezeljük, mert a threadelés nem syncronizált alapból
    private ArrayList<Projectile> hostBullets;
    private ArrayList<Projectile> clientBullets;
    private Projectile hostProjectile;

    private final EscapeMenu escapeMenu = new EscapeMenu();
    public boolean escapePressed = false;
    public boolean escapeBefore = false;

    private GameObjectRenderer gameObjectRenderer = new GameObjectRenderer();
    private static long seconds;
    private static long millis;
    private static int minutes;

    private static int hostPlayerScore;
    private static int clientPlayerScore;
    private static int hostPlayerAmmo;

    public static HealthPotion healthPotion2;
    public static Ammo ammo2;

    private static String hostPlayerName;
    public static String clientPlayerName;

    private boolean runOnce = true;

    private ArrayList<HealthPotion> healthPotions = new ArrayList<>();
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
        timerMax=500;
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
        enemyPlayer = new Player(tileMap);
        enemyPlayer.setPosition(400,300);
        enemyAnimation = new PlayerAnimation(enemyPlayer);

        //Player init
        player = new Player(tileMap);
        animation = new PlayerAnimation(player);

        setHostPlayerName(player.getPlayerName());
        setClientPlayerName(enemyPlayer.getPlayerName());


        //GAMEPLAY INIT

        //hostProjectile=new Projectile(tileMap,); //TODO: projectile
        //Init Weapon to player //TODO: TESZT, ha működik így akkor lehet szarakodni, hogy hogy adjuk be neki a projectile-t
        gun = new Weapon(tileMap,0);
        gun2 = new Weapon(tileMap,0);
        player.setPlayerWeapon(gun);
        enemyPlayer.setPlayerWeapon(gun2);

        //TODO player location egy játékos halálakor amit majd szintén meghívunk
        //TODO start timer, ami lehet metódus, itt végtelen ciklusban várunk a kliens csatlakozására és utána indul a meccs
        //TODO projectile tömb létrehozása

        HealthPotion healthPotion1 = new HealthPotion(tileMap);
        healthPotion2 = new HealthPotion(tileMap);

        //messageQueue.add("POTIONX:".concat(Float.toString(healthPotion1.getPositionX())));
        //messageQueue.add("POTIONY:".concat(Float.toString(healthPotion1.getPositionY())));

        healthPotions.add(healthPotion1);
        healthPotions.add(healthPotion2);

        Ammo ammo1 = new Ammo(tileMap);
        ammo2 = new Ammo(tileMap);

        //messageQueue.add("AMMOX:".concat(Float.toString(ammo1.getPositionX())));
        //messageQueue.add("AMMOY:".concat(Float.toString(ammo1.getPositionY())));

        ammos.add(ammo1);
        ammos.add(ammo2);


        hud = new HUD();
        dynamicHUD = new DynamicHUD();

    }

    @Override
    public void draw(Graphics2D graphics) {

        //System.out.println(MainProcess.getMapTemp());
        graphics.drawImage(background,0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight(),null);
        tileMap.draw(graphics);

        if (MainProcess.getAmIServer()) {
            dynamicHUD.draw(graphics, player, enemyPlayer);
            hud.draw(graphics, player, enemyPlayer);

        }
        else{
            dynamicHUD.draw(graphics, enemyPlayer, player);
            hud.draw(graphics, player, enemyPlayer);

        }

        //Player draw
        gameObjectRenderer.drawPlayer(graphics, player);
        gameObjectRenderer.drawPlayer(graphics, enemyPlayer);

        //TODO SHOULD REMOVE mint a projectile-nál és for ciklusos rajzolás
        for(int i=0; i<healthPotions.size(); i++) {
            gameObjectRenderer.drawHealthPotion(graphics, healthPotions.get(i));
        }
        for(int i=0; i<ammos.size(); i++) {
            gameObjectRenderer.drawAmmoPickup(graphics, ammos.get(i));
        }

        //Projectile draw
        //gameObjectRenderer.drawProjectile(graphics,hostProjectile);

        if (escapePressed) {
            escapeMenu.draw(graphics);
            escapeBefore = true;            //engedélyezzük az eltüntetés lehetőségét
        }
    }


    @Override
    public void update() {

        if (runOnce){
            messageQueue.add("ENEMYNAME:".concat(player.getPlayerName()));
            runOnce = false;
        }

        messageQueue.add("ENEMYSCORE:".concat(Integer.toString(player.getPlayerScore())));

        //Player update
        player.update();
        //animation.updateAnimation(player);
        animation.updateAnimationPossessed(player);

        enemyPlayer.update();
        //enemyAnimation.updateAnimation(enemyPlayer);
        enemyAnimation.updateAnimationPossessed(enemyPlayer);


        //hostProjectile.update();
        //attack enemy player
        player.checkAttack(enemyPlayer);
        enemyPlayer.checkAttack(player);

        //check if healthpotions is picked up
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
            timerCount=0;
        }
        //Ha a timer elérte a beállított időt, a score state-re váltunk, adatokat mentünk
        if(minutes == MainProcess.getMatchDurationTemp()){

            hostPlayerScore = player.getPlayerScore();
            clientPlayerScore = enemyPlayer.getPlayerScore();
            messageQueue.add("ENEMYSCORE:".concat(Integer.toString(clientPlayerScore)));
            stateManager.setState(StateManager.States.SCORESTATE);

        }

        setHostPlayerAmmo(player.getPlayerAmmoQty());


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
        //TODO :kiválasztunk egy random pozíciót a mapon és ott példányosítjuk
        //megadjuk, hogy hova lehet spawnolni, mert tudjuk a map layoutját
        //és azok közül random választunk
        //az időpont amilyen sebeséggel spawnol, pedig szintén lehet random, vagy minden 20 secenként
        HealthPotion h=new HealthPotion(tileMap);
        return h;
    }
    public Ammo spawnAmmo(TileMap tileMap) {
        //TODO :kiválasztunk egy random pozíciót a mapon és ott példányosítjuk
        //megadjuk, hogy hova lehet spawnolni, mert tudjuk a map layoutját
        //és azok közül random választunk
        //az időpont amilyen sebeséggel spawnol, pedig szintén lehet random, vagy minden 20 secenként
        Ammo a=new Ammo(tileMap);
        return a;
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
