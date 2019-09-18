import java.util.Random;

public class RiderGenerator implements Runnable {

    private float arrivalMeanTime;
    private BusStop busStop;
    private static Random random;
    private long riderInterArrivalTime;

    public RiderGenerator(float arrivalMeanTime, BusStop BusStop) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.busStop = BusStop;
        this.random = new Random();
        this.riderInterArrivalTime = computeRiderInterArrivalTime();
    }

    @Override
    public void run() {

        int riderIndex = 1;
        while (!Thread.currentThread().isInterrupted()) {

            try {
                Rider rider = new Rider(riderIndex, busStop);
                (new Thread(rider)).start();

                riderIndex++;
                Thread.sleep(riderInterArrivalTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // compute exponentially distributed inter arrival time
    public long computeRiderInterArrivalTime() {
        return Math.round(-Math.log(1 - random.nextFloat()) / (1 / arrivalMeanTime));
    }

}
