import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int N = 100;
    private static final int nrThreads = 3;
    public static final List<Integer> primesToSqrt = getPrimesUpToSqrt(N);

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> getPrimesUpToSqrt(int N) {
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static CheckTask initCheckTask(int index, int nrThreads, List<Integer> primesToSqrt) {
        int start = index == 0 ? (int) Math.sqrt(N) : index * (N - primesToSqrt.get(primesToSqrt.size() - 1) + 1) / nrThreads;
        int end = index == (nrThreads - 1) ? N : (index + 1) * (N - primesToSqrt.get(primesToSqrt.size() - 1) + 1) / nrThreads;

        System.out.println("Start:" + start);
        System.out.println("End: " + end);

        return new CheckTask(primesToSqrt, start, end);
    }

    public static void main(String[] args) throws Exception {

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < nrThreads; ++i) {
            threads.add(initCheckTask(i, nrThreads, primesToSqrt));
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
    }
}