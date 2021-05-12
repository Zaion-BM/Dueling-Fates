package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;

import java.awt.event.KeyEvent;

public class Weapon extends GameObject{

    public enum WeaponModel{DEFAULT, UNDERTAKER, MAGNUM}
    public int type;
    public WeaponModel modelType;
    public Projectile projectile;

    //Weapon parameters
    private int weaponDmg;
    private int weaponFireRate;


    public Weapon(TileMap tileMap,int modelType){
        super(tileMap);
        this.type = modelType;
        this.projectile = projectile;

        switch(type){
            case 2:    setWeaponDmg(20);            //firerate=1 -> 1 shoot = 1 bullet (bullet speed: ~0.1s/bullet)
                            setWeaponFireRate(1);
                            break;
            case 1:    setWeaponDmg(30);
                            setWeaponFireRate(5);   //firerate=5 -> 1shoot = 5 bullet (semi-automatic) (bullet speed: ~0.02s/bullet)
                            break;                  //firerate==ammoqty -> 1shoot = all bullet (fully-automatic) (bullet speed: ~0.02s/bullet)
            case 0:    setWeaponDmg(40);
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
