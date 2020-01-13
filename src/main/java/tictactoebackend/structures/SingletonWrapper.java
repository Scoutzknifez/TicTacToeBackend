package tictactoebackend.structures;

import lombok.Data;

@Data
public class SingletonWrapper<T> {
    private T value;
}
