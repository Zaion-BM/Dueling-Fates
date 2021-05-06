package com.DuelingFates.Networking.Server;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Queue;

public class Server implements Runnable {

    private Queue<String> messageQueue;
    private ServerSocket serverSocket = null;


    public Server(Queue<String> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        System.out.println("Server socket started");
        try {
            serverSocket = new ServerSocket(6868);
            Socket connection = serverSocket.accept();
            while (true) {
                try {
                    synchronized (this){
                        if (!messageQueue.isEmpty()) {
                            String msg = messageQueue.remove();
                            System.out.print(msg);
                            PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);
                            writer.write(msg);
                            writer.flush();
                            connection.getOutputStream().flush();
                        }
                        this.wait(10);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
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