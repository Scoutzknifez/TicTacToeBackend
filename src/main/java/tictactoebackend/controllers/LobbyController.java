package tictactoebackend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tictactoebackend.datastore.Lobby;
import tictactoebackend.structures.IPAddress;
import tictactoebackend.structures.LobbyPacket;
import tictactoebackend.structures.Player;
import tictactoebackend.utility.Constants;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LobbyController {
    @PostMapping(Constants.API_HEADER + "joinLobby")
    public LobbyPacket joinLobby(@RequestBody String userName, HttpServletRequest request) {
        return Lobby.makeLobbyPacket(new Player(new IPAddress(request.getRemoteAddr(), request.getRemotePort()), userName));
    }
}
