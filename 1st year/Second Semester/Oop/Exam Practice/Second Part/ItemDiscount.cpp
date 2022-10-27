#include "ItemDiscount.h"

ItemDiscount::ItemDiscount(int givenCode, int givenPercentage) {
    this->code = givenCode;
    this->percentage = givenPercentage;
}

double ItemDiscount::calc(Sale &s) {
    std::vector<SaleItem> items = s.getItems();
    double newPrice = 0;
    std::for_each(items.begin(), items.end(), [this, &newPrice](auto& it){
        if (it.getCode() == this->code) {
            newPrice = it.getPrice() - (it.getPrice() * this->percentage) / 100;
        }
    });
    return newPrice;
}
