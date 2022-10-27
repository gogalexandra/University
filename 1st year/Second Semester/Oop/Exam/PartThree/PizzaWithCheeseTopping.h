#include "PizzaWithTopping.h"

class PizzaWithCheeseTopping : public PizzaWithTopping {
public:
    std::string addTopping() override;
    double computePrice() override;
};

std::string PizzaWithCheeseTopping::addTopping() {
    return "with extra cheese topping";
}

double PizzaWithCheeseTopping::computePrice() {
    return this->myPizza->computePrice() + 5;
}
