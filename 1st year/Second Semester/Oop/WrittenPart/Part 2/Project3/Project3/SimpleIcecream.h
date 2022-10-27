#pragma once
#include "Icecream.h"

class SimpleIcecream : public Icecream
{
public:
	SimpleIcecream();
	SimpleIcecream(std::string d, double p);
	~SimpleIcecream();
	std::string getDescription() override;
	double computePrice() override;

private:
	std::string description;
	double price;
};

SimpleIcecream::SimpleIcecream()
{
}

inline SimpleIcecream::SimpleIcecream(std::string d, double p)
{
	this->description = d;
	this->price = p;
}

SimpleIcecream::~SimpleIcecream()
{
}

inline std::string SimpleIcecream::getDescription()
{
	return "simple icecream with " + this->description;
}

inline double SimpleIcecream::computePrice()
{
	return this->price;
}
