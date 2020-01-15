package com.scoutzknifez.tictactoe.datastore;

import com.scoutzknifez.tictactoe.structures.LobbyPacket;
import com.scoutzknifez.tictactoe.structures.Player;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private static List<Player> playerList = new ArrayList<>();
    public static LobbyPacket makeLobbyPacket(Player player) {
        playerList.add(player);

        return new LobbyPacket(playerList.size());
    }
}
