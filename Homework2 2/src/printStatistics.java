public class printStatistics {
    public static void messages() {

        //Print Stats
        System.out.println(String.format("\n%-35s","Packets Dropped: ") + BoundedBuffer.droppedPackets + " out of " + BoundedBuffer.totalPackets);

        System.out.println(String.format("%-35s","Percentage: ") + String. format("%.5f",( BoundedBuffer.droppedPackets / BoundedBuffer.totalPackets ) * 100) + "%");

        // get the averages
        Packet.getAverageServiceTime();
        Packet.getAverageTurnaroundTime();
        Packet.getAverageWaitTime();

        System.out.println(String.format("%-35s","Service Time: ") + "Average: " + String. format("%.5f", Packet.averageServiceTime)
                + "ms" + "  Max: " + String. format("%.5f", Packet.maxServTime) + "ms");

        System.out.println(String.format("%-35s","Turnaround Time: ") + "Average: " + String. format("%.5f", Packet.averageTurnaroundTime)
                + "ms" + "  Max: "  + String. format("%.5f", Packet.maxTurnTime) + "ms");

        System.out.println(String.format("%-35s","Wait Time: ") + "Average: " + String. format("%.5f", Packet.averageWaitTime)
                + "ms" + String. format("%9s","  Max: ") + String. format("%.5f", Packet.maxWaitTime) + "ms");
        //System.out.println(String.format("%-40s","Average Wait Time Per Packet: ") + Packet.avgWaitTime + "ms");

        double utilization = ((Packet.serviceTimeSum / Factory.timer) * 100);
        System.out.println(String.format("%-35s","Firewall Utilization: ")
                + String. format("%5.5f", utilization) + "%");

        System.out.println(String.format("%-35s","Expected Firewall Utilization: ")
                + String. format("%5.5f",((Double.valueOf(Network.serviceTime)/Double.valueOf(Network.interarrivalTime))*100.0)) + "%");

        System.out.println(String.format("%-35s","Throughput: ")
                + String. format("%5.5f", (BoundedBuffer.totalPackets / (Factory.timer/1000))) + " Packets/Second");
    }
}
