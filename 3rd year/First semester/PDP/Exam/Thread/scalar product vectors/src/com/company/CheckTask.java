package com.company;

import java.util.concurrent.locks.Lock;

public class CheckTask extends Thread {
    protected final int[] vectorA;
    protected final int[] vectorB;
    protected final int[] vectorResult;
    protected final int start;
    protected int count;
    protected Lock mtx;

    public CheckTask( int startRow, int count, int[] vectorA, int[] vectorB, int[] vectorResult, Lock mtx) {
        this.vectorA = vectorA;
        this.vectorB = vectorB;
        this.vectorResult = vectorResult;
        this.start = startRow;
        this.count = count;
        this.mtx = mtx;
    }

    @Override
    public void run() {
        mtx.lock();
        int i = start;
        //System.out.println("startRow = " + i + ", startC = " + j);
        //System.out.println("matrix.length = " + matrix.length + ", startC = " + matrix[0].length);
        while (count > 0 && i < vectorA.length) {
            vectorResult[i] = vectorA[i] * vectorB[i];
            count--;
            i++;
        }
        mtx.unlock();
    }
}
