package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Inventory {
    private HashMap<Product, Integer> products;

    public Inventory(HashMap<Product, Integer> products) {
        this.products = new HashMap<>();
    }

    public Inventory() {
        this.products = new HashMap<>();
    }

    public Set<Product> getProducts() {
        return products.keySet();
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public void add(Product product, int quantity){
        if (this.products.containsKey(product)){
            this.products.replace(product, this.products.get(product) + quantity);
        }else{
            this.products.put(product, quantity);
        }

    }

    public void remove(Product product, int quantity){
        if (!this.products.containsKey(product)){
            throw new RuntimeException("Cannot remove quantity of inexistent product in inventory!");
        }
        else {
            if (this.getQuantity(product) < quantity) {
                throw new RuntimeException("Cannot remove desired quantity from inventory, existing quantity is not sufficient!");
            }
            this.products.replace(product, this.products.get(product) - quantity);
            if (this.getQuantity(product) == 0) {
                this.products.remove(product);
            }
        }
    }

    public float getTotal(){
        float totalPrice = 0.0f;
        for(Product product : getProducts()){
            totalPrice += product.getPrice()* getQuantity(product);
        }
        return totalPrice;
    }

    int getQuantity(Product product) {
        return this.products.getOrDefault(product, 0);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "products=" + products +
                '}';
    }
}
