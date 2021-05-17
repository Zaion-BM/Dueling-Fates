package com.DuelingFates.Objects;

import com.DuelingFates.Objects.Consumable.Consumable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

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
    private BufferedImage defaultSteady;
    private BufferedImage defaultFiring;


    private BufferedImage undertakerWepSprite;
    private BufferedImage undertakerSteady;
    private BufferedImage undertakerFiring;

    private BufferedImage magnumWepSprite;
    private BufferedImage magnumSteady;
    private BufferedImage magnumFiring;


    public GameObjectRenderer(){

        try{
            //consumables
            healthSprite = ImageIO.read(new File("DuelingFates/Sources/item_Healing_PotionBig.png"));
            ammoSprite = ImageIO.read(new File("DuelingFates/Sources/item_Ammo_Pickup.png"));

            //projectiles
            defaultProjSprite = ImageIO.read(new File("DuelingFates/Sources/proj_Default_Bullet.png"));
            IchorProjSprite = ImageIO.read(new File("DuelingFates/Sources/proj_Ichor_Bullet.png"));

            //weapons
            defaultWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_DefaultWeapon.png"));
            defaultSteady = defaultWepSprite.getSubimage(0,0,31,13);
            defaultFiring = defaultWepSprite.getSubimage(31,0,31,13);

            undertakerWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_TheUndertaker.png"));
            undertakerSteady = undertakerWepSprite.getSubimage(0,0,31,13);
            undertakerFiring = undertakerWepSprite.getSubimage(31,0,31,13);

            magnumWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_VenusMagnum.png"));
            magnumSteady = magnumWepSprite.getSubimage(0,0,31,13);
            magnumFiring = magnumWepSprite.getSubimage(31,0,31,13);



        }catch (Exception e){

            e.printStackTrace();

        }

    }

    public void drawPlayer(Graphics2D graphics, Player player){

        /*if(player.getPlayerCharacter().equals(Player.PIRATE)) {

            if (player.facingRight) {
                graphics.drawImage(player.animation.getImage(),
                        (int) (player.x - player.spriteWidth / 2),
                        (int) (player.y - player.spriteHeight / 2),
                        null);

            }

            //Mirroring animation
            else {
                graphics.drawImage(player.animation.getImage(),
                        (int) (player.x - player.spriteWidth / 2 + player.spriteWidth),
                        (int) (player.y - player.spriteHeight / 2),
                        -player.spriteWidth,
                        player.spriteHeight,
                        null);
            }

        }*/

        drawProjectile(graphics,player.getProjectile());

        if(player.getPlayerCharacter().equals(Player.PIRATE)) {

            if (!player.facingRight) {
                graphics.drawImage(player.animation.getImage(),
                        (int) (player.x - player.spriteWidth / 2),
                        (int) (player.y - player.spriteHeight / 2),
                        null);
                if(Player.IDLE == player.currentAction) {
                    graphics.drawImage(defaultSteady, (int) (player.x) - 36, (int) (player.y) - 3, null);
                }
                if(Player.SHOOTING == player.currentAction) {
                    graphics.drawImage(defaultFiring, (int) (player.x) - 34, (int) (player.y) - 3, null);
                }
            }

            //Mirroring animation
            else {
                graphics.drawImage(player.animation.getImage(),
                        (int) (player.x - player.spriteWidth / 2 + player.spriteWidth),
                        (int) (player.y - player.spriteHeight / 2),
                        -player.spriteWidth,
                        player.spriteHeight,
                        null);

                if(Player.IDLE == player.currentAction) {
                    graphics.drawImage(defaultSteady,
                            (int) (player.x) + 36,
                            (int) (player.y) - 3,
                            -defaultSteady.getWidth(),
                            defaultSteady.getHeight(),
                            null);
                }

                if(Player.SHOOTING == player.currentAction) {
                    graphics.drawImage(defaultFiring,
                            (int) (player.x) + 34,
                            (int) (player.y) - 3,
                            -defaultFiring.getWidth(),
                            defaultFiring.getHeight(),
                            null);
                }
            }

        }

        if(player.getPlayerCharacter().equals(Player.POSSESSED)) {

            if (!player.facingRight) {
                graphics.drawImage(player.animation.getImage(),
                        (int) (player.x - player.spriteWidth / 2),
                        (int) (player.y - player.spriteHeight / 2),
                        null);
                if(Player.IDLE == player.currentAction) {
                    graphics.drawImage(magnumSteady, (int) (player.x) - 36, (int) (player.y) - 3, null);
                }
                if(Player.SHOOTING == player.currentAction) {
                    graphics.drawImage(magnumFiring, (int) (player.x) - 35, (int) (player.y) - 3, null);
                }
            }

            //Mirroring animation
            else {
                graphics.drawImage(player.animation.getImage(),
                        (int) (player.x - player.spriteWidth / 2 + player.spriteWidth),
                        (int) (player.y - player.spriteHeight / 2),
                        -player.spriteWidth,
                        player.spriteHeight,
                        null);

                if(Player.IDLE == player.currentAction) {
                    graphics.drawImage(magnumSteady,
                            (int) (player.x) + 36,
                            (int) (player.y) - 3,
                            -magnumSteady.getWidth(),
                            magnumSteady.getHeight(),
                            null);
                }

                if(Player.SHOOTING == player.currentAction) {
                    graphics.drawImage(magnumFiring,
                            (int) (player.x) + 35,
                            (int) (player.y) - 3,
                            -magnumFiring.getWidth(),
                            magnumFiring.getHeight(),
                            null);
                }

            }

        }


        //If we get shot, we are blinking red
        if(player.blinkRed){
            if(player.blinkCount % 10 < 5) return;
        }

    }

    public void drawHealthPotion(Graphics2D graphics, Consumable consumable){

        //ha nem null a consumable, akkor kirajzoljuk képernyőre
        graphics.drawImage(healthSprite,(int)(consumable.getPositionX()),
                          (int)(consumable.getPositionY()), null);

    }

    public void drawAmmoPickup(Graphics2D graphics, Consumable consumable){

        //ha nem null a consumable, akkor kirajzoljuk képernyőre
        graphics.drawImage(ammoSprite,(int)(consumable.getPositionX()),
                (int)(consumable.getPositionY()), null);

    }

    public void drawProjectile(Graphics2D graphics, ArrayList<Projectile> projectile){

        //ha nem null a projectile, akkor kirajzolja képernyőre
        for(int i=0; i<projectile.size();i++) {
            if (projectile.get(i).projectileType == Projectile.Types.DEFAULT){

            }

            if (projectile.get(i).projectileType == Projectile.Types.ICHOR) {

            }

            if (projectile.get(i).facingRight) {
                graphics.drawImage(projectile.get(i).animation.getImage(),
                        (int) (projectile.get(i).x - projectile.get(i).spriteWidth / 2),
                        (int) (projectile.get(i).y - projectile.get(i).spriteHeight / 2),
                        null);
            }

            //Mirroring animation
            else {
                graphics.drawImage(projectile.get(i).animation.getImage(),
                        (int) (projectile.get(i).x - projectile.get(i).spriteWidth / 2 + projectile.get(i).spriteWidth),
                        (int) (projectile.get(i).y - projectile.get(i).spriteHeight / 2),
                        -projectile.get(i).spriteWidth,
                        projectile.get(i).spriteHeight,
                        null);
            }

            //draw projectile
            projectile.get(i).draw(graphics);
        }

    }

    public void drawWeapon(Graphics2D graphics, Weapon weapon){
        //kirajzolunk, a fegyver pozíciója a játékoshoz lesz kötve, de azt nem itt adjuk meg



    }


}
