#pragma once
#include "SaleItem.h"
#include <vector>

class Sale
{
public:
	Sale();
	~Sale();
	std::vector<SaleItem> getElems();
	void addSaleItem(SaleItem si);

private:
	std::vector<SaleItem> elems;
};

Sale::Sale()
{
}

Sale::~Sale()
{
}

inline std::vector<SaleItem> Sale::getElems()
{
	return this->elems;
}

inline void Sale::addSaleItem(SaleItem si)
{
	this->elems.push_back(si);
}
