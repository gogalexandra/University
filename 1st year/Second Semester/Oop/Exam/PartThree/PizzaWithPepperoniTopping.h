#include "PizzaWithTopping.h"

class PizzaWithPepperoniTopping : public PizzaWithTopping {
public:
    std::string addTopping() override;
    double computePrice() override;
};

std::string PizzaWithPepperoniTopping::addTopping() {
    return "with pepperoni topping";
}

double PizzaWithPepperoniTopping::computePrice() {
    return this->myPizza->computePrice() + 3;
}
