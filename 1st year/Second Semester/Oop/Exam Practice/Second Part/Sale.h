#pragma once
#include "SaleItem.h"
#include <vector>

class Sale {
private:
    std::vector<SaleItem> items;

public:
    Sale();
    ~Sale();
    std::vector<SaleItem>getItems();
    void addSaleItem(SaleItem& si);
};


