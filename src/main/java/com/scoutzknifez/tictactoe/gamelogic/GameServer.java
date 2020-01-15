package com.scoutzknifez.tictactoe.gamelogic;

import com.scoutzknifez.tictactoe.gamelogic.dtos.Sample;
import com.scoutzknifez.tictactoe.utility.Utils;
import lombok.Getter;
import lombok.Setter;
import com.scoutzknifez.tictactoe.structures.Player;
import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Getter
@Setter
public class GameServer {
    private static int serverID = 0;

    private ServerSocket server;
    private boolean isAlive = true;


    private Player xPlayer;
    private Player oPlayer;

    @SneakyThrows
    public GameServer(Player x, Player o) {
        serverID++;

        setXPlayer(x);
        setOPlayer(o);

        server = new ServerSocket(5050);
        server.setSoTimeout(15000);

        startAccepting();
    }

    private void startAccepting() {
        while (isAlive()) {
            try {
                Utils.log("Server is waiting for connection...");

                Socket socket = server.accept();
                Utils.log("Connected to " + socket.getRemoteSocketAddress() + "!");

                InputStream input = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(input);
                Sample s = (Sample) ois.readObject();

                //DataInputStream input = new DataInputStream(socket.getInputStream());
                Utils.log("Input from client:\n " + s);

                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                output.writeUTF("Thanks for visiting!");

                socket.close();
            } catch (SocketTimeoutException ste) {
                Utils.log("No connections within past 15 seconds...");
            } catch (Exception e) {
                Utils.log("Game server crashed! %s", e);
                setAlive(false);
            }
        }
    }
}
