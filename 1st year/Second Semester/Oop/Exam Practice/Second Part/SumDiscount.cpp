#include "SumDiscount.h"

void SumDiscount::add(Discount* d) {
    this->discounts.push_back(d);
}

double SumDiscount::calc(Sale &s) {
    double sum = 0;
    std::for_each(this->discounts.begin(), this->discounts.end(), [&s, &sum](auto& it){
        sum += it->calc(s);
    });
    return sum;
}
