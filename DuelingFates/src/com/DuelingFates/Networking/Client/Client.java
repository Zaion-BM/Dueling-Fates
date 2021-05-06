package com.DuelingFates.Networking.Client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Client implements Runnable {
    private Socket socket;
    private String Name;
    public Client(String Name) {
        this.Name = Name;
    }

    @Override
    public void run() {
        System.out.println("Client socket started");
        /*try{Socket socket = new Socket("localhost", 6868);}
        catch (IOException e){ e.printStackTrace();}*/

            synchronized (this) {
                try {
                    Socket socket = new Socket("localhost", 6868);
                    while (true) {
                        InputStream input = socket.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
                        String cmd = bufferedReader.readLine();
                        System.out.println(cmd);
                        bufferedReader.mark(0);
                        bufferedReader.reset();
                        this.wait(10);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (IOException e){ e.printStackTrace();}

            }

        }
}
