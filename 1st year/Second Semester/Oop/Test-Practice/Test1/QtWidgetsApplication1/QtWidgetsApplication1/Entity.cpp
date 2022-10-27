#include "Entity.h"

Item::Item(const string& category, const string& name, const int quantity)
{
	this->category = category;
	this->name = name;
	this->quantity = quantity;
}

string Item::getCategory()
{
	return this->category;
}

string Item::getName()
{
	return this->name;
}

int Item::getQuantity()
{
	return this->quantity;
}

string Item::toString()
{
	string s;
	s = "Category: " + this->category + " ;Name: " + this->name + " ;Quantity: " + to_string(this->quantity);
	return s;
}
