#include "DishWashingMachine.h"

DishWashingMachine::DishWashingMachine(const string id, double weight, double length, double e)
{
	this->id = id;
	this->weight = weight;
	this->washingCycleLength = length;
	this->consumedElectricityForOneHour = e;
}

double DishWashingMachine::getwashingCycleLength()
{
	return this->washingCycleLength;
}

double DishWashingMachine::getconsumedElectricity()
{
	return this->consumedElectricityForOneHour;
}

string DishWashingMachine::toString()
{
	string s;
	s = "ID: " + this->id + "; Weight: " + to_string(this->weight) + "; Washing cycle length: " + to_string(this->washingCycleLength) + "; Consumed electricity for one hour: " + to_string(this->consumedElectricityForOneHour);
	return s;
}

double DishWashingMachine::consumedElectricity()
{
	return this->consumedElectricityForOneHour * this->washingCycleLength * 8;
}
