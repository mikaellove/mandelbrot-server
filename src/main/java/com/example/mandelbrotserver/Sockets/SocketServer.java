package com.example.mandelbrotserver.Sockets;

import com.example.mandelbrotserver.MandelbrotCalculator.MandelbrotCalculation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;

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
