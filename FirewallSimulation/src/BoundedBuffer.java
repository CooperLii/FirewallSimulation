//package Semaphores.boundedbuffer;

import java.util.concurrent.Semaphore;

/**
 * BoundedBuffer.java
 *
 * This program implements the bounded buffer with semaphores.
 * Note that the use of count only serves to output whether
 * the buffer is empty or full.
 */
public class BoundedBuffer implements Buffer {
    // initialize the buffer size
    private static final int BUFFER_SIZE = 5;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;
    private int count, in, out;
    private Object[] buffer;

    // Number of total packets and dropped packets
    public static double totalPackets, droppedPackets;

    // buffer is initially empty
    public BoundedBuffer() {
        count = 0;
        in = 0;
        out = 0;

        buffer = new Object[BUFFER_SIZE];
        mutex = new Semaphore(1);
        empty = new Semaphore(BUFFER_SIZE);
        full = new Semaphore(0);
    }

    // producer calls this method
    public void insert(Object item) {
        // set up a critical section for comparing the queue size
        try{
            mutex.acquire();
        }
        catch (Exception e){
            System.out.println("Exception caught:" + e);
        };

        // keep track of the number of total packets
        totalPackets++;

        // keep track of the number of dropped packets
        // if the buffer is full then drop the packets incoming
        // if the wait time is more than 6000ms drop the packets
        if(count == BUFFER_SIZE) {
            System.out.println("Packet dropped -> queue is full");
            droppedPackets++;
            mutex.release();
            return;}

//        } else if (Packet.waitTime > 6000.0) {
//            System.out.println("Packet dropped -> wait time is too long!");
//            droppedPackets++;
//            mutex.release();
//            return;
//        }

            mutex.release();

        try {
            empty.acquire();
            mutex.acquire();
        } catch (Exception e) {
            // handle the interrupt
            Thread.currentThread().interrupt();
            System.out.println("Threads interrupted in Insert: " + e);
        }

        // add an item to the buffer
        ++count;
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;

//         if (count == BUFFER_SIZE) {
//            System.out.println("Producer Entered " + " Buffer FULL");
//             System.out.println("Buffer FULL");
//         } else {
//            System.out.println("Producer Entered " + " Buffer Size = " + count);
//         }

        mutex.release();
        full.release();
    }


    // consumer calls this method
    public Object remove() {
        try {
            full.acquire();
            mutex.acquire();
        } catch (Exception e) {
            //handle the interrupt
            System.out.println("Threads interrupted in Remove: " + e);
            Thread.currentThread().interrupt();
        }

        // remove an item from the buffer
        --count;
        Object item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;

//       if (count == 0) {
//           System.out.println("Firewall received: Buffer EMPTY");
//           System.out.println("Buffer EMPTY");
//        } else {
//            System.out.println("Firewall received Buffer Size = " + count);
//        }

        mutex.release();
        empty.release();

        return item;
    }
}
