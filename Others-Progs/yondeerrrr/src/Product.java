public class Product {
    private int taxes;
    private int discount;
    private int price;

    public Product(int givenTaxes, int givenDiscount, int givenPrice){
        this.taxes = givenTaxes;
        this.discount = givenDiscount;
        this.price = givenPrice;
    }
    public int calculatePrice(){
        return this.price - (this.price * this.discount) / 100;
    }

    public int calculateTaxes(){
        return this.price + (this.price * this.taxes) / 100;
    }

    @Override
    public String toString() {

        return  "taxes=" + taxes +
                ", discount=" + discount +
                ", price=" + price +
                '}';
    }
}


class Hamburger extends Product {
    private String type;

    public Hamburger(int givenTaxes, int givenDiscount, int givenPrice, String givenType){
        super(givenTaxes, givenDiscount, givenPrice);
        this.type = givenType;
    }

    @Override
    public String toString() {
        return "Hamburger{" +
                "type='" + type + '\'' +
                super.toString() +
                '}';
    }
}

class Fries extends Product {
    private String type;

    public Fries(int givenTaxes, int givenDiscount, int givenPrice, String givenType){
        super(givenTaxes, givenDiscount, givenPrice);
        this.type = givenType;
    }

    public int getPrice(){
        return super.calculatePrice();
    }

    @Override
    public String toString() {
        return "Fries{" +
                "type='" + type + '\'' +
                super.toString() +
                '}';
    }
}