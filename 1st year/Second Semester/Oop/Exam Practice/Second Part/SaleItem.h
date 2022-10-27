#pragma once

class SaleItem {
private:
    int code;
    double price;

public:
    SaleItem();
    SaleItem(int givenCode, double givenPrice);
    ~SaleItem();
    int getCode();
    double getPrice();
    void setPrice(double newPrice);

};

