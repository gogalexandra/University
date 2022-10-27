#pragma once
#include <string>
#include <iostream>

class Beverage {
protected:
    std::string description;

public:
    Beverage();
    Beverage(const std::string& givenDescription);
    ~Beverage();

    virtual double price() = 0;
    void print();
};

Beverage::Beverage() {

}

Beverage::Beverage(const std::string& givenDescription) {
    this->description = givenDescription;
}

Beverage::~Beverage() {

}

void Beverage::print() {
    std::cout << this->description << " " << std::to_string(this->price()) << " RON";
}




