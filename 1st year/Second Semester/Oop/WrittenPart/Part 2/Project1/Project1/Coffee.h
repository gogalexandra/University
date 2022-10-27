#pragma once
#include "Beverage.h"

class Coffee: public Beverage
{
public:
	Coffee();
	~Coffee();
	double price() override;

private:

};

Coffee::Coffee(): Beverage("Coffee")
{
}

Coffee::~Coffee()
{
}

inline double Coffee::price()
{
	return 2.5;
}
