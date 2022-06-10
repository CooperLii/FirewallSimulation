//package Semaphores.boundedbuffer;

/**
 * This is the producer thread for the bounded buffer problem.
 */

// Producer generates Packets every interarrival time
public class Network implements Runnable {
    public static int serviceTime;
    public static int interarrivalTime;
    public Buffer buffer;

    public Network(Buffer b, int interarrivalTime, int serviceTime) {
        buffer = b;
        Network.interarrivalTime = interarrivalTime;
        Network.serviceTime = serviceTime;
    }

    public void run() {
        Packet packet;

        while (!Thread.interrupted()) {
            // generate a packet
            packet = new Packet(serviceTime);
            // System.out.println("Producer P ");

            // insert packet into the buffer
            buffer.insert(packet);

            // sleep for interarrival time
            // System.out.println("Producer N");
            SleepUtilities.nap(interarrivalTime);
        }
    }
}