import java.util.StringJoiner;
import java.util.concurrent.*;
import java.util.random.RandomGenerator;

public class Task4 {
    public static void run() {
        Callable<String> callable = () -> {
            int result = 0;
            StringJoiner joiner = new StringJoiner(" + ");
            for (int i = 0; i < 10; i++) {
                int generatedNumber = RandomGenerator.getDefault().nextInt(100);
                joiner.add(String.valueOf(generatedNumber));
                result += generatedNumber;
            }

            return joiner + " = " + result;
        };

        try (ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor()) {
            Future<String> future = service.submit(callable);
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
