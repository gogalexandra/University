package Model.Utils;

public interface MyILatch {
    MyIDictionary<Integer, Integer> getLatch();
    Integer getLatchAddress();
    int getCurrentFreeAddress();
    void put(Integer int1, Integer int2);
}
