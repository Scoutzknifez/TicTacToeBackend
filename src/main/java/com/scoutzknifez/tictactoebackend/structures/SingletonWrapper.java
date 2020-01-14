package com.scoutzknifez.tictactoebackend.structures;

import lombok.Data;

@Data
public class SingletonWrapper<T> {
    private T value;
}
