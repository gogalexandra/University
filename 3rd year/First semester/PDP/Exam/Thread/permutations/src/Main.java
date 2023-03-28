import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int nrThreads = 2;
    public static final int N = 4;
    public static final List<List<Integer>> solution = new ArrayList<>();

    public static CheckTask initCheckTask(int index) {
        int count = N / nrThreads - 1;

        int start = index * count;
        int end = start + count;

        if (index == nrThreads - 1)
            // ca totalul sa fie 4 incepand de la 0
            end = N - 1;

        System.out.println("count = " + count);
        return new CheckTask(start, end, N, solution);
    }

    public static void main(String[] args) throws Exception {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < nrThreads; ++i) {
            threads.add(initCheckTask(i));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("sum fuck u = " + solution);
    }
}
