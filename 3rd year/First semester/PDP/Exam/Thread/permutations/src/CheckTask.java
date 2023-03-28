
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class CheckTask extends Thread {
    static List<List<Integer>> sols;
    protected int start;
    protected int end;
    protected int N;

    public CheckTask(int start, int end, int N, List<List<Integer>> solutions) {
        this.start = start;
        this.end = end;
        this.N = N;
        sols = solutions;
    }

    public static boolean check(List<Integer> vec){
        return vec.get(0)%2==0;
    }

    public static void back(List<Integer> sol, int n, List<List<Integer>> sols){
        if(sol.size()==n){
            if(check(sol)){
                sols.add(new ArrayList<>(sol));
            }
            return;
        }

        for (int i = 0; i <= n ; i++) {
            if(sol.contains(i)) continue;
            sol.add(i);
            back(sol, n, sols);
            sol.remove(sol.size()-1);

        }
    }

    @Override
    public void run() {
        List<List<Integer>> localSols = new ArrayList<>(N);
        for (int i = start; i < end; i++) {
            List<Integer> sol = new ArrayList<>();
            sol.add(i);
            back(sol, N, localSols);
        }
        sols.addAll(localSols);
    }
}
