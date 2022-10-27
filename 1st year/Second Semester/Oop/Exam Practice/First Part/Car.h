#pragma once
#include <string>
#include <iostream>

class Car {
private:
    std::string brand;
    int number;

public:
    Car();
    Car(const std::string& givenName, int givenNumber);
    ~Car();

    std::string getBrand();
    int getNumber();
    bool operator==(const Car& c) const;
    friend std::ostream& operator<<(std::ostream& out, const Car& c);
};

inline std::ostream& operator<<(std::ostream& out, const Car& c) {
    out << c.brand << " ";
    out << c.number;
    return out;
}

Car::Car() {

}

Car::Car(const std::string &givenName, int givenNumber) {
    this->brand = givenName;
    this->number = givenNumber;
}

Car::~Car() {

}

std::string Car::getBrand() {
    return this->brand;
}

int Car::getNumber() {
    return this->number;
}

bool Car::operator==(const Car &c) const {
    if (this->brand == c.brand)
        return true;
    return false;
}


