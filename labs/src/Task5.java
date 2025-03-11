import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task5 {
    private static int counter;

    public static void run() {
        try (ExecutorService service = Executors.newFixedThreadPool(5)) {
            for (int i = 0; i < 5; i++) {
                service.submit(Task5::updateCounter);
            }
        }
    }

    private static void updateCounter() {
        synchronized (Main.class) {
            for (int i = 0; i < 1000; i++) {
                counter++;
            }
            System.out.println(Thread.currentThread().getName() + " increased the counter to " + counter);
        }
    }
}
