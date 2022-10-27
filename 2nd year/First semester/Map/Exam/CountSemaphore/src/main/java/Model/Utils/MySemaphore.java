package Model.Utils;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MySemaphore implements MyISemaphore{
    private MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> semaphore;
    private Integer semaphoreAddress;

    public MySemaphore(){
        semaphore = new MyDictionary<>();
        semaphoreAddress = 0;
    }

    public MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> semaphore) {
        this.semaphore = semaphore;
    }

    public Integer getSemaphorAddress(){
        semaphoreAddress++;
        return semaphoreAddress - 1;
    }

    @Override
    public void put(Integer foundIndex, Pair<Integer, ArrayList<Integer>> integerListPair) {
        semaphore.add(foundIndex, integerListPair);
    }

    @Override
    public int getCurrentFreeAddress() {
        return semaphoreAddress;
    }
}
