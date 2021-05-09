package com.DuelingFates.Objects;
import com.DuelingFates.TileMap.TileMap;
import java.awt.*;
/*
 * This is a super class, all objects have these methods
 * */
public abstract class GameObject {
    //Tile parameters
    protected TileMap tileMap;
    protected int tileSize;
    //Position and vector parameters
    protected float x;
    protected float y;
    protected float deltaX;
    protected float deltaY;
    //Dimensions
    protected int spriteWidth;
    protected int spriteHeight;
    //Collision box
    protected int objectWidth;
    protected int objectHeight;
    //Collision parameters
    protected int currentRow;
    protected int currentColumn;
    protected float nextX;
    protected float nextY;
    protected float tempX;
    protected float tempY;
    //4-point(corner) method for collision
    protected boolean topLeftCorner;
    protected boolean topRightCorner;
    protected boolean bottomLeftCorner;
    protected boolean bottomRightCorner;
    //Movement
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;
    //Movement attributes
    protected float moveSpeed;
    protected float maxSpeed;
    protected float stopSpeed;
    protected float fallSpeed;
    protected float maxFallSpeed;
    protected float jumpStart;
    protected float stopJumpSpeed;
    // animation
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;
    protected boolean facingRight;
    /*
     * Implementation of constructors
     * */
    public GameObject(TileMap tileMap){
        this.tileMap = tileMap;
        this.tileSize=tileMap.getTileSize();
        animation = new Animation();
        facingRight = true;
    }
    /*
     * Implementation of game engine
     * */
    /*
     * Creating new rectangle objects (used for intersection detection)
     * */
    public Rectangle getRectangle(){
        return new Rectangle((int)(x-objectWidth), (int)(y-objectHeight), objectWidth, objectHeight);
    }
    /*
     * Tells if the 2 objects have intersection (Used by projectiles/consumables)
     * */
    public boolean objectIntersection(GameObject object){
        Rectangle r1 = getRectangle();
        Rectangle r2 = object.getRectangle();
        return r1.intersects(r2);
    }
    /*
     * The 4-point(corner) method
     * Calculating the corners of object if they hit any tile nearby
     * */
    public void calculateCorners(float x, float y){
        /*
         * 4 Tile-s in the Player's neighbourhood
         * */
        int leftTile=(int)(x-objectWidth/2)/tileSize;
        int rightTile=(int)(x+objectWidth/2-1)/tileSize;
        int topTile=(int)(y-objectHeight/2)/tileSize;
        int bottomTile=(int)(y+objectHeight/2-1)/tileSize;
        if(topTile < 0 || bottomTile >= tileMap.getMapRows() || leftTile < 0 || rightTile >= tileMap.getMapColumns()) {
            topLeftCorner = topRightCorner = bottomLeftCorner = bottomRightCorner = false;
            return;
        }
        /*
         * Check if the Tile is solid or not
         * */
        int tl=tileMap.getType(topTile,leftTile);
        int tr=tileMap.getType(topTile,rightTile);
        int bl=tileMap.getType(bottomTile,leftTile);
        int br=tileMap.getType(bottomTile,rightTile);
        /*
         * 4 corners
         * */
        topLeftCorner=(tl==1);
        topRightCorner=(tr==1);
        bottomLeftCorner=(bl==1);
        bottomRightCorner=(br==1);
    }
    /*
     * Collision detection between tiles and objects
     * */
    public void checkTileMapCollision(){
        currentRow=(int)(x/tileSize);
        currentRow=(int)(y/tileSize);
        nextX=x+deltaX;
        nextY=y+deltaY;
        tempX=x;
        tempY=y;
        /*
         * If we move vertically
         * */
        calculateCorners(x,nextY);
        if(deltaY<0){//If we're going upwards
            if(topLeftCorner||topRightCorner){//if there is solid tile above us
                deltaY=0; //stop going upwards
                tempY=currentRow*tileSize+(float)objectHeight/2; //set object just below the tile we hit
            }
            else{//if there is no solid tile above us, we are good to go upwards
                tempY-=deltaY;
            }
        }
        if(deltaY>0){ //If we're going downwards
            if(bottomLeftCorner||bottomRightCorner){
                deltaY=0; //stop going downwards
                falling=false; //stop falling
                tempY=(currentRow+1)*tileSize-(float)objectHeight/2; //set object just above the tile we hit
            }
            else{//we are good to go downwards
                tempY+=deltaY;
            }
        }
        /*
         * If we move horizontally
         * */
        calculateCorners(nextX,y);
        if(deltaX<0){//If we're going left
            if(topLeftCorner||bottomLeftCorner){
                deltaX=0; //stop going left
                tempX=currentColumn*tileSize+(float)objectWidth/2; //set object just right the tile we hit
            }
            else{//we are good to go left
                tempX-=deltaX;
            }
        }
        if(deltaX>0){//If we're going right
            if(topRightCorner||bottomRightCorner){
                deltaX=0; //stop going right
                tempX=(currentColumn+1)*tileSize-(float)objectWidth/2; //set object just left the tile we hit
            }
            else{//we are good to go right
                tempX+=deltaX;
            }

        }
        /*
         *  If we no longer standing on solid ground
         * */
        if(!falling){
            calculateCorners(x,nextY+1);
            if(!bottomLeftCorner && !bottomRightCorner){//if there is no solid ground under us
                falling=true;//start falling
            }
        }
    }
    /*
     * Implementation of getters and setters
     * */
    public void setPosition(float x,float y){
        this.x=x;
        this.y=y;
    }
    public void setVector(float deltaX, float deltaY){
        this.deltaX=deltaX;
        this.deltaY=deltaY;
    }

    public void draw(Graphics2D g){
        if(facingRight){
            g.drawImage(animation.getImage(),(int)(x-spriteWidth/2),(int)(y-spriteHeight/2),null);
        }
        else{//Mirroring animation
            g.drawImage(animation.getImage(),(int)(x-spriteWidth/2+spriteWidth),(int)(y-spriteHeight/2),-spriteWidth,spriteHeight,null);
        }
    }
}//end of class
