package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final int nrThreads = 3;
    public static final int[] vectorA = initVector();
    public static final int[] vectorB = initVector();

    private static final int dim = 9;
    public static final int[] vectorResult = new int[dim];

    private static Lock mtx = new ReentrantLock();


    public static int[] initVector() {
        Random random = new Random();
        int[] randomVector = new int[dim];
        for (int i = 0; i < dim; i++) {
                randomVector[i] = random.nextInt(10);
                //randomVector[i][j] = 1;
                //System.out.println("elem = " + randomVector[i][j]);
        }

        System.out.println("Vectors = " + Arrays.toString(randomVector));
        return randomVector;

    }

    public static CheckTask initCheckTask(int index) {
        int count = dim / nrThreads;

        int start = index * count;
        int reminder = dim % nrThreads;

        if (index == nrThreads - 1)
            count += reminder;

        System.out.println("count = " + count);
        return new CheckTask(start, count, vectorA, vectorB, vectorResult, mtx);
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
        System.out.println("sum fuck u = " + Arrays.toString(vectorResult));

    }
}