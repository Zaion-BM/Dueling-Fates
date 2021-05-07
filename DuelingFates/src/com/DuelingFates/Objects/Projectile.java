package com.DuelingFates.Objects;

import com.DuelingFates.TileMap.TileMap;

public class Projectile extends GameObject{

    public enum Types {DEFAULT, ICHOR, METEOR}
    public Types projectileType;
    
    private float projectileSpeed;

    public Projectile(Types projectileType, TileMap tileMap){
        super(tileMap);
        switch(projectileType) {
            case METEOR:
                setProjectileSpeed(15);
                break;
            case ICHOR:
                setProjectileSpeed(13);
                break;
            case DEFAULT:
                setProjectileSpeed(10);
        }

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
}
