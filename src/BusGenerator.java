import java.util.Random;

public class BusGenerator implements Runnable{

    private float arrivalMeanTime;
    private BusStop busStop;
    private static Random random;
    private long busInterArrivalTime;

    public BusGenerator(float arrivalMeanTime, BusStop busStop) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.busStop = busStop;
        this.random = new Random();
        this.busInterArrivalTime = computeBusInterArrivalTime();
    }

    @Override
    public void run() {

        int busIndex = 1;
        while (!Thread.currentThread().isInterrupted()) {

            try {
                Bus bus = new Bus(busIndex, busStop);
                (new Thread(bus)).start();

                busIndex++;
                Thread.sleep(busInterArrivalTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All buses have arrived");
    }

    // compute the exponentially distributed inter arrival time
    public long computeBusInterArrivalTime() {
        return Math.round(-Math.log(1 - random.nextFloat()) / (1 / arrivalMeanTime));
    }
}
