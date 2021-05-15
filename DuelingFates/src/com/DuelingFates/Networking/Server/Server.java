package com.DuelingFates.Networking.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Queue;

public class Server implements Runnable {

    private Queue<Integer> messageQueue;
    private ServerSocket serverSocket = null;
    private int port;


    public Server(Queue<Integer> messageQueue, int port) {
        this.messageQueue = messageQueue;
        this.port = port;
    }

    @Override
    public void run() {
        System.out.println("Server socket started");
        try {
            serverSocket = new ServerSocket(port);
            Socket connection = serverSocket.accept();
            while (true) {
                try {
                    synchronized (this){
                        if (!messageQueue.isEmpty()) {
                            PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);
                            writer.write(messageQueue.remove());
                            writer.flush();
                            connection.getOutputStream().flush();
                        }
                        this.wait(10);
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