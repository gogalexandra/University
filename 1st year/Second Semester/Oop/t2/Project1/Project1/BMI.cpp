#include "BMI.h"

BMI::BMI(const string& date, const double value)
{
	this->date = date;
	this->value = value;
}

double BMI::getValue()
{
	return this->value;
}

string BMI::toString()
{
	string s = Measurement::toString();
	s = s + "; BMI Value: " + to_string(this->value);
	return s;
}

bool BMI::isNormalValue()
{
	if (this->value < 18.5 or this->value > 25.0)
		return false;
	else
		return true;
}
