#pragma once
#include "BeverageWithMilk.h"
#include "Coffee.h"
#include "Tea.h"

class BeverageMachine {
public:
    void prepare(std::string beverageType, int milkCount);
};

void BeverageMachine::prepare(std::string beverageType, int milkCount) {
    if (beverageType == "Coffee") {
        Coffee c{};
        BeverageWithMilk b{&c, milkCount};
        b.print();
    }
    else {
        Tea t{};
        BeverageWithMilk b{&t, milkCount};
        b.print();
    }
}


