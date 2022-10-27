#pragma once
#include "Discount.h"

class ItemDiscount : public Discount {
private:
    int code;
    int percentage;

public:
    ItemDiscount(int givenCode, int givenPercentage);
    double calc(Sale &s) override;
};


