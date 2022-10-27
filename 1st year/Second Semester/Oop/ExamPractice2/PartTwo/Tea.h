#pragma once
#include "Beverage.h"

class Tea : public Beverage {
public:
    Tea();
    ~Tea();
    double price() override;
};

Tea::Tea() : Beverage("Tea") {

}

Tea::~Tea() {

}

double Tea::price() {
    return 1.5;
}



