import java.util.concurrent.Semaphore;

class Rider implements Runnable {

    private final Semaphore riderBusStopEntranceSem;
    private final Semaphore allAboard;
    private final Semaphore busDepartureSem;
    private final Semaphore mutex;
    private final int index;
    private BusStop busStop;

    public Rider(int index, BusStop busStop) {
        this.busStop = busStop;
        this.index = index;
        this.riderBusStopEntranceSem = busStop.getRiderBusStopEntranceSem();
        this.allAboard = busStop.getAllAboard();
        this.busDepartureSem = busStop.getBusDepartureSem();
        this.mutex = busStop.getMutex();
    }

    @Override
    public void run() {

        try {
            riderBusStopEntranceSem.acquire();

            mutex.acquire();
            enterBoardingArea();
            busStop.incrementRidersCount();
            mutex.release();


            allAboard.acquire();
            boardBus();

            riderBusStopEntranceSem.release();
            busStop.decrementRidersCount();


            if (busStop.getRidersCount() == 0)
                busDepartureSem.release();

            else
                allAboard.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void boardBus() {
        System.out.println( "Rider " + index + "  -> boarded");
    }

    public void enterBoardingArea() {
        System.out.println("Rider " + index + "  -> entered boarding area");
    }
}