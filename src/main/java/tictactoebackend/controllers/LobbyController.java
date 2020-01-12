package tictactoebackend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tictactoebackend.datastore.Lobby;
import tictactoebackend.structures.IPAddress;
import tictactoebackend.structures.LobbyPacket;
import tictactoebackend.utility.Constants;

@RestController
public class LobbyController {
    @PostMapping(Constants.API_HEADER + "joinLobby")
    public LobbyPacket joinLobby(/*@RequestBody IPAddress ipAddress*/) {
        // TODO Send IP as requestBody
        return Lobby.makeLobbyPacket();
    }
}
