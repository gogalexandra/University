#pragma once
#include "Sale.h"

class Discount {
public:
    virtual double calc(Sale& s) = 0;
};

