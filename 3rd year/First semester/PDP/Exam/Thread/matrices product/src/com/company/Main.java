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

    private static final int nrThreads = 3;
    public static final int[][] matrixA = initMatrixA();
    public static final int[][] matrixB = initMatrixB();

    private static final int rowsA = 4;
    private static final int colsA = 5;
    private static final int rowsB = 5;
    private static final int colsB = 4;
    public static final int[][] matrixResult = new int[rowsA][colsB];

    private static Lock mtx = new ReentrantLock();


    public static int[][] initMatrixA() {
        Random random = new Random();
        int[][] randomMatrix = new int[rowsA][colsA];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                randomMatrix[i][j] = random.nextInt(10);
                //randomMatrix[i][j] = 1;
            }
        }
        System.out.println("Matrix  = " + Arrays.deepToString(randomMatrix));
        return randomMatrix;
    }

    public static int[][] initMatrixB() {
        Random random = new Random();
        int[][] randomMatrix = new int[rowsB][colsB];
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                randomMatrix[i][j] = random.nextInt(10);
                //randomMatrix[i][j] = 1;
            }
        }
        System.out.println("Matrix  = " + Arrays.deepToString(randomMatrix));
        return randomMatrix;
    }


    public static CheckTask initCheckTask(int index) {
        int count = rowsA * colsB / nrThreads;

        int startRow = count * index / colsB;
        int startCol = count * index % colsB;

        if (index == nrThreads - 1)
            count += rowsA * colsB % nrThreads;

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