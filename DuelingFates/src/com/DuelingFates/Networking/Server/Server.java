package com.DuelingFates.Networking.Server;

import com.DuelingFates.GameState.StateManager;
import com.DuelingFates.Main.MainProcess;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import static com.DuelingFates.Main.MainProcess.*;

public class Server implements Runnable {

    private Queue<String> messageQueue;
    private ServerSocket serverSocket = null;
    private int port;


    public Server(Queue<String> messageQueue, int port) {
        this.messageQueue = messageQueue;
        this.port = port;
    }

    public void setPort(int port){
        this.port = port;
    }


    @Override
    public void run() {
        System.out.println("Server socket started");
        try {
            serverSocket = new ServerSocket(port);
        }
        catch(BindException e){
            System.out.println("Host already exists. Please join to session.");
            System.exit(2);
        }
        catch(IOException exp){
            exp.printStackTrace();
        }
        try{
            Socket socket = serverSocket.accept();

            //itt várunk acceptig
            messageQueue.add("MAP:".concat(MainProcess.getMapTemp()));
            messageQueue.add("TIME:".concat(String.valueOf(MainProcess.getMatchDurationTemp())));
            messageQueue.add("CHAR:".concat(MainProcess.getEnemyCharacterTemp()));

            stateManager.setState(StateManager.States.GAMEPLAYSTATE);
            while (true) {
                try {
                    synchronized (this){
                        if (!messageQueue.isEmpty()) {
                            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                            writer.write(messageQueue.remove().concat("\n"));
                            writer.flush();
                            socket.getOutputStream().flush();
                        }
                        this.wait(20);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(IOException e){e.printStackTrace();}
    }
}