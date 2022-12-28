#include "BP.h"

BP::BP(const string& date, const int sv, const int dv)
{
	this->date = date;
	this->systolicValue = sv;
	this->diastolicValue = dv;
}

int BP::getSystolic()
{
	return this->systolicValue;
}

int BP::getDiastolic()
{
	return this->diastolicValue;
}

string BP::toString()
{
	string s = Measurement::toString();
	s = s + "; Systolic Value: " + to_string(this->systolicValue) + "; Daastolic Value: " + to_string(this->diastolicValue);
	return s;
}

bool BP::isNormalValue()
{
	if (this->systolicValue < 90 or this->systolicValue > 119 or this->diastolicValue < 60 or this->diastolicValue > 79)
		return false;
	else
		return true;
}
