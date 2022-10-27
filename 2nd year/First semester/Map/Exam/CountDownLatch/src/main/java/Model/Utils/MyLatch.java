package Model.Utils;

public class MyLatch implements MyILatch{
    private MyIDictionary<Integer, Integer> latch;
    private Integer address;

    public MyLatch(){
        latch = new MyDictionary<>();
        address = 0;
    }

    @Override
    public MyIDictionary<Integer, Integer> getLatch() {
        return latch;
    }

    @Override
    public Integer getLatchAddress() {
        address ++;
        return address - 1;
    }

    @Override
    public int getCurrentFreeAddress() {
        return address;
    }

    @Override
    public void put(Integer int1, Integer int2) {
        latch.add(int1, int1);
    }
}
