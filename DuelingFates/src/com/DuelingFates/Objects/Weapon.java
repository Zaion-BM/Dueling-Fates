package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;
import java.awt.event.KeyEvent;

public class Weapon extends GameObject{

    public enum WeaponModel{DEFAULT, UNDERTAKER, MAGNUM}
    public WeaponModel modelType;
    public final int[] DAMAGES = {10, 20, 40};
    public final int[] FIRERATE = {1, 2, 5};

    public Projectile projectile;

    //Weapon parameters
    private int weaponDmg;
    private int weaponFireRate;

    public Weapon(TileMap tileMap, WeaponModel modelType){
        super(tileMap);
        this.modelType = modelType;

        switch(modelType){
            case DEFAULT:       setWeaponDmg(DAMAGES[0]);              //firerate=1 -> 1 shoot = 1 bullet (bullet speed: ~0.1s/bullet)
                                setWeaponFireRate(FIRERATE[0]);
                                break;
            case UNDERTAKER:    setWeaponDmg(DAMAGES[1]);
                                setWeaponFireRate(FIRERATE[1]);         //firerate=5 -> 1shoot = 5 bullet (semi-automatic) (bullet speed: ~0.02s/bullet)
                                break;                                  //firerate==ammoqty -> 1shoot = all bullet (fully-automatic) (bullet speed: ~0.02s/bullet)
            case MAGNUM:        setWeaponDmg(DAMAGES[2]);
                                setWeaponFireRate(FIRERATE[2]);
                                break;
        }

        objectHeight = 13;
        objectWidth = 31;

    }

    public void shootWeapon(Player player){

        //NEM LETT IMPLEMENTÁLVA, időhiány miatt

        //player.setShooting();
        //player.setPlayerAmmoQty(player.getPlayerAmmoQty() - this.getWeaponFireRate());
    }

    //Implementation of getters and setters
    public void setModel(WeaponModel modelType, int weaponFireRate, int weaponDmg){
        this.modelType = modelType;
        this.weaponFireRate = weaponFireRate;
        this.weaponDmg = weaponDmg;
    }

    public WeaponModel getModelType() { return modelType; }

    public void setModelType(WeaponModel modelType) { this.modelType = modelType; }

    public Projectile getProjectile() { return projectile; }

    public void setProjectile(Projectile projectile) { this.projectile = projectile; }

    public int getWeaponDmg() { return weaponDmg; }

    public void setWeaponDmg(int weaponDmg) { this.weaponDmg = weaponDmg; }

    public int getWeaponFireRate() { return weaponFireRate; }

    public void setWeaponFireRate(int weaponFireRate) { this.weaponFireRate = weaponFireRate; }

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
