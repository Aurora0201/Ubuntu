package com.simple.factory;

/**
 * Factory role
 * @author binjunkai
 */
public class WeaponFactory {
    public static Weapon makeWeapon(String weapon) {
        switch (weapon) {
            case "Tank" -> {
                return new Tank();
            }
            case "Plane" -> {
                return new Plane();
            }
            default -> {
                return null;
            }
        }
    }
}
