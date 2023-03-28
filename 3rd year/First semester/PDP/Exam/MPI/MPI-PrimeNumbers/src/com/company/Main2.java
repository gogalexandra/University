package com.company;

import mpi.MPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {
    static final int N = 100;

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

    public static List<Integer> getPrimesInInterval(int start, int end) {
        List<Integer> primes = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static List<Integer> convertToList(Object obj) {
        List<Integer> list = new ArrayList<>();
        if (obj instanceof int[]) {
            int[] array = (int[]) obj;
            for (int value : array) {
                list.add(value);
            }
        } else if (obj instanceof List) {
            List<?> objList = (List<?>) obj;
            for (Object o : objList) {
                if (o instanceof Integer) {
                    list.add((Integer) o);
                }
            }
        }
        return list;
    }

    public static void master(int N, int processes){
        long startTime = System.nanoTime();
//        int length = (int) ((N - Math.sqrt(N) + 1) / (processes - 1));
//        int start;
//        int end = (int) Math.sqrt(N);
        for (int i = 1; i  < processes; i++){
//            start = end;
//            end = start + length;
//            if (i == processes - 1) {
//                end = N;
//            }

//            int[] parameters = new int[2];
//            parameters[0] = start;
//            parameters[1] = end;
            MPI.COMM_WORLD.Send(new Object[]{getPrimesUpToSqrt(N)}, 0, 1, MPI.OBJECT, i, 0);
            MPI.COMM_WORLD.Send(new int[]{i}, 0, 1, MPI.INT, i, 0);
        }

        Object[] results = new Object[processes - 1];
        for (int i = 1; i  < processes; i++){
            MPI.COMM_WORLD.Recv(results, i - 1, 1, MPI.OBJECT, i, 0);
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("Execution time: " + time);
        System.out.println(Arrays.toString(results) + "\n");
    }

    public static void regularWorker(int me, int nrOfProcesses){
        Object[] listOfPrimesUpToSqrt = new Object[1];
        int[] processesId = new int[2];

        MPI.COMM_WORLD.Recv(listOfPrimesUpToSqrt, 0, 1, MPI.OBJECT, 0, 0);
        MPI.COMM_WORLD.Recv(processesId, 0, 2, MPI.INT, 0, 0);

        List<Integer>  listOfPrimesUpToSqrtConverted = convertToList(listOfPrimesUpToSqrt);

        int begin = (int) (processesId[0] * (N  - Math.sqrt(N) )/ nrOfProcesses);
        int end = (int) ((processesId[0] + 1) * (N  - Math.sqrt(N) )/ nrOfProcesses);

        List<Integer> result = getPrimesInInterval(begin, end);

        MPI.COMM_WORLD.Send(new Object[]{result}, 0, 1, MPI.OBJECT, 0, 0);
    }

    public static void main(String[] args) {
        MPI.Init(args);
        //own id
        int me = MPI.COMM_WORLD.Rank();
        //nr of launched instances
        int size = MPI.COMM_WORLD.Size();

        if (me == 0){
            System.out.println("Master process: \n");

            System.out.println("N:" + N);
            System.out.println("\n");

            master(N, size);
        } else {
            regularWorker(me, size);
        }
        MPI.Finalize();
    }
}
