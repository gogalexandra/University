#pragma once
#include "IcecreamWithTopping.h"

class IcecreamWithCaramelTopping : public IcecreamWithTopping
{
public:
	IcecreamWithCaramelTopping();
	IcecreamWithCaramelTopping(Icecream* ice);
	~IcecreamWithCaramelTopping();
	std::string addTopping() override;
	double computePrice() override;

private:

};

IcecreamWithCaramelTopping::IcecreamWithCaramelTopping()
{
}

inline IcecreamWithCaramelTopping::IcecreamWithCaramelTopping(Icecream* ice)
{
	ic = ice;
}

IcecreamWithCaramelTopping::~IcecreamWithCaramelTopping()
{
}

inline std::string IcecreamWithCaramelTopping::addTopping()
{
	return " with caramel topping";
}

inline double IcecreamWithCaramelTopping::computePrice()
{
	return ic->computePrice() + 2.0;
}