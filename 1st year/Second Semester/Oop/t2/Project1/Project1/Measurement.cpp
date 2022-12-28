#include "Measurement.h"

Measurement::Measurement(const string date)
{
	this->date = date;
}

string Measurement::getDate()
{
	return this->date;
}

string Measurement::toString()
{
	string s;
	s = "Date: " + this->date;
	return s;
}

bool Measurement::isNormalValue()
{
	return false;
}
