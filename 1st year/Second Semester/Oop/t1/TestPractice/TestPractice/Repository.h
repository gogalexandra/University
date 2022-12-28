#pragma once
#include <vector>
#include "Appliance.h"

typedef Appliance elem;

class Repository {
private:
	vector<elem*> elements;
	vector<elem*> filtered_elements;

public:

	Repository() {};
	Repository(const Repository& r);
	bool add(elem* e);
	void writeToFile(string filename);
	vector<elem*> getAll();
	vector<elem*> getAllWithConsumedElectricityLessThan(double value);
	bool exists(elem e);

};