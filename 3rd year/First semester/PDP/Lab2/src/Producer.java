import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread{
    public ArrayList<Integer> vector1;
    public ArrayList<Integer> vector2;
    public Buffer buffer;
    public int size;
    public int index;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
        this.size = 100;
        this.index = 0;
        this.vector1 = new ArrayList<Integer>();
        this.vector2 = new ArrayList<Integer>();
        for(int i = 0 ; i < size ; i++) {
//            vector1.add(ThreadLocalRandom.current().nextInt(0,20));
//            vector2.add(ThreadLocalRandom.current().nextInt(0,20));
            vector1.add(1);
            vector2.add(i+1);
        }

    }

    public int produceData(){
        System.out.println(vector1.get(index) *vector2.get(index));
        return vector1.get(index) *vector2.get(index);
    }

    @Override
    public void run(){
        while(index < size){
            try {
                buffer.lock.lock();
                while (buffer.buffer.size() > 2) {
                    try {
                        buffer.producerStartProducing.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer.buffer.add(produceData());
                buffer.consumerStartConsuming.signalAll();
                index++;
            } finally {
                buffer.lock.unlock();
            }
        }
    }

}

