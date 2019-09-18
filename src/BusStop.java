import java.util.concurrent.Semaphore;

public class BusStop {

    private static int ridersCount;

    public BusStop () {
        this.ridersCount = 0 ;
    }

    private static final Semaphore riderBusStopEntranceSem = new Semaphore(50);
    private static final Semaphore allAboard = new Semaphore(0);
    private static final Semaphore busDepartureSem = new Semaphore(0);
    private static final Semaphore mutex = new Semaphore(1);

    public void incrementRidersCount() {
        ridersCount++;
    }

    public void decrementRidersCount() {
        ridersCount--;
    }

    public int getRidersCount() {
        return ridersCount;
    }

    public static Semaphore getRiderBusStopEntranceSem() {
        return riderBusStopEntranceSem;
    }

    public static Semaphore getAllAboard() {
        return allAboard;
    }

    public static Semaphore getBusDepartureSem() {
        return busDepartureSem;
    }

    public static Semaphore getMutex() {
        return mutex;
    }
}
