package com.lixiaoxuan.collider;

import com.lixiaoxuan.GameObject;

/**
 * 碰撞接口
 */
public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
