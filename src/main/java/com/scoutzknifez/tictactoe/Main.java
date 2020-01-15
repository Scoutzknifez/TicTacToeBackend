package com.scoutzknifez.tictactoe;

import com.scoutzknifez.tictactoe.gamelogic.GameServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("<==========[Starting API Server]==========>");

        SpringApplication.run(Main.class, args);

        GameServer gameServer = new GameServer(null, null);
    }
}
