#pragma once
#include "Discount.h"
#include "Sale.h"

class SumDiscount: public Discount
{
public:
	SumDiscount();
	~SumDiscount();
	void add(Discount* d);
	double calc(Sale s) override;


private:
	std::vector<Discount*> discounts;
};

SumDiscount::SumDiscount()
{
}

SumDiscount::~SumDiscount()
{
}

void SumDiscount::add(Discount* d)
{
	this->discounts.push_back(d);
}

inline double SumDiscount::calc(Sale s)
{
	double sum = 0.0;
	for (auto i : discounts) {
		sum += i->calc(s);
	}
	return sum;
}
