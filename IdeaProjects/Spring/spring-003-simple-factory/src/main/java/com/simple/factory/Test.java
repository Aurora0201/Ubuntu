package com.simple.factory;

public class Test {
    public static void main(String[] args) {
        Weapon tank = WeaponFactory.makeWeapon("Tank");
        Weapon plane = WeaponFactory.makeWeapon("Plane");
        tank.attack();
        plane.attack();
    }
}
