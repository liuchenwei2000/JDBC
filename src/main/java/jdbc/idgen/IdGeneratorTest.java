package jdbc.idgen;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2017/8/1.
 */
public class IdGeneratorTest {

    static Set<Integer> ids = new CopyOnWriteArraySet<>();

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Task());
        }

        TimeUnit.SECONDS.sleep(10);
        System.out.println(ids.size());

        threadPool.shutdown();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                int id = IdGenerator.generate();
                System.out.println(id);
                ids.add(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
