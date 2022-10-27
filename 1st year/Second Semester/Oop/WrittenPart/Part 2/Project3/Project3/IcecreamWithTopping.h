#pragma once
#include "Icecream.h"

class IcecreamWithTopping : public Icecream
{
public:
	IcecreamWithTopping();
	~IcecreamWithTopping();
	std::string getDescription() override;
	double computePrice() override;
	virtual std::string addTopping() = 0;
protected:
	Icecream* ic;
};

IcecreamWithTopping::IcecreamWithTopping()
{
}

IcecreamWithTopping::~IcecreamWithTopping()
{
}

inline std::string IcecreamWithTopping::getDescription()
{
	return ic->getDescription() + addTopping();
}

inline double IcecreamWithTopping::computePrice()
{
	return ic->computePrice();
}
