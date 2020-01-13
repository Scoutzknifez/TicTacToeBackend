package tictactoebackend.datastore;

import tictactoebackend.structures.LobbyPacket;
import tictactoebackend.structures.Player;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private static List<Player> playerList = new ArrayList<>();
    public static LobbyPacket makeLobbyPacket(Player player) {
        playerList.add(player);

        return new LobbyPacket(playerList.size());
    }
}
