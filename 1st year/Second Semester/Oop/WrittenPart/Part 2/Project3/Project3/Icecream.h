#pragma once
#include <string>

class Icecream
{
public:
	Icecream();
	~Icecream();
	virtual std::string getDescription() = 0;
	virtual double computePrice() = 0;

private:

};

Icecream::Icecream()
{
}

Icecream::~Icecream()
{
}