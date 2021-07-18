package com.lixiaoxuan.collider;

import com.lixiaoxuan.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 碰撞责任链
 */
public class ColliderChain implements Collider {

    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new TankTankCollider());
        add(new TankWallCollider());
        add(new BulletTankCollider());
        add(new BulletWallCollider());
    }

    private void add(Collider c) {
        colliders.add(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
