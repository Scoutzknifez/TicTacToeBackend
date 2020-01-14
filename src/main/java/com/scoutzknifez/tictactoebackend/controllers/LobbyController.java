package com.scoutzknifez.tictactoebackend.controllers;

import com.scoutzknifez.tictactoebackend.structures.SingletonWrapper;
import com.scoutzknifez.tictactoebackend.utility.Constants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.scoutzknifez.tictactoebackend.datastore.Lobby;
import com.scoutzknifez.tictactoebackend.structures.IPAddress;
import com.scoutzknifez.tictactoebackend.structures.LobbyPacket;
import com.scoutzknifez.tictactoebackend.structures.Player;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LobbyController {
    @PostMapping(Constants.API_HEADER + "joinLobby")
    public LobbyPacket joinLobby(@RequestBody SingletonWrapper<String> value, HttpServletRequest request) {
        return Lobby.makeLobbyPacket(new Player(new IPAddress(request.getRemoteAddr(), request.getRemotePort()), value.getValue()));
    }
}
