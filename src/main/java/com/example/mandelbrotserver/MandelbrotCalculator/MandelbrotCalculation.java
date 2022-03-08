package com.example.mandelbrotserver.MandelbrotCalculator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MandelbrotCalculation extends Thread{
    private final int max;
    int red = new Color(255,0,0).getRGB();
    int blue = new Color(0,0,255).getRGB();
    private ServerSocket serverSocket;
    private Socket socket;
    private final BufferedImage image;
    private final int rowIndex;

    public MandelbrotCalculation(ServerSocket serverSocket ,int rowIndex, BufferedImage image,int max){
        this.rowIndex = rowIndex;
        this.image = image;
        this.max = max;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run()
    {
        PrintWriter out;
        /**
         * Waiting for client socket to connect.
         */
        System.out.println("start thread");
        try
        {
            socket = serverSocket.accept();
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        /**
         * Mandelbrot calculation
         */
        for (int col = 0; col < image.getHeight(); col++) {
            for (int row = rowIndex; row < image.getWidth(); row++) {
                double c_re = (col - image.getWidth() / 2.0) * 4.0 / image.getWidth();
                double c_im = (row - image.getHeight() / 2.0) * 4.0 / image.getWidth();
                double x = 0, y = 0;
                int iteration = 0;
                while (x * x + y * y <= 4 && iteration < max) {
                    double x_new = x * x - y * y + c_re;
                    y = 2 * x * y + c_im;
                    x = x_new;
                    iteration++;
                }

                /**
                 * Sets a pixel on the buffered image with the given color.
                 *
                 * I would like to have time to implement the commented out parts, write the pixel to the output stream and let the client socket receive it.
                 * But now I set the pixel of the image and send the image in bytes in the response body.
                 */
                if (iteration < max) {
                    image.setRGB(row,col,red);
                    //out.write(image.getRGB(row,col));
                } else {
                    image.setRGB(row,col,blue);
                    //out.write(image.getRGB(row,col));
                }

            }
        }

        /**
         * When the calculation is finished close socket on server and client side.
         */
        try {
            serverSocket.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
