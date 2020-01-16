package com.scoutzknifez.tictactoe.gamelogic;

import com.scoutzknifez.tictactoe.gamelogic.dtos.GameState;
import com.scoutzknifez.tictactoe.gamelogic.dtos.Pieces;
import com.scoutzknifez.tictactoe.structures.Player;
import com.scoutzknifez.tictactoe.utility.Constants;
import com.scoutzknifez.tictactoe.utility.Globals;
import com.scoutzknifez.tictactoe.utility.Utils;
import lombok.Getter;
import lombok.Setter;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GameServer {
    private static int serverID = 0;

    private ServerSocket server;
    private Map<Pieces, ServerConnection> serverConnections = new HashMap<>();
    private boolean isAlive = true;
    private boolean isMoving = true;
    private GameState gameState = new GameState();

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
        while (isAlive() && serverConnections.size() < 2) {
            try {
                socket = server.accept();
                Utils.log("Connected to " + socket.getRemoteSocketAddress() + "!");
            } catch (Exception e) {
                Utils.log("Game server crashed! %s", e);
                setAlive(false);
            }

            boolean isX = serverConnections.size() == 0 ? Globals.random.nextBoolean() : !getServerConnections().containsKey(Pieces.CROSS);

            // Creates a separate thread to handle all the game logic for the one player's connection
            ServerConnection serverConnection = new ServerConnection(this, socket, isX);

            getServerConnections().put(isX ? Pieces.CROSS : Pieces.CIRCLE, serverConnection);
            serverConnection.start();
        }
        startGame();
    }

    private void startGame(){
        getServerConnections().values().forEach(serverConnection -> serverConnection.sendOutput(gameState));

        while (isAlive()){
            while (isMoving){}

            if (getGameState().isXTurn())
                getServerConnections().get(Pieces.CROSS).sendOutput(gameState);
            else
                getServerConnections().get(Pieces.CIRCLE).sendOutput(gameState);

            if(getGameState().getBoard().gameIsOver()){
                closeServer();
            }
            setMoving(true);
        }
    }

    private void closeServer() {
        try {
            setAlive(false);
            getServer().close();
        } catch (Exception e) {
            Utils.log("Could not close the server! %s", e);
        }
    }

    public boolean checkInput(GameState gs, boolean x){
        // Checks if the player who just went, was the player we are waiting for
        if(x != gs.isXTurn())
            return false;

        int changedCounter = 0;
        for(int i = 0; i < Constants.BOARD_SIZE; i++) {
            // Checks each instance of the board where the slot has changed
            if(!gs.getBoard().getSlots()[i].isEqualTo(gameState.getBoard().getSlots()[i])) {
                // Checks to make sure that any changes that occurred was the correct player
                if(!gs.getBoard().getSlots()[i].isEqualTo(x ? Pieces.CROSS: Pieces.CIRCLE)) {
                    return false;
                }

                changedCounter++;
            }
        }

        // If the board change is more than 1 differential, the board is invalid
        if(changedCounter != 1) {
            return false;
        }

        return true;
    }
}
