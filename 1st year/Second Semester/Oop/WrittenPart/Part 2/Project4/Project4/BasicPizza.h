#pragma once
#include "Pizza.h"

class BasicPizza : public Pizza
{
public:
	BasicPizza();
	BasicPizza(std::string d, double p);
	~BasicPizza();
	std::string getDescription() override;
	double computePrice() override;

private:
	std::string description;
	double price;
};

BasicPizza::BasicPizza()
{
}

inline BasicPizza::BasicPizza(std::string d, double p)
{
	this->description = d;
	this->price = p;
}

BasicPizza::~BasicPizza()
{
}

inline std::string BasicPizza::getDescription()
{
	return "Basic pizza " + this->description;
}

inline double BasicPizza::computePrice()
{
	return this->price;
}
