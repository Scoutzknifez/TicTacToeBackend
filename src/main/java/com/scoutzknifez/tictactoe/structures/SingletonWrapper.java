package com.scoutzknifez.tictactoe.structures;

import lombok.Data;

@Data
public class SingletonWrapper<T> {
    private T value;
}
