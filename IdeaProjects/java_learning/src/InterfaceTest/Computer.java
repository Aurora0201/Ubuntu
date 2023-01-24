package InterfaceTest;

class ComputerTest{
    public static void main(String[] args) {
        Computer computer = new Computer(new Mouse());
        computer.setInsertDrawable(new Keyboard());
        computer.checkInsert();
    }
}

public class Computer {
    private InsertDrawable insertDrawable;

    public InsertDrawable getInsertDrawable() {
        return insertDrawable;
    }

    public void setInsertDrawable(InsertDrawable insertDrawable) {
        this.insertDrawable = insertDrawable;
    }

    public Computer(InsertDrawable insertDrawable) {
        this.insertDrawable = insertDrawable;
    }

    public void checkInsert(){
        insertDrawable.insert();
    }
}

interface InsertDrawable{
    void insert();
}

class Mouse implements InsertDrawable{
    @Override
    public void insert() {
        System.out.println("接入鼠标");
    }
}

class Keyboard implements InsertDrawable{
    @Override
    public void insert() {
        System.out.println("接入键盘");
    }
}

class Display implements InsertDrawable{
    @Override
    public void insert() {
        System.out.println("接入显示器");
    }
}
class printer implements InsertDrawable{
    @Override
    public void insert() {
        System.out.println("接入打印机");
    }
}