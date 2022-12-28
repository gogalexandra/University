#include "Person.h"
#include <fstream>

void Person::addMeasurement(elem* e)
{
	this->measurements.push_back(e);
}

bool Person::isHealthy(int month)
{
	if (month == 1) {
		bool healthy = true;
		for (auto& it : this->getMeasuremntsByMonth(month)) {
			if (not it->isNormalValue())
				healthy = false;
		}
		return healthy;
	}
	else {
		bool healthy = true;
		for (auto& it : this->getMeasuremntsByMonth(month)) {
			if (not it->isNormalValue())
				healthy = false;
		}
		for (auto& it : this->getMeasuremntsByMonth(month - 1)) {
			if (not it->isNormalValue())
				healthy = false;
		}
		return healthy;
	}
	
}

void Person::WriteToFile(string filename, string date)
{
	ofstream out(filename);
	for (auto& it : this->measurements) {
		if (it->getDate() > date)
			out << it->toString() << endl;
	}
	out.close();
}

string Person::getName()
{
	return this->name;
}

vector<elem*> Person::getMeasuremnts()
{
	return this->measurements;
}

vector<elem*> Person::getMeasuremntsByMonth(int month)
{
	vector<elem*> filtered;
	for (auto& it : this->measurements) {
		///select the good ones 
		string date = it->getDate().substr(6,7);
		
	}

	return vector<elem*>();
}

vector<elem*> Person::getMeasuremntsNewerThan(string date)
{
	vector<elem*> filtered;
	for (auto& it : this->measurements) {
		if (it->getDate() > date)
			filtered.push_back(it);
	}

	return filtered;
}
