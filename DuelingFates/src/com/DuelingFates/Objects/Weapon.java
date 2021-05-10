package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;

import java.awt.event.KeyEvent;

public class Weapon extends GameObject{


    public enum WeaponModel{DEFAULT, BLASTER, UNDERTAKER, MAGNUM}
    public WeaponModel modelType;
    public Projectile projectile;

    //Weapon parameters
    private int weaponDmg;
    private int weaponFireRate;


    public Weapon(TileMap tileMap,WeaponModel modelType, Projectile projectile){
        super(tileMap);
        this.modelType = modelType;
        this.projectile = projectile;

        switch(modelType){
            case MAGNUM:    setWeaponDmg(20);       //firerate=1 -> 1 shoot = 1 bullet (bullet speed: ~0.1s/bullet)
                            setWeaponFireRate(1);
                            break;
            case UNDERTAKER:setWeaponDmg(40);
                            setWeaponFireRate(1);
                            break;
            case BLASTER:   setWeaponDmg(30);
                            setWeaponFireRate(5);   //firerate=5 -> 1shoot = 5 bullet (semi-automatic) (bullet speed: ~0.02s/bullet)
                            break;                  //firerate==ammoqty -> 1shoot = all bullet (fully-automatic) (bullet speed: ~0.02s/bullet)
            case DEFAULT:   setWeaponDmg(10);
                            setWeaponFireRate(1);
        }

    }


    public void shootWeapon(Weapon weapon){

        //create projectile, a típus alapján
        //csökkentjük a Player töltényeinek számát
        //ekkor meg kell jeleníteni a player mellett a fegyvert, ha nem lövünk akkor hideoljuk

    }


    /*
     * Implementation of getters and setters
     * */
    public WeaponModel getModelType() {
        return modelType;
    }

    public void setModelType(WeaponModel modelType) {
        this.modelType = modelType;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public int getWeaponDmg() {
        return weaponDmg;
    }

    public void setWeaponDmg(int weaponDmg) {
        this.weaponDmg = weaponDmg;
    }

    public int getWeaponFireRate() {
        return weaponFireRate;
    }

    public void setWeaponFireRate(int weaponFireRate) {
        this.weaponFireRate = weaponFireRate;
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
