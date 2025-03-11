import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task1 {
    public static void run() {
        try (ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor()) {
            service.scheduleAtFixedRate(() -> System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))), 1, 1, TimeUnit.SECONDS);
            if (!service.awaitTermination(5, TimeUnit.SECONDS)) {
                service.shutdown();
            }
            service.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
