package com.DuelingFates.Objects;

public class Weapon extends GameObject{

    public enum WeaponModel{DEFAULT, BLASTER, UNDERTAKER, MAGNUM}
    public WeaponModel modelType;
    public String bulletType;

    //Weapon parameters
    private int weaponDmg;
    private int weaponFireRate;


    public Weapon(WeaponModel modelType, String bulletType){
        this.modelType = modelType;
        this.bulletType = bulletType;

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
        //ekkor meg kell jeleníteni a player mellett a fegyvert, ha nem lövünk akkor hide oljuk


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

    public String getBulletType() {
        return bulletType;
    }

    public void setBulletType(String bulletType) {
        this.bulletType = bulletType;
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
}
