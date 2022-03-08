package com.example.mandelbrotserver.services;

import com.example.mandelbrotserver.Sockets.SocketServer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MandelbrotService {

    public byte[] generateImage(int width, int height, int max, List<Integer> ports){
        /**
         * Creating image from variables passed in from client.
         * Instantiating list for caching the started threads.
         */
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        List<Thread> activeThreads = new ArrayList<>();

        try
        {
            int partToRender = height / ports.size();
            for(int port :ports){
                height = height - partToRender;

                /**
                 * Creating instances of the server-sockets that will render the image.
                 */
                SocketServer socketServer = new SocketServer(port);
                socketServer.setUpSocket();

                /**
                 * Waiting for client socket to connect then accepts that connection.
                 * Returns a thread on which the calculation are performed, stores that in List of threads.
                 */
                Thread thread = socketServer.acceptSocketConnection(height,img,max);
                activeThreads.add(thread);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


        /**
         * Waiting for each thread to complete the calculations.
         */
        activeThreads.forEach( thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /**
         * Write the buffered image to a ByteArrayOutputStream.
         */
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            System.out.println("Write Image");
            ImageIO.write(img , "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * Converts the output stream into a byte array and returns it.
         */
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        return imageInByte;
    }
}
