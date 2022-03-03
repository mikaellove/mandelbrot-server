package com.example.mandelbrotserver;

import java.io.*;
import java.net.*;
import java.util.*;
public class SocketClient {

    private final String proxy;
    private final int port;

    public SocketClient(String proxy, int port) {
        this.proxy = proxy;
        this.port = port;
    }

    public void socketstufffss(){
        try(Socket socket = new Socket(proxy,port)){
            // Reads from server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            System.out.println(reader.readLine());

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
