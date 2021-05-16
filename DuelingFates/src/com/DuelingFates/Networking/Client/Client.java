package com.DuelingFates.Networking.Client;
import com.DuelingFates.Objects.Player;
import com.DuelingFates.GameState.GamePlayState.*;

import javax.swing.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Queue;

import static com.DuelingFates.GameState.GamePlayState.*;


public class Client implements Runnable {
    private Socket socket;
    private String Name;
    private int port;

    public Client(String Name, int port) {
        this.Name = Name;
        this.port = port;
    }

    public int state;

    public int getState(){return this.state;}

    @Override
    public void run() {
        boolean tryConnect = true;
        Socket socket =null;
        String value="DEFAULT,DEFAULT";
        System.out.println("Client socket started");
        while(tryConnect) {
            try {
                socket = new Socket("localhost", port);
                tryConnect = false;
            }  catch (ConnectException e) {
                System.out.println("Connection failed");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

            synchronized (this) {
                try {
                    while (true) {
                        InputStreamReader streamReader= new InputStreamReader(socket.getInputStream());
                        BufferedReader reader= new BufferedReader(streamReader);
                        value="DEFAULT,DEFAULT";
                        value= reader.readLine();
                        String[] command = value.split(":",2);
                        //System.out.println(value);

                        switch(command[0]){
                            case("LEFT"):       System.out.println(value); clientPlayer.setLeft(true);                  break;
                            case("RIGHT"):      System.out.println(value); clientPlayer.setRight(true);                 break;
                            case("JUMP"):       System.out.println(value); clientPlayer.setJumping(true);               break;
                            case("SHOOT"):      System.out.println(value); clientPlayer.setShooting();                  break;
                            case("STOPLEFT"):   System.out.println(value); clientPlayer.setLeft(false);;                break;
                            case("STOPRIGHT"):  System.out.println(value); clientPlayer.setRight(false);                break;
                            case("STOPJUMP"):   System.out.println(value); clientPlayer.setJumping(false);              break;
                            case("STOPSHOOT"):  System.out.println(value); clientPlayer.setShooting();                  break;
                            //case("DAMAGE"):     System.out.println(value);                                              break;
                            case("ENEMYSCORE"): System.out.println(value);                                              break;
                            case("NAME"):       System.out.println(command[1]);  setClientPlayerName(command[1]);       break;
                            case("AMMOX"):      System.out.println(value);                                              break;
                            case("AMMOY"):      System.out.println(value);                                              break;
                            case("POTIONX"):    System.out.println(value);       healthPotion2.setPositionX(Integer.parseInt(command[1]));                                break;
                            case("POTIONY"):    System.out.println(value);                                              break;
                            case("MAP"):        System.out.println(command[1]);                                         break;
                            default:            System.out.println(value);                                              break;
                        }

                        this.wait(20);
                    }

                }
                catch (SocketException e){e.printStackTrace();}
                catch (InterruptedException e) { e.printStackTrace(); }
                catch (IOException e){ e.printStackTrace();}

            }

        }
}
