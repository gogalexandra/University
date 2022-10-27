#include "Pizza.h"

class PizzaWithTopping : public Pizza {
protected:
    Pizza* myPizza;

public:
    virtual std::string addTopping() = 0;
    std::string getDescription() override;
    virtual double computePrice() = 0;
};

std::string PizzaWithTopping::getDescription() {
    return this->myPizza->getDescription();
}

