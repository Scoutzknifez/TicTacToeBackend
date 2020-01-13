package tictactoebackend.structures;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class IPAddress {
    private String ipAddress;
    private int port;
}
