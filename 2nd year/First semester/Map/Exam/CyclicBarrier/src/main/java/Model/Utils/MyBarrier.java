package Model.Utils;

import javafx.util.Pair;

import java.util.ArrayList;

public class MyBarrier implements MyIBarrier{
    private MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> barrier;
    private Integer barrierAddress;


    public MyBarrier(){
        barrier = new MyDictionary<>();
        barrierAddress = 0;
    }

    @Override
    public void setBarrier(MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> barrier) {
        this.barrier = barrier;
    }

    @Override
    public MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> getBarrier() {
        return barrier;
    }

    @Override
    public Integer getBarrierAddress() {
        barrierAddress ++;
        return barrierAddress - 1;
    }

    @Override
    public void put(Integer foundIndex, Pair<Integer, ArrayList<Integer>> integerListPair) {
        barrier.add(foundIndex, integerListPair);
    }

    @Override
    public int getCurrentFreeAddress() {
        return barrierAddress;
    }
}
