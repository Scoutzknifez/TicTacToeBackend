package com.scoutzknifez.tictactoe.structures;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Player {
    private IPAddress ipAddress;
    private String userName;
}
