package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Projectile extends GameObject{

    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private int shootRange;
    private int shoot;

    public enum Types {DEFAULT, ICHOR, METEOR}
    public Types projectileType;
    private float projectileSpeed;

    public Projectile(TileMap tileMap, boolean right){
        super(tileMap);
       /* switch(projectileType) {
            case METEOR:
                setProjectileSpeed(15);
                break;
            case ICHOR:
                setProjectileSpeed(13);
                break;
            case DEFAULT:
                setProjectileSpeed(10);
        }*/
        setProjectileSpeed(20);
        shootRange=40; //TODO: teszt , lövedk eltűnik egy megadott távolság után  shootRange*projectileSpeed (40*20 = 800 pixel távolság) kb félpálya
        shoot=0; //update -ben shoot++ és ha shootRange -szer lefut az update, akkor remove bullet
        if(right) deltaX=projectileSpeed;
        else deltaX=-projectileSpeed;
        spriteHeight=2;
        spriteWidth=20;
        objectHeight=2;
        objectWidth=20;

        //load sprites
        try{
            BufferedImage spritesheet = ImageIO.read(new File(
                            "DuelingFates/Sources/proj_Default_Bullet.png"
                    )
            );
            sprites= new BufferedImage[1];
            sprites[0]=spritesheet.getSubimage(0*spriteWidth,0,spriteWidth,spriteHeight);
            animation= new Animation();
            animation.setFrames(sprites);
            animation.setDelay(10);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setHit(){
        if(hit) return;
        hit=true;
        deltaX=0;
    }
    public boolean shouldRemove(){
        return remove;
    }
    public void update(){
        checkTileMapCollision();
        setPosition(tempX,tempY);
        shoot++;
        if(deltaX==0 && !hit){ setHit();}
        animation.update();
        if(hit && animation.hasPlayedOnce() || shoot>shootRange){
            shoot=0;
            remove=true;
        }
    }
    public void draw(Graphics2D g){

    }
    /*
    * Implementation of getters and setters
    * */
    public Types getProjectileType() {
        return projectileType;
    }

    public void setProjectileType(Types projectileType) {
        this.projectileType = projectileType;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setProjectileSpeed(float projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
