package com.scoutzknifez.tictactoe.gamelogic.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameState implements Serializable{
    private boolean isXTurn = true;
    private TTTBoard board = new TTTBoard();
}
