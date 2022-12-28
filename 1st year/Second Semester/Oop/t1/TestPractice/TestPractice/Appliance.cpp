#include "Appliance.h"

Appliance::Appliance(const string id, double w)
{
	this->id = id;
	this->weight = w;
}

string Appliance::getId()
{
	return this->id;
}

double Appliance::getWeight()
{
	return this->weight;
}

string Appliance::toString() 
{
	string s;
	s = "ID: " + this->id + "; Weight: " + to_string(this->weight);
	return s;
}
//
//double Appliance::consumedElectricity()
//{
//	return 0.0;
//}
