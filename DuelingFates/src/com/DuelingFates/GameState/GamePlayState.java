package com.DuelingFates.GameState;

import com.DuelingFates.HUDs.EscapeMenu;
import com.DuelingFates.HUDs.HUD;
import com.DuelingFates.Main.MainProcess;
import com.DuelingFates.Objects.*;
import com.DuelingFates.Objects.Consumable.Ammo;
import com.DuelingFates.Objects.Consumable.Consumable;
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
import java.util.Random;


public class GamePlayState extends GameState implements KeyListener {

    private BufferedImage background;
    private TileMap tileMap;
    @SuppressWarnings("FieldCanBeLocal")
    private final int tileSize = 64;

    private Player hostPlayer;          //TODO: miért kell két külön player? gombok nyomkodásánál mi lesz?
    private PlayerAnimation hostAnimation;
    public static Player clientPlayer;
    private PlayerAnimation clientAnimation;
    private Weapon gun;
    private int timerMax;
    private int timerCount;

    private HUD hud;

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

    private static String hostPlayerName;
    public static String clientPlayerName;

    private ArrayList<HealthPotion> healthPotions = new ArrayList<>();
    private ArrayList<Ammo> ammos = new ArrayList<>();

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

        //Player init
        clientPlayer = new Player(tileMap);
        clientPlayer.setPosition(500,300);
        clientAnimation = new PlayerAnimation(clientPlayer);
        setClientPlayerName(clientPlayer.getPlayerName());

        hostPlayer = new Player(tileMap);
        hostAnimation = new PlayerAnimation(hostPlayer);
        hud = new HUD(hostPlayer);

        //TODO CSAK A TESZT MIATT
        hostPlayer.setPlayerName("HOST!4!44");
        setHostPlayerName(hostPlayer.getPlayerName());

        //hostProjectile=new Projectile(tileMap,); //TODO: projectile
        //Init Weapon to player //TODO: TESZT, ha működik így akkor lehet szarakodni, hogy hogy adjuk be neki a projectile-t
        gun = new Weapon(tileMap,0);
        hostPlayer.setPlayerWeapon(gun);

        //TODO player location egy játékos halálakor amit majd szintén meghívunk
        //TODO start timer, ami lehet metódus, itt végtelen ciklusban várunk a kliens csatlakozására és utána indul a meccs
        //TODO projectile tömb létrehozása

        HealthPotion healthPotion1 = new HealthPotion(tileMap);
        HealthPotion healthPotion2 = new HealthPotion(tileMap);

        healthPotions.add(healthPotion1);
        healthPotions.add(healthPotion2);

        Ammo ammo1 = new Ammo(tileMap);
        Ammo ammo2 = new Ammo(tileMap);

        ammos.add(ammo1);
        ammos.add(ammo2);


    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(background,0,0, MainProcess.getGameWidth(), MainProcess.getGameHeight(),null);
        tileMap.draw(graphics);

        hud.draw(graphics, hostPlayer);

        //Player draw
        gameObjectRenderer.drawPlayer(graphics, hostPlayer);
        gameObjectRenderer.drawPlayer(graphics, clientPlayer);

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

        //TODO match start, player death esemény,
        //TODO ezenkívűl itemek és fegyverek spawnolása

        //Player update
        hostPlayer.update();
        hostAnimation.updateAnimation(hostPlayer);
        //hostAnimation.updateAnimationPossessed(hostPlayer);

        clientPlayer.update();
        clientAnimation.updateAnimation(clientPlayer);
        //clientAnimation.updateAnimationPossessed(clientPlayer);

        //hostProjectile.update();

        //attack enemy player
        hostPlayer.checkAttack(clientPlayer);
        clientPlayer.checkAttack(hostPlayer);
        //clientPlayer.setLeft(getMSG);

        //check if healthpotions is picked up
        checkPickedUpHealth(hostPlayer,healthPotions);
        checkPickedUpHealth(clientPlayer,healthPotions);

        checkPickedUpAmmo(hostPlayer,ammos);
        checkPickedUpAmmo(clientPlayer,ammos);

        //ha szeretnénk ha hullana az ammo
        //for(int i=0; i<healthPotions.size();i++) healthPotions.get(i).update();
        //for(int i=0; i<ammos.size();i++) ammos.get(i).update();

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
        if(minutes == MainProcess.getMatchDurationTemp() ){

            hostPlayerScore = hostPlayer.getPlayerScore();
            clientPlayerScore = clientPlayer.getPlayerScore();
            stateManager.setState(StateManager.States.SCORESTATE);

        }

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

        duelingFates.addKeyListener(new PlayerInputHandler(hostPlayer));

        duelingFates.addKeyListener(new InputHandler(this));        //Listener a keyboard érzékeléséért

        layeredPane.removeAll();                                                //GamePlay-nél nincs szükség a Swing elemekre
        duelingFates.setCursor(MainProcess.hiddenCursor);
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
