package Vehicle;

public class Vehicle {
    private double speed;
    private double size;

    public Vehicle(double size) {
        this(0, size);
    }

    public Vehicle(double speed, double size) {
        this.speed = speed;
        this.size = size;
        System.out.println("速度：" + speed + " 体积：" + size);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSpeed() {
        return speed;
    }

    public double getSize() {
        return size;
    }
    public void speedUp(double speed){
        if(speed == 0)this.speed++;
        else this.speed += speed;
        System.out.println("加速 当前速度：" + this.speed);
    }
    public void speedDown(double speed){
        if(this.speed - speed> 0) {
            this.speed -= speed;
            System.out.println("减速 当前速度" + this.speed);
        } else {
           this.speed = 0;
           System.out.println("已经停止");
        }
    }
}

class Test{
    public static void main(String[] args) {
//        Vehicle v = new Vehicle(100, 25);
//        v.speedDown(20);
//        v.speedUp(20);
        System.out.println();

    }
}