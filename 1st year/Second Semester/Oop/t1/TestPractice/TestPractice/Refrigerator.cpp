#include "Refrigerator.h"

Refrigerator::Refrigerator(const string& id, const double weight, const string& usage, bool freezer)
{
	this->id = id;
	this->weight = weight;
	this->electricityUsageClass = usage;
	this->hasFreezer = freezer;
}

string Refrigerator::getelectricityUsageClass()
{
	return this->electricityUsageClass;
}

bool Refrigerator::gethasFreezer()
{
	return this->hasFreezer;
}

string Refrigerator::toString()
{
	string s = Appliance::toString();
	s = s  +  "; Electricity Usage: " + this->electricityUsageClass + "; Freezer: ";
	if (this->hasFreezer)
		s += "true";
	else
		s += "false";
	return s;
}

double Refrigerator::consumedElectricity()
{
	double value;
	if (this->electricityUsageClass == "A")
		value = 30*3;
	else
		if (this->electricityUsageClass == "A+")
			value = 30 * 2.5;
		else
			value = 30*2;
	if (this->hasFreezer)
		return value + 20;
	else
		return value;
}