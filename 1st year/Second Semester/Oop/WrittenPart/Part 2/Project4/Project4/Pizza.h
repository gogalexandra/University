#pragma once
#include <string>

class Pizza
{
public:
	Pizza();
	~Pizza();
	virtual std::string getDescription() = 0;
	virtual double computePrice() = 0;

private:

};

Pizza::Pizza()
{
}

Pizza::~Pizza()
{
}