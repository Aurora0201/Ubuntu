package top.pi1grim.util;

public class MyTimer {
    private MyTimer() {

    }

    /**
     * make program wait for a moment
     * @param time second
     */
    public static void sleep(long time) {
        long begin = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - begin > time * 1000) {
                break;
            }
        }
    }
}
