package Model.Utils;

import javafx.util.Pair;

import java.util.ArrayList;

public interface MyISemaphore {
    void setSemaphore(MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> semaphore);
    MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> getSemaphore();
    Integer getSemaphorAddress();
    void put(Integer foundIndex, Pair<Integer, ArrayList<Integer>> integerListPair);

    int getCurrentFreeAddress();

}
