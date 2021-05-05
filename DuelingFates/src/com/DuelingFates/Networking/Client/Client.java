package com.DuelingFates.Networking.Client;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
    private Socket socket;
    private String Name;

    public Client(String Name) {
        this.Name = Name;
    }

    @Override
    public void run() {
        System.out.println("Client socket started");

        while (true) {
            synchronized (this) {
                System.out.println("Server sync block.");
                try (Socket socket = new Socket("localhost", 6868)) {
                    this.socket = socket;
                    int state = socket.getInputStream().read();
                    System.out.println("Received: " + state);
                    this.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Client socket stopped.");
                }
            }

        }
    }
}
