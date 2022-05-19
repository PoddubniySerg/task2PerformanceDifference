public class Main {
    public static final int THREADS_NUMBER = 8;

    public static void main(String[] args) {
        MapReaderWriter mapReaderWriter = new MapReaderWriter();
        System.out.println("Работа с synchronizedMap:");
        mapReadWrite(mapReaderWriter::synchronizedMapWrite, mapReaderWriter::synchronizedMapRead);
        System.out.println("\n\nРабота с ConcurrentMap:");
        mapReadWrite(mapReaderWriter::concurrentMapWrite, mapReaderWriter::concurrentMapRead);
    }

    public static void mapReadWrite(Runnable write, Runnable read) {
        Thread[] threads = new Thread[THREADS_NUMBER];
        for (int i = 0; i < threads.length; i += 2) {
            threads[i] = new Thread(write);
            threads[i + 1] = new Thread(read);
            threads[i].start();
            threads[i + 1].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
