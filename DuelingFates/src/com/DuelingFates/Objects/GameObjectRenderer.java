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
    //private BufferedImage defaultProjSprite;
    //private BufferedImage IchorProjSprite;

    private BufferedImage defaultSteady;
    private BufferedImage defaultFiring;

    private BufferedImage undertakerSteady;
    private BufferedImage undertakerFiring;

    private BufferedImage magnumSteady;
    private BufferedImage magnumFiring;


    public GameObjectRenderer(){

        try{
            //consumables
            healthSprite = ImageIO.read(new File("DuelingFates/Sources/item_Healing_PotionBig.png"));
            ammoSprite = ImageIO.read(new File("DuelingFates/Sources/item_Ammo_Pickup.png"));

            //projectiles
            //defaultProjSprite = ImageIO.read(new File("DuelingFates/Sources/proj_Default_Bullet.png"));
            //IchorProjSprite = ImageIO.read(new File("DuelingFates/Sources/proj_Ichor_Bullet.png"));

            //Weapon sprites
            BufferedImage defaultWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_DefaultWeapon.png"));
            defaultSteady = defaultWepSprite.getSubimage(0,0,31,13);
            defaultFiring = defaultWepSprite.getSubimage(31,0,31,13);

            BufferedImage undertakerWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_TheUndertaker.png"));
            undertakerSteady = undertakerWepSprite.getSubimage(0,0,31,13);
            undertakerFiring = undertakerWepSprite.getSubimage(31,0,31,13);

            BufferedImage magnumWepSprite = ImageIO.read(new File("DuelingFates/Sources/wep_VenusMagnum.png"));
            magnumSteady = magnumWepSprite.getSubimage(0,0,31,13);
            magnumFiring = magnumWepSprite.getSubimage(31,0,31,13);



        }catch (Exception e){

            e.printStackTrace();

        }

    }

    public void drawPlayer(Graphics2D graphics, Player player){

        drawProjectile(graphics,player.getProjectile());

        if (!player.facingRight) {
                graphics.drawImage(player.animation.getImage(),
                        (int) (player.x - player.spriteWidth / 2),
                        (int) (player.y - player.spriteHeight / 2),
                        null);

                if(Player.IDLE == player.currentAction) {

                    switch (player.getPlayerWeapon().modelType) {

                        case DEFAULT: drawSteadyWeapon(graphics, defaultSteady, player); break;
                        case MAGNUM: drawSteadyWeapon(graphics,magnumSteady, player); break;
                        case UNDERTAKER: drawSteadyWeapon(graphics,undertakerSteady, player); break;

                    }
                }

                if(Player.SHOOTING == player.currentAction) {

                    switch (player.getPlayerWeapon().modelType) {

                        case DEFAULT: drawFiringWeapon(graphics, defaultFiring, player); break;
                        case MAGNUM: drawFiringWeapon(graphics,magnumFiring, player); break;
                        case UNDERTAKER: drawFiringWeapon(graphics,undertakerFiring, player); break;

                    }

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

                    switch (player.getPlayerWeapon().modelType) {

                        case DEFAULT: drawMirroredSteadyWeapon(graphics, defaultSteady, player); break;
                        case MAGNUM: drawMirroredSteadyWeapon(graphics,magnumSteady, player); break;
                        case UNDERTAKER: drawMirroredSteadyWeapon(graphics,undertakerSteady, player); break;

                    }
                }

                if(Player.SHOOTING == player.currentAction) {

                    switch (player.getPlayerWeapon().modelType) {

                        case DEFAULT: drawMirroredFiringWeapon(graphics, defaultFiring, player); break;
                        case MAGNUM: drawMirroredFiringWeapon(graphics,magnumFiring, player); break;
                        case UNDERTAKER: drawMirroredFiringWeapon(graphics,undertakerFiring, player); break;

                    }

                }

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

        for (Projectile value : projectile) {

            //Ha magnumot használunk akkor más legyen a sprite
            //TODO bugol, beraad a lőszer a playerbe ezzel a módszerrel
            /*if(player.getPlayerWeapon().modelType == Weapon.WeaponModel.MAGNUM) {
                value.animation.setFrames(value.spritesIchor);
            }*/

            //egyébként marad a default
            if (value.facingRight) {
                graphics.drawImage(value.animation.getImage(),
                        (int) (value.x - value.spriteWidth / 2),
                        (int) (value.y - value.spriteHeight / 2),
                        null);
            }

            //Mirroring animation
            else {
                graphics.drawImage(value.animation.getImage(),
                        (int) (value.x - value.spriteWidth / 2 + value.spriteWidth),
                        (int) (value.y - value.spriteHeight / 2),
                        -value.spriteWidth,
                        value.spriteHeight,
                        null);
            }

        }

    }

    public void drawSteadyWeapon(Graphics2D graphics, BufferedImage weapon, Player player){

        graphics.drawImage(weapon, (int) (player.x) - 36, (int) (player.y) - 3, null);

    }

    public void drawFiringWeapon(Graphics2D graphics, BufferedImage weapon, Player player){

        graphics.drawImage(weapon, (int) (player.x) - 35, (int) (player.y) - 3, null);

    }

    private void drawMirroredSteadyWeapon(Graphics2D graphics, BufferedImage weapon, Player player){

        graphics.drawImage(weapon,
                (int) (player.x) + 36,
                (int) (player.y) - 3,
                -weapon.getWidth(),
                weapon.getHeight(),
                null);

    }

    private void drawMirroredFiringWeapon(Graphics2D graphics, BufferedImage weapon, Player player){

        graphics.drawImage(weapon,
                (int) (player.x) + 35,
                (int) (player.y) - 3,
                -weapon.getWidth(),
                weapon.getHeight(),
                null);

    }

}
