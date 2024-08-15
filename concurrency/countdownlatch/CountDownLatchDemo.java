package concurrency.countdownlatch;

import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 3; i++) {
            new CountDownLatchThread("CountDownLatch", countDownLatch);
        }

        boolean isCompleted = countDownLatch.await(4, TimeUnit.SECONDS);
        Thread.sleep(5000);
        System.out.println("All tasks are completed ? " + isCompleted);
    }
}


class CountDownLatchThread {
    String name;
    CountDownLatch countDownLatch;

    Runnable r1 = () -> {
        try {
            System.out.println(Thread.currentThread()
                .getName() + " > " + name + " task started. " + LocalTime.now());
            Thread.sleep(3000);
            System.out.println(Thread.currentThread()
                .getName() + " > " + name + " task completed. " + LocalTime.now());
//            System.out.println(countDownLatch.getCount());
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    };

    public CountDownLatchThread(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
        new Thread(r1).start();
    }
}