package com.scoutzknifez.tictactoe.controllers;

import com.scoutzknifez.tictactoe.structures.SingletonWrapper;
import com.scoutzknifez.tictactoe.utility.Constants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.scoutzknifez.tictactoe.datastore.Lobby;
import com.scoutzknifez.tictactoe.structures.IPAddress;
import com.scoutzknifez.tictactoe.structures.LobbyPacket;
import com.scoutzknifez.tictactoe.structures.Player;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LobbyController {
    @PostMapping(Constants.API_HEADER + "joinLobby")
    public LobbyPacket joinLobby(@RequestBody SingletonWrapper<String> value, HttpServletRequest request) {
        return Lobby.makeLobbyPacket(new Player(new IPAddress(request.getRemoteAddr(), request.getRemotePort()), value.getValue()));
    }
}
