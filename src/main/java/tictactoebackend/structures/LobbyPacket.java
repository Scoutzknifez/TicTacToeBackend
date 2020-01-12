package tictactoebackend.structures;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LobbyPacket {
    private int peopleInLobby;
}
