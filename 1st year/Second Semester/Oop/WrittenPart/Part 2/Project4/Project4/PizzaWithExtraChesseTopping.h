#pragma once
#include "PizzaWithTopping.h"

class PizzaWithExtraChesseTopping : public PizzaWithTopping
{
public:
	PizzaWithExtraChesseTopping();
	PizzaWithExtraChesseTopping(Pizza* p);
	~PizzaWithExtraChesseTopping();
	std::string addTopping() override;
	double computePrice() override;

private:

};

PizzaWithExtraChesseTopping::PizzaWithExtraChesseTopping()
{
}

inline PizzaWithExtraChesseTopping::PizzaWithExtraChesseTopping(Pizza* p)
{
	pizza = p;
}

PizzaWithExtraChesseTopping::~PizzaWithExtraChesseTopping()
{
}

inline std::string PizzaWithExtraChesseTopping::addTopping()
{
	return " with extra chesse topping";
}

inline double PizzaWithExtraChesseTopping::computePrice()
{
	return pizza->computePrice() + 5.0;
}
