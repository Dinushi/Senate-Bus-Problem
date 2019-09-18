import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String userInput;
        BusStop BusStop = new BusStop();

        float riderArrivalMeanTime = 30f * 1000;
        float busArrivalMeanTime = 20 * 60f * 1000;

        //create a thread to generate riders within 30s mean time
        RiderGenerator riderGenerator = new RiderGenerator(riderArrivalMeanTime, BusStop);
        (new Thread(riderGenerator)).start();

        //create a thread to generate buses within 20min mean time
        BusGenerator busGenerator = new BusGenerator(busArrivalMeanTime,BusStop);
        (new Thread(busGenerator)).start();

        //Termination of program
        while(true){
            userInput = scanner.nextLine();
            if(userInput != null)
                System.exit(0);
        }
    }
}
