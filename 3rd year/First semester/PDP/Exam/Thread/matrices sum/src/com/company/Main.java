package com.company;

import com.company.CheckTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final int nrThreads = 9;
    public static final int[][] matrixA = initMatrix();
    public static final int[][] matrixB = initMatrix();

    private static final int rows = 4;
    private static final int cols = 5;
    public static final int[][] matrixResult = new int[rows][cols];

    private static Lock mtx = new ReentrantLock();


    public static int[][] initMatrix() {
        Random random = new Random();
        int[][] randomMatrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                randomMatrix[i][j] = random.nextInt(10);
                //randomMatrix[i][j] = 1;
                //System.out.println("elem = " + randomMatrix[i][j]);
            }
        }
        System.out.println("Matrix  = " + Arrays.deepToString(randomMatrix));
        return randomMatrix;

    }

    public static CheckTask initCheckTask(int index) {
        int count = rows * cols / nrThreads;

        int startRow = count * index / cols;
        int startCol = count * index % cols;

        if (index == nrThreads - 1)
            count += rows * cols % nrThreads;

        System.out.println("count = " + count);
        return new CheckTask(startRow, startCol, count, matrixA, matrixB, matrixResult, mtx);
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
        System.out.println("sum fuck u = " + Arrays.deepToString(matrixResult));

    }
}