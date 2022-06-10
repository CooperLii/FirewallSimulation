//package Semaphores.boundedbuffer;

/**
 * Utilities for causing a thread to sleep.
 * Note, we should be handling interrupted exceptions
 * but choose not to do so for code clarity.
 *
 */

public class SleepUtilities
{
    /**
     * Nap between zero and duration seconds.
     */
    public static void nap(double duration) {
       // long sleeptime = (long)(duration*100);
        //long sleeptime = (long)(duration);
         long sleeptime =  (long)(duration * -Math.log(Math.random()));
        try { Thread.sleep(sleeptime); }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Threads interrupted. Exiting!");
        }
    }
}