package com.lixiaoxuan.strategy;

import com.lixiaoxuan.model.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank t);
}
