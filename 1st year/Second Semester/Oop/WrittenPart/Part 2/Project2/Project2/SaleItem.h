#pragma once

class SaleItem
{
public:
	SaleItem();
	SaleItem(int c, double p);
	~SaleItem();
	int getCode();
	double getPrice();

private:
	int code;
	double price;
};

SaleItem::SaleItem()
{
}

inline SaleItem::SaleItem(int c, double p)
{
	this->code = c;
	this->price = p;
}

SaleItem::~SaleItem()
{
}

inline int SaleItem::getCode()
{
	return this->code;
}

inline double SaleItem::getPrice()
{
	return this->price;
}
