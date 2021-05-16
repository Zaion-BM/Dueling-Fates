package com.DuelingFates.Networking.Server;

import com.DuelingFates.GameState.StateManager;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
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

    @Override
    public void run() {
        System.out.println("Server socket started");
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                System.out.println("Server socket stopped");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}