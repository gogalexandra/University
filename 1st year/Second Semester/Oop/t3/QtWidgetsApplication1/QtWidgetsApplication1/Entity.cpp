#include "Entity.h"

TimeInterval::TimeInterval(const int start, const int end, const int precip, const string& description)
{
	this->start = start;
	this->end = end;
	this->precip = precip;
	this->description = description;
}

int TimeInterval::getStart()
{
	return this->start;
}

int TimeInterval::getEnd()
{
	return this->end;
}

int TimeInterval::getPrecip()
{
	return this->precip;
}

string TimeInterval::getDescription()
{
	return this->description;
}

string TimeInterval::toString()
{
	string s;
	s = "Iterval: " + to_string(this->start) + " to " + to_string(this->end) + " ;Precipitation: " + to_string(this->precip) + " :Details: " + this->description;
	return s;
}
