public class Consumer extends Thread{
    public int sum;
    public Buffer buffer;

    public Consumer(Buffer buffer) {
        this.sum = 0;
        this.buffer = buffer;
    }

    @Override
    public void run(){
        while (true) {
            try {
                buffer.lock.lock();
                while (buffer.buffer.isEmpty()) {
                    buffer.consumerStartConsuming.await();
                }
                sum += buffer.buffer.get(buffer.buffer.size() - 1);
                buffer.buffer.remove(buffer.buffer.size() - 1);
                buffer.producerStartProducing.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                buffer.lock.unlock();
                System.out.println("sum"+ sum);
            }

        }
    }
}
