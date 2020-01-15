package com.scoutzknifez.tictactoe.gamelogic;

import com.scoutzknifez.tictactoe.utility.Constants;
import com.scoutzknifez.tictactoe.utility.Utils;
import lombok.Getter;
import lombok.Setter;
import com.scoutzknifez.tictactoe.structures.Player;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameServer {
    private static int serverID = 0;

    private ServerSocket server;
    private List<ServerConnection> serverConnections = new ArrayList<>();
    private boolean isAlive = true;

    private Player xPlayer;
    private Player oPlayer;

    public GameServer(Player x, Player o) {
        serverID++;

        setXPlayer(x);
        setOPlayer(o);

        try {
            server = new ServerSocket(Constants.GAME_SERVER_PORT);
        } catch (Exception e) {
            Utils.log("Could not create the server at port 5050! %s", e);
        }

        startAccepting();
    }

    private void startAccepting() {
        Utils.log("Server is awaiting connections...");
        Socket socket = null;
        while (isAlive()) {
            try {
                socket = server.accept();
                Utils.log("Connected to " + socket.getRemoteSocketAddress() + "!");
            } catch (Exception e) {
                Utils.log("Game server crashed! %s", e);
                setAlive(false);
            }

            // Creates a separate thread to handle all the game logic for the one player's connection
            ServerConnection serverConnection = new ServerConnection(this, socket);
            getServerConnections().add(serverConnection);
            serverConnection.start();
        }

        closeServer();
    }

    private void closeServer() {
        try {
            getServer().close();
        } catch (Exception e) {
            Utils.log("Could not close the server! %s", e);
        }
    }
}
