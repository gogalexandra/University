import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    protected ArrayList<Integer> buffer = new ArrayList<Integer>();
    public Lock lock = new ReentrantLock(true);
    public Condition producerStartProducing = lock.newCondition();
    public Condition consumerStartConsuming = lock.newCondition();
}
