#pragma once

#include <vector>
#include "Measurement.h"

typedef Measurement elem;

class Person {
private:
	string name;
	vector<elem*> measurements;
public:

	Person() {};
	explicit Person(string n) : name{ move(n) } {}
	~Person() {};

	string getName();
	vector<elem*> getMeasuremnts();
	vector<elem*> getMeasuremntsByMonth(int month);
	vector<elem*> getMeasuremntsNewerThan(string date);

	void addMeasurement(elem* e);
	bool isHealthy(int month);
	void WriteToFile(string filename, string date);

};