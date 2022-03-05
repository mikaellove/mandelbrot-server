package com.example.mandelbrotserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class MandelbrotServerApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(MandelbrotServerApplication.class, args);
    }
}
