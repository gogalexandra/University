#pragma once
#include "Beverage.h"

class BeverageWithMilk : public Beverage 
{
public:
	BeverageWithMilk();
	BeverageWithMilk(Beverage* b, int mc);
	~BeverageWithMilk();
	double price() override;
	void print();

private:
	int milkCount;
	Beverage* b;
};

BeverageWithMilk::BeverageWithMilk()
{
}

inline BeverageWithMilk::BeverageWithMilk(Beverage* b, int mc )
{
	this->b = b;
	this->milkCount = mc;
}

BeverageWithMilk::~BeverageWithMilk()
{
}

inline double BeverageWithMilk::price()
{
	return this->milkCount * 0.5 + this->b->price();
}

inline void BeverageWithMilk::print()
{
	this->b->print();
	std::cout << ", " << std::to_string(this->milkCount) << "\n";
}
