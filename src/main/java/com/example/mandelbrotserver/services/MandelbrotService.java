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
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        List<Thread> activeThreads = new ArrayList<>();

        try
        {
            int split = height / ports.size();
            for(int port :ports){
                height = height - split;

                SocketServer socketServer = new SocketServer(port);
                socketServer.setUpSocket();

                Thread thread = socketServer.acceptSocketConnection(height,img,max);

                activeThreads.add(thread);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


        activeThreads.forEach( thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            System.out.println("Write Image");
            ImageIO.write(img , "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        return imageInByte;
    }
}
