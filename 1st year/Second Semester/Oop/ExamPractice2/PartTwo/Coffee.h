#pragma once
#include "Beverage.h"

class Coffee : public Beverage {
public:
    Coffee();
    ~Coffee();
    double price() override;
};

Coffee::Coffee() : Beverage("Coffee") {

}

Coffee::~Coffee() {

}

double Coffee::price() {
    return 2.5;
}


