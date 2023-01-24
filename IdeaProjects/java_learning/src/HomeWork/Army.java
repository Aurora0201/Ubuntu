package HomeWork;

public class Army {
    private Weapon[] wps;
    private int size;
    private int idx;
    public Army(int size) {
        this.size = size;
        wps = new Weapon[size];
        for (int i = 0; i < size; i++) {
            wps[i] = new Weapon();
        }
    }

    public void addWeapon(Weapon weapon) throws WeaponException {
        if(idx < size){
            wps[idx++] = weapon;
        } else throw new WeaponException("武器库存已满无法添加武器");
    }

    public void attackAll(){
        for (int i = 0; i < idx; i++) {
            if(wps[i] instanceof Fireable){
                Fireable fireable = (Fireable) wps[i];
                fireable.openFire();
            }
        }
    }
    public void moveAll(){
        for (int i = 0; i < idx; i++) {
            if(wps[i] instanceof Moveable){
                Moveable moveable = (Moveable) wps[i];
                moveable.move();
            }
        }
    }

}

interface Moveable {
    void move();
}

interface Fireable {
    void openFire();
}

class Weapon {
}

class Tank extends Weapon implements Moveable, Fireable{
    @Override
    public void move() {
        System.out.println("Tank Move");
    }

    @Override
    public void openFire() {
        System.out.println("Tank OpenFire");
    }
}

class Cannon extends Weapon implements Fireable{
    @Override
    public void openFire() {
        System.out.println("Cannon OpenFire");
    }
}

class Fighter extends Weapon implements Moveable, Fireable{
    @Override
    public void move() {
        System.out.println("Fighter Move");
    }

    @Override
    public void openFire() {
        System.out.println("Fighter OpenFire");
    }
}

class WeaponException extends Exception{
    public WeaponException() {
        super();
    }

    public WeaponException(String message) {
        super(message);
    }
}

class Test {
    public static void main(String[] args) {
        Army army = new Army(10);
        try {
            army.addWeapon(new Tank());
            army.addWeapon(new Tank());
            army.addWeapon(new Fighter());
            army.addWeapon(new Cannon());
            army.attackAll();
            army.moveAll();
            army.addWeapon(new Cannon());
            army.addWeapon(new Cannon());
            army.addWeapon(new Cannon());
            army.addWeapon(new Cannon());
            army.addWeapon(new Cannon());
            army.addWeapon(new Cannon());
            army.addWeapon(new Cannon());
        } catch (WeaponException e) {
            System.out.println(e.getMessage());
        }
    }
}