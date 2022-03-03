package com.example.mandelbrotserver;

import java.io.*;
import java.net.*;
public class ClientThreadHandler implements Runnable {

    private final Socket clientSocket;

    public ClientThreadHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

        PrintWriter out = null;
        BufferedReader reader = null;
    public void run(){

        try {
            // get the outputstream of client
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;

                System.out.printf(" Sent from the client: %s\n", reader.readLine());
                out.println("heeeeelloooo from server");

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                    clientSocket.close();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sendMessage() throws IOException {
        out.println("heeeej");
        String hej = reader.readLine();
    }
}
