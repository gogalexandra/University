package Model.Utils;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MySemaphore implements MyISemaphore{
    private MyIDictionary<Integer, MyTriplet<Integer, ArrayList<Integer>, Integer>> semaphore;
    private Integer semaphoreAddress;

    public MySemaphore(){
        semaphore = new MyDictionary<>();
        semaphoreAddress = 0;
    }

    @Override
    public void setSemaphore(MyIDictionary<Integer, MyTriplet<Integer, ArrayList<Integer>, Integer>> semaphore) {
        this.semaphore = semaphore;
    }

    public MyIDictionary<Integer, MyTriplet<Integer, ArrayList<Integer>, Integer>> getSemaphore() {
        return semaphore;
    }


    public Integer getSemaphorAddress(){
        semaphoreAddress ++;
        return semaphoreAddress - 1;
    }

    @Override
    public Integer getCurrentFreeAddress() {
        return semaphoreAddress;
    }

    @Override
    public void put(Integer foundIndex, MyTriplet<Integer, ArrayList<Integer>, Integer> integerListTripler) {
        semaphore.add(foundIndex, integerListTripler);
    }
}
