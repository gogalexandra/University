#pragma once
#include "PizzaWithTopping.h"

class PizzaWithPepperoniTopping : public PizzaWithTopping
{
public:
	PizzaWithPepperoniTopping();
	PizzaWithPepperoniTopping(Pizza* p);
	~PizzaWithPepperoniTopping();
	std::string addTopping() override;
	double computePrice() override;

private:

};

PizzaWithPepperoniTopping::PizzaWithPepperoniTopping()
{
}

inline PizzaWithPepperoniTopping::PizzaWithPepperoniTopping(Pizza* p)
{
	pizza = p;
}

PizzaWithPepperoniTopping::~PizzaWithPepperoniTopping()
{
}

inline std::string PizzaWithPepperoniTopping::addTopping()
{
	return " with pepperoni topping";
}

inline double PizzaWithPepperoniTopping::computePrice()
{
	return pizza->computePrice() + 3.0;
}
