package com.play001.gobang.support.entity;

import java.io.Serializable;



public enum ChessType {
    EMPTY((byte)0, "空"),
    BLACKNESS((byte)0, "黑棋"),
    WHITENESS((byte)0, "白棋");

    Byte value;
    String name;
    ChessType(Byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public Byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}

