#pragma once
#include <string>
#include <iostream>

class Activity
{
public:
	Activity();
	Activity(std::string d, std::string t);
	~Activity();
	std::string getDescription();
	std::string getTime();
	friend std::ostream& operator<<(std::ostream& out, const Activity& a);

private:
	std::string description;
	std::string time;
};

inline std::ostream& operator<<(std::ostream& out, const Activity& a) {
	out << "Activity " << a.description << " will take place at " << a.time<< ".";
	return out;
}

Activity::Activity()
{
}

Activity::Activity(std::string d, std::string t)
{
	this->description = d;
	this->time = t;
}

Activity::~Activity()
{
}

inline std::string Activity::getDescription()
{
	return this->description;
}

inline std::string Activity::getTime()
{
	return this->time;
}
