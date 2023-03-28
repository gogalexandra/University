package com.company;

import java.util.AbstractMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CheckTask extends Thread {
    protected final int[][] matrixA;
    protected final int[][] matrixB;
    protected final int[][] matrixResult;
    protected final int startRow;
    protected final int startColumn;
    protected int count;
    protected Lock mtx;

    public CheckTask( int startRow, int startColumn, int count, int[][] matrixA, int[][] matrixB, int[][] matrixResult, Lock mtx) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixResult = matrixResult;
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.count = count;
        this.mtx = mtx;
    }

    @Override
    public void run() {
        mtx.lock();
        int i = startRow, j = startColumn;
        while (count > 0 && i < matrixA.length && j < matrixB[0].length) {

            matrixResult[i][j] = 0;
            int aux = 0;
            while (aux < matrixA[0].length) {
                matrixResult[i][j] += matrixA[i][aux] * matrixB[aux][j];
                aux++;
            }
            j++;
            count--;
            if (j ==  matrixA.length) {
                j = 0;
                i++;
            }
        }
        mtx.unlock();
    }
}
