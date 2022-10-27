package Model.Utils;

import javafx.util.Pair;

import java.util.ArrayList;

public interface MyIBarrier {

    void setBarrier(MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> barrier);
    MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> getBarrier();
    Integer getBarrierAddress();
    void put(Integer foundIndex, Pair<Integer, ArrayList<Integer>> integerListPair);

    int getCurrentFreeAddress();
}
