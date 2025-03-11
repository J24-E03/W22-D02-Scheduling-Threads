import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

public class Task3 {
    public static void run() {
        try (ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor()) {
            List<Runnable> list = Arrays.asList(() -> System.out.println("Task 1 finished"), () -> System.out.println("Task 2 finished"), () -> System.out.println("Task 3 finished"));
            for (Runnable runnable : list) {
                service.schedule(runnable, RandomGenerator.getDefault().nextLong(10), TimeUnit.SECONDS);
            }

            if (!service.awaitTermination(5, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
