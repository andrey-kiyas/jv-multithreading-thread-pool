package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Future<String>> futures = new ArrayList<>();
        // write your code here
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            Future<String> future = executorService.submit(new MyThread());
            futures.add(future);
        }

        for (Future<String> future : futures) {
            try {
                logger.info("Task result: " + future.get());
            } catch (Exception e) {
                logger.error("Error getting task result", e);
            }
        }
        executorService.shutdown();
    }
}
