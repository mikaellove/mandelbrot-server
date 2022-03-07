package com.example.mandelbrotserver.controllers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketServer {

    private final int port;
    private ServerSocket serverSocket;
    private boolean calculationFinished = false;

    public SocketServer(int port) {
        this.port = port;
    }

    public void setUpSocket()
    {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Thread acceptSocketConnection(int rowIndex, BufferedImage image,int max){
            Thread thread = new MandelbrotCalculation(serverSocket,rowIndex,image,max);
            thread.start();
            return thread;
    }
}
