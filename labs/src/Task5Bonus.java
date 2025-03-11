import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Task5Bonus {
    public static void run() {
        AtomicInteger delay = new AtomicInteger(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");

        while (delay.get() != 0) {
            System.out.println("Countdown " + delay.get() + " seconds!");
            try (ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor()) {
                LocalTime time = LocalTime.now();
                System.out.println("Current time: " + time.format(formatter));
                System.out.println("The function must stop at " + time.plusSeconds(delay.get()).format(formatter) + "\n");
                executor.schedule(() -> {
                    System.out.println("STOP ---> " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")) + " <--- STOP\n");
                    delay.addAndGet(-1);
                }, delay.get(), TimeUnit.SECONDS);
            }
        }

        // Task for Omar (the timing is not quite precise)
//        AtomicInteger delay = new AtomicInteger(5000);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSSS");
//
//        while (delay.get() != 0) {
//            System.out.println("Countdown " + delay.get() + " milliseconds!");
//            try (ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor()) {
//                LocalTime currentTime = LocalTime.now();
//                System.out.println("Current time: " + currentTime.format(formatter));
//                System.out.println("The function must stop at " + currentTime.plus(delay.get(), ChronoUnit.MILLIS).format(formatter) + "\n");
//                executor.schedule(() -> {
//                    System.out.println("STOP ---> " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss.SSSS")) + " <--- STOP\n");
//                    delay.addAndGet(-500);
//                }, delay.get(), TimeUnit.MILLISECONDS);
//            }
//        }
    }
}
