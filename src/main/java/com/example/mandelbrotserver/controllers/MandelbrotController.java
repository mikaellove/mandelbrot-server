package com.example.mandelbrotserver.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@RestController
@RequestMapping()
public class MandelbrotController {


    @GetMapping("mandel")
    public ResponseEntity<String> getTest(){
        BufferedImage img = new BufferedImage(10000,10000,BufferedImage.TYPE_INT_RGB);


        // set up server sockets
        try{
            InetAddress ip = InetAddress.getLocalHost();
            ServerSocket serverSocket1 = new ServerSocket(8083);


            // Step 1: Open the socket connection.
            Socket s1 = new Socket(ip, 8083);
            DataInputStream dis = new DataInputStream(s1.getInputStream());
            DataOutputStream dos = new DataOutputStream(s1.getOutputStream());

            // accept connetion then calculate and send back data.
            Socket serverAccept1 = serverSocket1.accept();
            DataInputStream serverDis = new DataInputStream(serverAccept1.getInputStream());
            DataOutputStream serverDos = new DataOutputStream(serverAccept1.getOutputStream());
            serverDos.write(2);

            // recieve data from calculation
            System.out.println(dis.read());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("hj");
    }
}
