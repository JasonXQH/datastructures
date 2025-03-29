package deadlock;

/**
 * @author: XuQihang
 * @date: 2025/3/12 18:56
 * @description:
 */
public class DeadLockDemo {
    final static Object lock1 = new Object();
    final static Object lock2 = new Object();

    public Thread getT1() {
        return t1;
    }

    public Thread getT2() {
        return t2;
    }

    Thread t1 = new Thread(() -> {
        synchronized (lock1) {
            System.out.println("线程1，已获得lock1");
            try{
                Thread.sleep(100);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程1：等待获得 lock2");
            synchronized (lock2) {
                System.out.println("线程1：已经获得lock2");
            }
        }
    });

    Thread t2 = new Thread(() -> {
        synchronized (lock2) {
            System.out.println("线程2，已获得lock2");
            try{
                Thread.sleep(100);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程2：等待获得 lock1");
            synchronized (lock1) {
                System.out.println("线程1：已经获得lock2");
            }
        }
    });
}
