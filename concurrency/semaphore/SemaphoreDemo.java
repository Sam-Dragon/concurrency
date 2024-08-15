package concurrency.semaphore;

import java.time.LocalTime;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
//        Semaphore semaphore = new Semaphore(2, true);
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
//            System.out.println("Available Permits :: " + semaphore.availablePermits());
            new IncrementCount("Increment", semaphore);
        }
    }
}


class IncrementCount {
    String name;
    Semaphore semaphore;

    Runnable r1 = () -> {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread()
                .getName() + " > " + name + " got the permit." + LocalTime.now());

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Release the permit
        System.out.println(Thread.currentThread()
            .getName() + " > " + name + " release the permit" + LocalTime.now());
        semaphore.release();
//        System.out.println("Release Available Permits :: " + semaphore.availablePermits());
    };

    public IncrementCount(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
        new Thread(r1).start();
    }
}