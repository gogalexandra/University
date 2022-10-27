#include "Sale.h"

Sale::Sale() {

}

Sale::~Sale() {

}

std::vector<SaleItem> Sale::getItems() {
    return this->items;
}

void Sale::addSaleItem(SaleItem &si) {
    this->items.push_back(si);
}
