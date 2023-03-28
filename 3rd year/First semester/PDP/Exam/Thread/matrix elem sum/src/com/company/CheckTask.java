package com.company;

import java.util.AbstractMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CheckTask extends Thread {
    protected final int[][] matrix;
    protected final int startRow;
    protected final int startColumn;
    protected int count;
    protected Lock mtx;

    public CheckTask( int startRow, int startColumn, int count, int[][] matrix, Lock mtx) {
        this.matrix = matrix;
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.count = count;
        this.mtx = mtx;
    }

    @Override
    public void run() {
        //mtx.lock();
        int i = startRow, j = startColumn;
        //System.out.println("startRow = " + i + ", startC = " + j);
        //System.out.println("matrix.length = " + matrix.length + ", startC = " + matrix[0].length);
        while (count > 0 && i < matrix.length && j < matrix[0].length) {
            Main.resultSum.addAndGet(matrix[i][j]);
            System.out.println("sum += " + matrix[i][j]);
            count--;
            if (j >=  matrix[0].length - 1) {
                //System.out.println(j);
                j = 0;
                i++;
            }
            else {
                j++;
            }
        }
        //mtx.unlock();
        System.out.println(Main.resultSum);
    }
}
