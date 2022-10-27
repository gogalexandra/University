package Model.Utils;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public interface MyISemaphore {
    void setSemaphore(MyIDictionary<Integer, MyTriplet<Integer, ArrayList<Integer>, Integer>> semaphore);
    MyIDictionary<Integer, MyTriplet<Integer, ArrayList<Integer>, Integer>> getSemaphore();
    Integer getSemaphorAddress();
    Integer getCurrentFreeAddress();
    void put(Integer foundIndex, MyTriplet<Integer, ArrayList<Integer>, Integer> integerListTriple);
}
