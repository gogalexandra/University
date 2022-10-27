package Model.Utils;

import javafx.util.Pair;

import java.util.ArrayList;

public interface MyILock {
    MyIDictionary<Integer, Integer> getLock();
    Integer getLockAddress();
    int getCurrentFreeAddress();
    void put(Integer int1, Integer int2);
}
