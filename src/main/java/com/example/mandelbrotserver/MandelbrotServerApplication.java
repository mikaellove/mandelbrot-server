package com.example.mandelbrotserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MandelbrotServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(MandelbrotServerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            SocketServer server = new SocketServer(8081);
            SocketClient client = new SocketClient("localhost",8081);

            server.socketserverstuff();
            client.socketstufffss();

            server.sendMessage();

            System.out.println("hej");
        };
    }

}
