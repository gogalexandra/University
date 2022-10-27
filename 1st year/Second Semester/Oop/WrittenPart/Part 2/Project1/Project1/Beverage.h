#pragma once
#include <string>
#include <iostream>

class Beverage
{
public:
	Beverage();
	Beverage(std::string descrip);
	~Beverage();
	virtual double price() = 0;
	void print();

private:
	std::string description;
};

Beverage::Beverage()
{
}

Beverage::Beverage(std::string descrip)
{
	this->description = descrip;
}

Beverage::~Beverage()
{

}

void Beverage::print()
{
	std::cout << this->description << " " << std::to_string(this->price()) << " RON";
}
