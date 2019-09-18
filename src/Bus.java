import java.util.concurrent.Semaphore;

public class Bus implements Runnable {

    private final Semaphore allAboard;
    private final Semaphore busDepartureSem;
    private final Semaphore mutex;
    private final int busIndex;
    private BusStop busStop;


    public Bus(int BusIndex, BusStop busStop) {
        this.busStop = busStop;
        this.allAboard = busStop.getAllAboard();
        this.busDepartureSem = busStop.getBusDepartureSem();
        this.mutex = busStop.getMutex();
        this.busIndex = BusIndex;
    }

    @Override
    public void run() {

        try {
            mutex.acquire();
            arrived();

            if (busStop.getRidersCount() > 0) {

                allAboard.release();
                busDepartureSem.acquire();
            }
            mutex.release();

            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void arrived() {
        System.out.println( "Bus " + busIndex +"  -> arrived");
        System.out.println( busStop.getRidersCount()+" riders are waiting ");
    }

    public void depart() {
        System.out.println( "Bus " + busIndex + "  -> departed");
    }


}