package com.simple.factory;

/**
 * specific role
 * @author binjunkai
 */
public class Tank extends Weapon{
    @Override
    void attack() {
        System.out.println("Tank attack!");
    }
}
