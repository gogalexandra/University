#include "Order.h"
#include "BasicPizza.h"
#include "PizzaWithCheeseTopping.h"
#include "PizzaWithPepperoniTopping.h"
#include <iostream>

void createdOrder() {
    BasicPizza p1{"Cannibale"};
    BasicPizza p2{"Mexicana"};
    PizzaWithCheeseTopping p3{};

    Order o;
    o.addPizza(&p1);
    o.addPizza(&p2);
    o.addPizza(&p3);
}

//int main() {
//    std::cout << "---Creating order---" << std::endl;
//    createdOrder();
//    return 0;
//}
