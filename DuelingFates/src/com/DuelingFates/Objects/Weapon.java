package com.DuelingFates.Objects;

public class Weapon extends GameObject{

    public enum Model{DEFAULT, BLASTER, UNDERTAKER, MAGNUM};
    public Model modelType;
    public String BulletType;

    //weapon specs
    private int weaponDmg;
    private int weaponFireRate;


    public void Weapon(Model modelType, String bulletType){
        this.modelType = modelType;
        this.BulletType = bulletType;

        if(modelType == Model.DEFAULT){
            weaponDmg = 4;
            weaponFireRate = 10;    //jelentsen ez most akármit is
        }

        if(modelType == Model.BLASTER){

        }

        if(modelType == Model.UNDERTAKER){

        }

        if(modelType == Model.MAGNUM){

        }

    }


    public void shootWeapon(Weapon weapon){

        //create projectile, a típus alapján
        //csökkentjük a Player töltényeinek számát
        //ekkor meg kell jeleníteni a player mellett a fegyvert, ha nem lövünk akkor hide oljuk


    }


}
