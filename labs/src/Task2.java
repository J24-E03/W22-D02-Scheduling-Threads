import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task2 {
    public static void run() {
        try (ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor()) {
            service.scheduleAtFixedRate(() -> System.out.println("Probing service at " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))), 2, 3, TimeUnit.SECONDS);
            if (!service.awaitTermination(20, TimeUnit.SECONDS)) {
                service.shutdown();
            }
            service.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
