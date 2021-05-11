package com.DuelingFates.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TileMap {

    //map paramatérek
    private int[][] mapLayout;                          //map 2D-s tömb, X és Y irány
    private int mapRows;                                //oszlopok és sorok száma
    private int mapColumns;
    private int mapWidth;
    private int mapHeight;

    @SuppressWarnings("FieldCanBeLocal")
    private final int mapStartX = 0;                    //innen kezddődik a map kirajzolása
    @SuppressWarnings("FieldCanBeLocal")
    private final int mapStartY = 0;

    //Tile paraméterek
    private final int tileSize;
    private final Tile[] tiles;
    private Tile tileLonely;
    private Tile tileUp;
    private Tile tileDown;
    private Tile tileLeft;
    private Tile tileRight;
    private Tile tileBottom;
    private Tile tileHorizontal;
    private Tile tileVertical;

    //TODO SPIKE: 64-esre felhúzni a 30x30-as tilet, és elhelyezni a mappon VAGY mivel ezen át tudsz menni,
    // így lehet egyszerűbb külön elhelyezni és a mapot ezt békén hagyni

    public TileMap(int tileSize) {                      //1920*1080 64x64 miatt 30db soronként és 16 oszloponként     //1600*9000 53*53

        this.tileSize = tileSize;
        this.tiles = new Tile[9];                       //mert összesen 9 tilet használunk a mapon

    }

    public void loadTilesFormFiles(String fileLocation) {

        try {

            tileLonely = new Tile(ImageIO.read(new File(fileLocation + "TileLonely.png")));
            tileUp = new Tile(ImageIO.read(new File(fileLocation + "TileUp.png")));
            tileDown = new Tile(ImageIO.read(new File(fileLocation + "TileDown.png")));
            tileLeft = new Tile(ImageIO.read(new File(fileLocation + "TileLeft.png")));
            tileRight = new Tile(ImageIO.read(new File(fileLocation + "TileRight.png")));
            tileBottom = new Tile(ImageIO.read(new File(fileLocation + "TileBottom.png")));
            tileHorizontal = new Tile(ImageIO.read(new File(fileLocation + "TileMiddleH.png")));
            tileVertical = new Tile(ImageIO.read(new File(fileLocation + "TileMiddleV.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        tiles[0] = null;
        tiles[1] = tileUp;
        tiles[2] = tileLonely;
        tiles[3] = tileLeft;
        tiles[4] = tileRight;
        tiles[5] = tileHorizontal;
        tiles[6] = tileVertical;
        tiles[7] = tileBottom;
        tiles[8] = tileDown;

    }

    public void loadTilesToMap(String textMapLocation) {

        BufferedReader reader;

        try {

            reader = new BufferedReader(new FileReader(textMapLocation));

            //első sor a map szélességét adja
            mapColumns = Integer.parseInt(reader.readLine());                           //String konvertálása Int-re

            //második sor a map magasságát adja
            mapRows = Integer.parseInt(reader.readLine());

            mapLayout = new int[mapRows][mapColumns];                                   //mapLayout létrehozása

            mapWidth=mapColumns*tileSize;                                               //Map szélesség (30*64=1920)
            mapHeight=mapRows*tileSize;                                                 //Map magasság (16*64=1024) //(16*64+56=1080)

            String divider = "\\t";                                                     //számok elválasztása tabbal
            int row = 0;

            while (row != mapRows) {                                                    //a fájl végéig olvasunk - 16 sor
                String rowLine = reader.readLine();                                     //sor beolvasása
                String[] rowWithoutTab = rowLine.split(divider);                        //tabok kiszedése

                for (int i = 0; i < mapColumns; i++) {                                  //oszlopokon lépkedünk - 30

                    mapLayout[row][i] = Integer.parseInt(rowWithoutTab[i]);             //az adott oszlop a map oszlopa lesz

                }
                row++;                                                                  //következő sorra lépünk
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw (Graphics2D graphics){

        for(int row = 0; row < mapRows; row++){                                         //soron lépkedünk

            for (int col = 0; col < mapColumns; col++){                                 //oszlopon haladunk

                //0 esetén nem rajzolunk
                if (mapLayout[row][col] == 0) continue;

                //minden más esetben a tiles[] tömb adott pozíciójában található elemét rajzoljuk (pl. 5),
                //mely megegyezik a mapLayout[][] "mátrix" adott elemének értékével.

                graphics.drawImage(tiles[mapLayout[row][col]].getTileImage(),
                        mapStartX + col*tileSize,
                        mapStartY + row*tileSize,
                        null);

                /*Ha kisebb felbontáson szeretnénk játszani akkor az alábbi kirajzolás skálázza az imaget
                Ehhez egyedül a tile méretét kell tudni, a beolvasott tile mindig 64 pixeles
                A kirajzoláskor vett pixelszélességgel kell a játékban is számolni, ami 53 1600*900-as febontáson
                graphics.drawImage(tiles[mapLayout[row][col]].getTileImage().getScaledInstance(
                                (int)(tiles[mapLayout[row][col]].getTileImage().getWidth()*((float)tileSize/64f)),
                                (int)(tiles[mapLayout[row][col]].getTileImage().getWidth()*((float)tileSize/64f)),
                                Image.SCALE_SMOOTH),
                                mapStartX + (int)(col*tileSize*(tileSize/64f)),
                                mapStartY + (int)(row*tileSize*(tileSize/64f)),null);*/

            }

        }

    }

    /*
     * Check if in mapLayout [row][col] is a tile
     * */
    public int getType(int row, int col){
        return mapLayout[row][col];
    }
    public int getMapWidth() {
        return mapWidth;
    }
    public int getMapHeight() {
        return mapHeight;
    }
    public int getTileSize() {
        return tileSize;
    }
    public int getMapRows() { return mapRows; }
    public int getMapColumns() { return mapColumns; }

}

