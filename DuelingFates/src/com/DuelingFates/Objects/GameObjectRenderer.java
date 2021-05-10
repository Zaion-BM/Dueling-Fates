package com.DuelingFates.Objects;

import com.DuelingFates.Objects.Consumable.Consumable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameObjectRenderer {

    //Az osztály csak a kirajzolások implementálása miatt jött létre
    //A gameplaystate-ben csak hivatkozunk rá gey példányon keresztül, a konstruktorban olvassuk be a fájlokat

    //Consumable sprites
    private BufferedImage healthSprite;
    private BufferedImage ammoSprite;

    //Projectile sprites
    private BufferedImage defaultProjSprite;
    private BufferedImage IchorProjSprite;
    private BufferedImage meteorProjSprite;

    //Weapon sprites
    private BufferedImage defaultWepSprite;
    private BufferedImage blasterWepSprite;
    private BufferedImage undertakerWepSprite;
    private BufferedImage magnumWepSprite;


    public GameObjectRenderer(){

        try{
            //consumables
            healthSprite = ImageIO.read(new File("DuelingFates/Sources/item_Healing_Potion.png"));
            ammoSprite = ImageIO.read(new File("DuelingFates/Sources/item_Ammo_Pickup.png"));

            //projectiles
            defaultProjSprite = ImageIO.read(new File("DuelingFates/Sources/proj_Default_Bullet.png"));
            IchorProjSprite = ImageIO.read(new File("DuelingFates/Sources/proj_Ichor_Bullet.png"));
            meteorProjSprite = ImageIO.read(new File("DuelingFates/Sources/proj_Meteor_Bullet.png"));

            //weapons
            defaultWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_Handgun.png"));
            blasterWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_Phoenix_Blaster.png"));
            undertakerWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_The_Undertaker.png"));
            magnumWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_Venus_Magnum.png"));

        }catch (Exception e){

            e.printStackTrace();

        }

    }

    public void drawPlayer(Graphics2D graphics, Player player){

        if(player.facingRight){
            graphics.drawImage(player.animation.getImage(),
                              (int)(player.x-player.spriteWidth/2),
                              (int)(player.y-player.spriteHeight/2),
                              null);
        }

        //Mirroring animation
        else{
            graphics.drawImage(player.animation.getImage(),
                              (int)(player.x-player.spriteWidth/2 + player.spriteWidth),
                              (int)(player.y-player.spriteHeight/2),
                              -player.spriteWidth,
                              player.spriteHeight,
                              null);
        }

        //If we get shot, we are blinking red
        if(player.blinkRed){
            if(player.blinkCount % 10 < 5) return;
        }

        //csak kirajzolunk, az animáció a PlayerAnimation-ben lesz

    }

    public void drawConsumable(Graphics2D graphics, Consumable consumable){
        //ha nem null a consumable, akkor kirajzolja képernyőre


    }

    public void drawProjectile(Graphics2D graphics, Projectile projectile){
        //ha nem null a projectile, akkor kirajzolja képernyőre

        if(projectile.projectileType == Projectile.Types.DEFAULT){

        }

        if(projectile.projectileType == Projectile.Types.ICHOR){

        }

        if(projectile.projectileType == Projectile.Types.METEOR){

        }



    }

    public void drawWeapon(Graphics2D graphics, Weapon weapon){
        //kirajzolunk, a fegyver pozíciója a játékoshoz lesz kötve, de azt nem itt adjuk meg


    }


}
