#pragma once
#include "Discount.h"

class ItemDiscount: public Discount
{
public:
	ItemDiscount();
	ItemDiscount(int code, int precentage);
	~ItemDiscount();
	double calc(Sale s) override;

private:
	int code;
	int precentage;
};

ItemDiscount::ItemDiscount()
{
}

inline ItemDiscount::ItemDiscount(int code, int precentage)
{
	this->code = code;
	this->precentage = precentage;
}

ItemDiscount::~ItemDiscount()
{
}

inline double ItemDiscount::calc(Sale s)
{
	for (auto i : s.getElems()) {
		if (i.getCode() == this->code)
			return i.getPrice() - (i.getPrice() * this->precentage) / 100;
	}
}
