#pragma once
#include "IcecreamWithTopping.h"

class IcecreamWithChocolateTopping : public IcecreamWithTopping
{
public:
	IcecreamWithChocolateTopping();
	IcecreamWithChocolateTopping(Icecream* ice);
	~IcecreamWithChocolateTopping();
	std::string addTopping() override;
	double computePrice() override;

private:

};

IcecreamWithChocolateTopping::IcecreamWithChocolateTopping()
{
}

inline IcecreamWithChocolateTopping::IcecreamWithChocolateTopping(Icecream* ice)
{
	ic = ice;
}

IcecreamWithChocolateTopping::~IcecreamWithChocolateTopping()
{
}

inline std::string IcecreamWithChocolateTopping::addTopping()
{
	return " with chocolate topping";
}

inline double IcecreamWithChocolateTopping::computePrice()
{
	return ic->computePrice() + 3.0;
}
