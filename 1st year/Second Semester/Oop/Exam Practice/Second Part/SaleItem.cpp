#include "SaleItem.h"

SaleItem::SaleItem() {

}

SaleItem::SaleItem(int givenCode, double givenPrice) {
    this->code = givenCode;
    this->price = givenPrice;
}

SaleItem::~SaleItem() {

}

int SaleItem::getCode() {
    return this->code;
}

double SaleItem::getPrice() {
    return this->price;
}

void SaleItem::setPrice(double newPrice) {
    this->price = newPrice;
}



