//package Semaphores.boundedbuffer;

import java.util.concurrent.TimeUnit;

// This class creates the buffer and the producer and consumer threads.
public class Factory {
    // a timer used to track how long the program ran
    public static double timer = 0;

    public static void main(String args[]) throws InterruptedException {
        // create the buffer
        Buffer server = new BoundedBuffer();

        // create the producer and consumer threads
        Thread producerThread = new Thread(new Network(server, 8, 6));
        Thread consumerThread0 = new Thread(new Firewall(server));
//        Thread consumerThread1 = new Thread(new Firewall(server));
//        Thread consumerThread2 = new Thread(new Firewall(server));
//        Thread consumerThread3 = new Thread(new Firewall(server));


        // start the producer and consumer threads
        producerThread.start();
        consumerThread0.start();
//        consumerThread1.start();
//        consumerThread2.start();
//        consumerThread3.start();

        // current time
        timer = System.currentTimeMillis();

        // number of units program run
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Threads interrupted");
            Thread.currentThread().interrupt();
        }

        // terminate the producer and consumer threads
        producerThread.interrupt();
        consumerThread0.interrupt();
//        consumerThread1.interrupt();
//        consumerThread2.interrupt();
//        consumerThread3.interrupt();

        // wait until threads finish
        producerThread.join();
        consumerThread0.join();
//       consumerThread1.join();
//        consumerThread2.join();
//        consumerThread3.join();

        // total program run time
        timer = System.currentTimeMillis() - timer;

        // print out the statistics
        printStatistics.messages();
    }
}