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

import static com.DuelingFates.GameState.GamePlayState.clientPlayer;


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
                        state = socket.getInputStream().read();
                        //System.out.println("Received: "+state);
                        switch(state){
                            case(0):System.out.println("LEFT"); clientPlayer.setLeft(true); break;
                            case(1):System.out.println("RIGHT");clientPlayer.setRight(true);break;
                            case(2):System.out.println("JUMP");clientPlayer.setJumping(true);break;
                            case(3):System.out.println("SHOOT");clientPlayer.setShooting();break;
                            case(4):System.out.println("STOPLEFT");clientPlayer.setLeft(false);;break;
                            case(5):System.out.println("STOPRIGHT");clientPlayer.setRight(false);break;
                            case(6):System.out.println("STOPJUMP");clientPlayer.setJumping(false);break;
                            case(7):System.out.println("STOPSHOOT");clientPlayer.setShooting();break;
                        }

                        this.wait(10);
                    }

                }
                catch (SocketException e){e.printStackTrace();}
                catch (InterruptedException e) { e.printStackTrace(); }
                catch (IOException e){ e.printStackTrace();}

            }

        }
}
