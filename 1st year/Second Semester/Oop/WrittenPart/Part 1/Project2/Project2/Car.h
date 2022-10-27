#pragma once
#include <string>

class Car
{
public:
	Car();
	Car(std::string t, int n);
	~Car();
	std::string getType();
	int getNr();

private:
	std::string type;
	int nr;

};

Car::Car()
{
}

Car::Car(std::string t, int n)
{
	this->type = t;
	this->nr = n;
}

Car::~Car()
{
}

std::string Car::getType()
{
	return this->type;
}

int Car::getNr()
{
	return this->nr;
}
