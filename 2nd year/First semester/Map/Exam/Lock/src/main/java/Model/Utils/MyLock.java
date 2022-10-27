package Model.Utils;

public class MyLock implements MyILock{
    private MyIDictionary<Integer, Integer> lock;
    private Integer address;


    public MyLock(){
        lock = new MyDictionary<>();
        address = 0;
    }
    @Override
    public MyIDictionary<Integer, Integer> getLock() {
        return lock;
    }

    @Override
    public Integer getLockAddress() {
        address ++;
        return address - 1;
    }

    @Override
    public int getCurrentFreeAddress() {
        return address;
    }

    @Override
    public void put(Integer int1, Integer int2) {
        lock.add(int1, int2);
    }
}
