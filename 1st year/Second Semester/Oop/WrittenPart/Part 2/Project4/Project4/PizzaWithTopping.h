#pragma once
#include "Pizza.h"

class PizzaWithTopping : public Pizza
{
public:
	PizzaWithTopping();
	~PizzaWithTopping();
	std::string getDescription() override;
	double computePrice() override;
	virtual std::string addTopping() = 0;
protected:
	Pizza* pizza;
};

PizzaWithTopping::PizzaWithTopping()
{
}

PizzaWithTopping::~PizzaWithTopping()
{
}

inline std::string PizzaWithTopping::getDescription()
{
	return pizza->getDescription() + addTopping();
}

inline double PizzaWithTopping::computePrice()
{
	return pizza->computePrice();
}
