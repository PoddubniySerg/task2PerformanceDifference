import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapReaderWriter {
    private static final int MAP_SIZE = 10000000;
    private static final int CICLES_NUMBER = 1000;
    private final Map<Integer, Integer> synchronizedMap;
    private final ConcurrentMap<Integer, Integer> concurrentMap;

    public MapReaderWriter() {
        this.synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        this.concurrentMap = new ConcurrentHashMap<>();
        this.mapsFill();
    }

    private void mapsFill() {
        for (int i = 0; i < MAP_SIZE; i++) {
            this.synchronizedMap.put(i, i);
            this.concurrentMap.put(i, i);
        }
    }

    public void synchronizedMapWrite() {
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < CICLES_NUMBER; i++) {
            int newValue = random.nextInt(MAP_SIZE);
            this.synchronizedMap.put(newValue, newValue);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(Thread.currentThread().getName() + " выполнил запись в synchronizedMap за время: " + time);
    }

    public void synchronizedMapRead() {
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < CICLES_NUMBER; i++) {
            int newValue = random.nextInt(MAP_SIZE);
            this.synchronizedMap.get(newValue);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(Thread.currentThread().getName() + " выполнил чтение из synchronizedMap за время: " + time);
    }

    public void concurrentMapWrite() {
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < CICLES_NUMBER; i++) {
            int newValue = random.nextInt(MAP_SIZE);
            this.concurrentMap.put(newValue, newValue);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(Thread.currentThread().getName() + " выполнил запись в concurrentMap за время: " + time);
    }

    public void concurrentMapRead() {
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < CICLES_NUMBER; i++) {
            int newValue = random.nextInt(MAP_SIZE);
            this.concurrentMap.get(newValue);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(Thread.currentThread().getName() + " выполнил чтение из concurrentMap за время: " + time);
    }
}
