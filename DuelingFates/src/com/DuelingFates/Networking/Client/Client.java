package com.DuelingFates.Networking.Client;
import com.DuelingFates.GameState.StateManager;
import com.DuelingFates.Main.MainProcess;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

import static com.DuelingFates.GameState.GamePlayState.*;
import static com.DuelingFates.Main.MainProcess.*;


public class Client implements Runnable {
    private Socket socket;
    private String name;
    private int port;

    public Client(String Name, int port) {
        this.name = Name;
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
                        if (stateManager.currentState == StateManager.States.JOINSTATE){
                            switch (command[0]) {                                           //command 0 a MAP, 1 amit küldünk
                                case ("MAP"):
                                    System.out.println(command[1]);
                                    MainProcess.setMapTemp(command[1]);
                                    break;
                                case ("TIME"):
                                    System.out.println(command[1]);
                                    MainProcess.setMatchDurationTemp(Integer.parseInt(command[1]));
                                    break;
                            }
                        }
                        if(stateManager.currentState == StateManager.States.GAMEPLAYSTATE) {
                            switch (command[0]) {
                                case ("LEFT"):
                                    System.out.println(value);
                                    enemyPlayer.setLeft(true);
                                    break;
                                case ("RIGHT"):
                                    System.out.println(value);
                                    enemyPlayer.setRight(true);
                                    break;
                                case ("JUMP"):
                                    System.out.println(value);
                                    enemyPlayer.setJumping(true);
                                    break;
                                case ("SHOOT"):
                                case ("STOPSHOOT"):
                                    System.out.println(value);
                                    enemyPlayer.setShooting();
                                    break;
                                case ("STOPLEFT"):
                                    System.out.println(value);
                                    enemyPlayer.setLeft(false);
                                    break;
                                case ("STOPRIGHT"):
                                    System.out.println(value);
                                    enemyPlayer.setRight(false);
                                    break;
                                case ("STOPJUMP"):
                                    System.out.println(value);
                                    enemyPlayer.setJumping(false);
                                    break;
                                //case("DAMAGE"):     System.out.println(value);  break;
                                case ("ENEMYSCORE"):
                                    System.out.println(value);
                                    enemyPlayer.setPlayerScore(Integer.parseInt(command[1]));
                                    break;
                                case ("ENEMYNAME"):
                                    System.out.println(command[1]);
                                    enemyPlayer.setPlayerName(command[1]);
                                    break;

                                /*case ("NAME"):
                                    System.out.println(command[1]);
                                    setHostPlayerName(command[1]);
                                    break;
                                case ("SCORE"):
                                    System.out.println(value);
                                   hostPlayer.setPlayerScore(Integer.parseInt(command[1]));
                                    break;
                                */

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
                                case("HPADD"):
                                    System.out.println("HP added.");
                                    enemyPlayer.setPlayerHealth(enemyPlayer.getPlayerHealth()+30);
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
