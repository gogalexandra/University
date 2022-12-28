import org.w3c.dom.ls.LSInput;

import java.util.*;


public class Truck {
    private String companyName;
    private String foodTruckType;
    //soldProducts<productName, [producPrice, productStock]>
    private Map<String, List<Integer>> soldProducts;
    private int totalIncome;
    private int nrOfProductsSold;


    //default constructor
    public Truck(){
        this.companyName = "";
        this.foodTruckType = "";
        this.soldProducts = new HashMap<String, new ArrayList<Integer>()>();
        this.totalIncome = 0;
        this.nrOfProductsSold = 0;
    }

    //parameterized constructor
    public Truck(String givenCompanyName, String givenFoodTruckType, Map<String, List<Integer>> givenSoldProducts,
                 int givenTotalIncome, int givenNrOfProductsSold){
        //here we can add validation for the foodTruckType for ex (the value must be one of those mentioned in
        // the requirement)
        this.companyName = givenCompanyName;
        this.foodTruckType = givenFoodTruckType;
        this.soldProducts = givenSoldProducts;
        this.totalIncome= givenTotalIncome;
        this.nrOfProductsSold = givenNrOfProductsSold;
    }


    //getters for members of the class
    public String getCompanyName(){ return this.companyName; }
    public String getFoodTruckType(){ return this.foodTruckType; }
    public Map<String, List<Integer>> getSoldProducts() { return this.soldProducts; }
    public int getTotalIncome() { return this.totalIncome; }
    public int getProductsSold() { return this.nrOfProductsSold; }



    //check if there are product left to be sold
    public Boolean stockLeft(String productName){
        return this.soldProducts.get(productName).get(1) > 0;
    }

    public void productSold(String productName) throws Exception {
        //here we could add a function to check if the product stock is > 0 so that the selling will be valid
        if (stockLeft(productName)) {

            //increase the total number of products sold
            this.nrOfProductsSold += 1;
            //decrease the stock with one element
            this.soldProducts.get(productName).set(1, this.soldProducts.get(productName).get(1) - 1);
            //increase the total income with the product price
            //here the income could be calculated with the help of anther class to apply some taxes, discounts etc
            this.totalIncome += this.soldProducts.get(productName).get(0);
        }
        else
            throw new Exception("No product of that type left :(");
    }
}
