package com.simple.factory;

/**
 * specific role
 * @author binjunkai
 */
public class Plane extends Weapon{
    @Override
    void attack() {
        System.out.println("Plane attack!");
    }
}
