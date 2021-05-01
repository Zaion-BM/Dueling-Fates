package com.DuelingFates.Objects;

public class Projectile extends GameObject{

    public enum Types{DEFAULT, ICHOR, METEOR};
    public Types projectileType;
    
    private float projectileSpeed;

    public Projectile(Types projectileType){

        if(projectileType == Types.DEFAULT) {
            projectileSpeed = 10;
        }

        if(projectileType == Types.ICHOR) {
            projectileSpeed = 13;
        }

        if(projectileType == Types.METEOR) {
            projectileSpeed = 15;
        }


    }


}
