package com.DuelingFates.Networking.Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

public class Server implements Runnable {

    private Queue<String> messageQueue;
    private ServerSocket serverSocket = null;

    /**
     * A ServerSocketHandler osztály konstruktorában vár egy üzenetsort amin keresztül kapja majd később az üzeneteket
     * <p>
     * Nem a ConcurrentLinkedQueue-t vesszük át! Interface-t veszünk át paraméterül hogy minél rugalmasabb legyen
     * az implementációnk.
     *
     * @param messageQueue
     */
    public Server(Queue<String> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        System.out.println("Server socket started");
        try {
            serverSocket = new ServerSocket(6868);

            while (true) {
                try {
                    Socket connection = serverSocket.accept();
                    System.out.println("Connection established.");
                    synchronized (this){
                        System.out.println("Server sync block.");
                        while (!messageQueue.isEmpty()) {
                            String msg = messageQueue.remove();
                            System.out.println(msg);
                            connection.getOutputStream().write(msg.getBytes());
                            System.out.println();
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
            // itt csak azokat a hibákat kell kezelni, ami a server socket készítésekor merültek fel
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Server socket stopped");

    }
}