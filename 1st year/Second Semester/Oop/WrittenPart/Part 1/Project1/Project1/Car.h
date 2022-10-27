#pragma once
#include <string>

class Car
{
public:
	Car();
	Car(std::string t; int n);
	~Car();
	int getType();
	int getNr();

private:
	std::string type;
	int nr;

};

Car::Car()
{
}

Car::Car(std::string t; int n)
{
	this->type = t;
	this->nr = n;
}

Car::~Car()
{
}

inline int Car::getType()
{
	return this->type;
}

inline int Car::getNr()
{
	return this->nr;
}
