#pragma once
#include "Sale.h"

class Discount
{
public:
	Discount();
	virtual double calc(Sale s) = 0 ;
	~Discount();

private:

};

Discount::Discount()
{
}

Discount::~Discount()
{
}