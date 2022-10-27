#include "Pizza.h"
#include <vector>

class Order {
private:
    std::vector<Pizza*> myOrder;

public:
    void addPizza(Pizza* givenPizza);
    void printOrder();
};

void Order::addPizza(Pizza *givenPizza) {
    this->myOrder.push_back(givenPizza);
}

void Order::printOrder() {
    for (auto& it : this->myOrder) {
        std::cout << it->getDescription() + " " + it->computePrice();
    }
}

create
