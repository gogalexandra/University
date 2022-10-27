#include "Vegetables.h"

Vegetable::Vegetable()
{
}

Vegetable::Vegetable(const string& family, const string& name, list<string> parts)
{
	this->family = family;
	this->name = name;
	this->parts = parts;
}

string Vegetable::getFamily()
{
	return this->family;
}

string Vegetable::getName()
{
	return this->name;
}

list<string> Vegetable::getParts()
{
	return this->parts;
}
