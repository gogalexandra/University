#include "Pizza.h"
#include <string>

class BasicPizza : public Pizza {
protected:
    std::string description;
    double price;

public:
    BasicPizza(const std::string& givenDescription) : description(givenDescription){};
    std::string getDescription() override;
    double computePrice() override;
};

std::string BasicPizza::getDescription() {
    return "basic pizza " + this->description;
}

double BasicPizza::computePrice() {
    return this->price;
}
