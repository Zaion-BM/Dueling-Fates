package com.DuelingFates.Networking.Client;
import com.DuelingFates.GameState.StateManager;
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
import static com.DuelingFates.Main.MainProcess.*;


public class Client implements Runnable {
    private Socket socket;
    private String Name;
    private int port;

    public Client(String Name, int port) {
        this.Name = Name;
        this.port = port;
    }

    public static float ammox=-100;
    public static float ammoy=-100;
    public static float potionx=-100;
    public static float potiony=-100;


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
                        if(stateManager.currentState == StateManager.States.GAMEPLAYSTATE) {
                            switch (command[0]) {
                                case ("LEFT"):
                                    System.out.println(value);
                                    clientPlayer.setLeft(true);
                                    break;
                                case ("RIGHT"):
                                    System.out.println(value);
                                    clientPlayer.setRight(true);
                                    break;
                                case ("JUMP"):
                                    System.out.println(value);
                                    clientPlayer.setJumping(true);
                                    break;
                                case ("SHOOT"):
                                    System.out.println(value);
                                    clientPlayer.setShooting();
                                    break;
                                case ("STOPLEFT"):
                                    System.out.println(value);
                                    clientPlayer.setLeft(false);
                                    ;
                                    break;
                                case ("STOPRIGHT"):
                                    System.out.println(value);
                                    clientPlayer.setRight(false);
                                    break;
                                case ("STOPJUMP"):
                                    System.out.println(value);
                                    clientPlayer.setJumping(false);
                                    break;
                                case ("STOPSHOOT"):
                                    System.out.println(value);
                                    clientPlayer.setShooting();
                                    break;
                                //case("DAMAGE"):     System.out.println(value);                                              break;
                                case ("ENEMYSCORE"):
                                    System.out.println(value);
                                    clientPlayer.setPlayerScore(Integer.parseInt(command[1]));
                                    break;
                                case ("NAME"):
                                    System.out.println(command[1]);
                                    setClientPlayerName(command[1]);
                                    break;
                                /*case ("AMMOX"):
                                    System.out.println(value);
                                    ammox = Float.parseFloat(command[1]);
                                    break;
                                case ("AMMOY"):
                                    System.out.println(value);
                                    ammoy = Float.parseFloat(command[1]);
                                    break;
                                case ("POTIONX"):
                                    System.out.println(value);
                                    potionx = Float.parseFloat(command[1]);
                                    break;
                                case ("POTIONY"):
                                    System.out.println(value);
                                    potiony = Float.parseFloat(command[1]);
                                    break;*/
                                case ("MAP"):
                                    System.out.println(command[1]);
                                    break;
                                case("HPADD"):
                                    System.out.println("HP added.");
                                    clientPlayer.setPlayerHealth(clientPlayer.getPlayerHealth()+30);
                                    break;
                                default:
                                    System.out.println(value);
                                    break;
                            }
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
