#pragma once
#include "Beverage.h"

class BeverageWithMilk : public Beverage {
private:
    Beverage* b;
    int milkCount;

public:
    BeverageWithMilk(Beverage* beverage, int givenMilkCount);
    double price() override;
    void print();
};

BeverageWithMilk::BeverageWithMilk(Beverage* beverage, int givenMilkCount) {
    this->b = beverage;
    this->milkCount = givenMilkCount;
}

double BeverageWithMilk::price() {
    return (this->b->price() + this->milkCount * 0.5);
}

void BeverageWithMilk::print() {
    this->b->print();
    std::cout << ", " << std::to_string(this->milkCount) << "\n";
}


