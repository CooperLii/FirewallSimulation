//package Semaphores.boundedbuffer;

/**
 * This is the consumer thread for the bounded buffer problem.
 */

public class Firewall implements Runnable {

    public Firewall(Buffer b) {
        buffer = b;
    }

    public void run() {
        Packet packet;

        while (!Thread.interrupted()) {
            // consume an item from the buffer
            // System.out.println("Consumer C");
            packet = (Packet) buffer.remove();

            //used to help calculate service time
            packet.serviceTimeHelper();

            // nap during service time
            // System.out.println("Consumer N");
            SleepUtilities.nap((int)packet.serviceTime);

            System.out.println(packet);
        }
    }
    private Buffer buffer;
}

