import java.sql.SQLOutput;
import java.util.ArrayList;

public class Packet {
    // an array list to keep track of service time for packets
    public static ArrayList<Double> serviceTimeList = new ArrayList<>();
    // an array list to keep track of turnaround time for packets
    public static ArrayList<Double> turnaroundTimeList = new ArrayList<>();
    // an array list to keep track of wait time for packets
    public static ArrayList<Double> waitTimeList = new ArrayList<>();
    // maximum service time
    public static double maxServTime = 0;
    // maximum turnaround time
    public static double maxTurnTime = 0;
    // maximum wait time
    public static double maxWaitTime = 0;
    // average service time
    public static double averageServiceTime = 0;
    // average turnaround time
    public static double averageTurnaroundTime = 0;
    // average wait time
    public static double averageWaitTime = 0;
    // the sum of service time
    public static double serviceTimeSum = 0;
    // given service time
    public  double serviceTime = 0;
    // the amount of time packets being  processed
    public  double firewallServiceTime = 0;
    // initial firewall turn around time
    public  double turnaroundTime = 0;
    // initial firewall start time
    public  double createTime = 0;
    // initial firewall wait time
    public static double waitTime = 0;
    // a string used to print when the packets are produced
    String start;

    // constructor for class Packet
    public Packet (double time) {
        serviceTime = time;
        // track start time
        createTime();
        // start = String.format("%1$TH:%1$TM:%1$TS", System.currentTimeMillis());
    }

    // this method is used to help find the service time
    public  void serviceTimeHelper() { firewallServiceTime = System.currentTimeMillis(); }
    // this program tracks the service time of packets
    public  void getServiceTime() { firewallServiceTime = System.currentTimeMillis() - firewallServiceTime;}
    // this method tracks the start time of the program
    public  void createTime() { createTime = System.currentTimeMillis(); }
    // this method tracks turnaround time
    // by doing (current time - start time)
    public  void turnTime() { turnaroundTime = System.currentTimeMillis() - createTime; }
    // this method tracks the wait time of the program
    // by doing (turnaround time - service time)
    public  void waitTime() { waitTime = turnaroundTime - firewallServiceTime; }

    // keep track of firewall wait time, turnaround time, and service time
    private  void trackStatistics() {
        waitTimeList.add(waitTime);
        turnaroundTimeList.add(turnaroundTime);
        serviceTimeList.add(firewallServiceTime);
        serviceTimeSum += firewallServiceTime;
    }

    // get the max firewall service time
    private  void getMaxServiceTime() {
        if(firewallServiceTime > maxServTime) { maxServTime = firewallServiceTime; }
    }
    // get the max firewall wait time
    private  void getMaxWaitTime() {
        if(waitTime > maxWaitTime) { maxWaitTime = waitTime; }
    }
    // get the max firewall turnaround time
    private  void getMaxTurnaroundTime() {
        if(turnaroundTime > maxTurnTime) { maxTurnTime = turnaroundTime; }
    }

    // average turnaround time
    public static void getAverageTurnaroundTime() {
        for (double time : turnaroundTimeList) {
            averageTurnaroundTime += time;
        }
        if (turnaroundTimeList.size() >= 1) {
            averageTurnaroundTime = averageTurnaroundTime / turnaroundTimeList.size();
        } else {
            System.out.println("Nothing in the list!");
        }
    }

    // average wait time
    public static void getAverageWaitTime() {
        for (double time : waitTimeList) {
            averageWaitTime += time;
        }
        if (waitTimeList.size() >= 1) {
            averageWaitTime = averageWaitTime / waitTimeList.size();
        } else {
            System.out.println("Nothing in the list!");
        }
    }

    // average service time
    public static void getAverageServiceTime() {
        for(double time: serviceTimeList) { averageServiceTime += time; }
        if (serviceTimeList.size() >= 1) {
            averageServiceTime = averageServiceTime / serviceTimeList.size();
        } else {
            System.out.println("Nothing in the list!");
        }
    }

    // overide tostring method to print out message for each packet and get times
    @Override
    public String toString() {
        getServiceTime();
        if(turnaroundTime == 0) {
        turnTime();
        waitTime();
        }
        trackStatistics();
        getMaxServiceTime();
        getMaxTurnaroundTime();
        getMaxWaitTime();
//        System.out.println("turn" + turnaroundTime );
//        System.out.println("firewall service time " + firewallServiceTime);
//        Double cur = Double.longBitsToDouble(System.currentTimeMillis());
//        System.out.println("current time" + cur + " start:" + startTime);
//        double mi= startTime-cur;
//        System.out.println(cur + " - " + startTime + " = " + mi);
//        System.out.println("Current time: " + System.currentTimeMillis() + " Start time: " + createTime);
//        return ("Turnaround time: " + turnaroundTime + "ms" + " Wait time: " + waitTime + "ms");
        return ".";
    }
}
