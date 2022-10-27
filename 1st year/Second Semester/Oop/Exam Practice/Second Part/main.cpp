#include "Sale.h"
#include "Discount.h"
#include "ItemDiscount.h"
#include "SumDiscount.h"
#include <iostream>


int main() {
    SaleItem i1(1, 50);
    SaleItem i2(2, 10);
    SaleItem i3(3, 40);

    Sale s{};
    s.addSaleItem(i1);
    s.addSaleItem(i2);
    s.addSaleItem(i3);

    ItemDiscount i1d(1, 10);
    ItemDiscount i2d(2, 15);

    SumDiscount sum;
    sum.add(&i1d);
    sum.add(&i2d);

    std::cout << sum.calc(s) << std::endl;

    return 0;
}

