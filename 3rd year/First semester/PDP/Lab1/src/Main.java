import Model.Bill;
import Model.Inventory;
import Model.Product;
import Model.Transaction;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Math.round;

public class Main {
    private static final int NUMBER__OF_THREADS = 5;
    private static float initialSum;
    private static Inventory deposit = new Inventory();
    private static List<Product> products = new ArrayList<>();
    //private static List<Bill> recordOfSales = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        ReadFromFile();
        initialSum = deposit.getTotal();
        Map<String, Lock> mutexes = new HashMap<>();
        for(Product prod: products){
            mutexes.put(prod.getName(), new ReentrantLock());
        }

        float start =  System.nanoTime() / 1000000;
        for (int i = 0; i < NUMBER__OF_THREADS; i++) {
            Transaction t = new Transaction(deposit, "t" + i, mutexes);
            int product = new Random().nextInt(10);
            for (int j = 0; j < product; j++) {
                int quantity = new Random().nextInt(10);
                int productId = new Random().nextInt(products.size());
                t.add(products.get(productId), quantity);

            }
            transactions.add(t);
        }


        List<Thread> threads = new ArrayList<>();

        transactions.stream().forEach(t -> threads.add(new Thread(t)));

        for (Thread thread : threads){
            thread.start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        verify();

        float end = System.nanoTime() / 1000000;
        System.out.println("\n End work: " + (end - start) / 1000 + " seconds");

    }

    static void verify() {
        System.err.println("Verifying the stock...");
        double sum = transactions.stream().mapToDouble(i -> {
            if (i == null)
                return 0.0f;
            else
                return i.getTotalPrice();
        }).sum();
        DecimalFormat df = new DecimalFormat("#.#");
        if(Objects.equals(df.format(sum), df.format(initialSum - deposit.getTotal()))) {
            System.err.println("Stock verification successful!");
        }
        else {
            System.err.println("Verification failed!");
        }
    }


    private static void ReadFromFile() {
        File file = new File("C:\\UBB Sem 5\\PDP\\Lab1\\src\\Inventory.txt");
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()){
                Lock mutex = new ReentrantLock();
                Product p = new Product(sc.next(), sc.nextFloat(), mutex);
                products.add(p);
                int quan = sc.nextInt();
                deposit.add(p, ((Integer) quan));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
