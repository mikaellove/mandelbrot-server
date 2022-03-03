package com.example.mandelbrotserver;

import java.io.*;
import java.net.*;
public class SocketServer {
    private final int port;
    ServerSocket server = null;

    ClientThreadHandler clientThread = null;
    public SocketServer(int port) {
        this.port = port;
    }

    public void socketserverstuff(){
        try{
            server = new ServerSocket(port);
            server.setReuseAddress(true);



                // incoming client requests
                Socket client = server.accept();

                // display connected client
                System.out.println("Client connected " + client.getInetAddress().getHostAddress());

                clientThread = new ClientThreadHandler(client);

                new Thread(clientThread).start();



        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void sendMessage() throws IOException {
        clientThread.sendMessage();
    }
}
