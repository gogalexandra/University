#include "Repository.h"
#include <vector>
#include <fstream>
#include <algorithm>

Repository::Repository(const Repository& r)
{
	this->elements = r.elements;
}

bool Repository::add(elem* e)
{
	if (exists(*e))
		return false;
	else
		this->elements.push_back(e);
	return true;
}

void Repository::writeToFile(string filename)
{
	ofstream out(filename);
	for (auto &it : this->filtered_elements) {
		out << it->toString() << endl;
	}
	out.close();
}

vector<elem*> Repository::getAll()
{
	return this->elements;
}

vector<elem*> Repository::getAllWithConsumedElectricityLessThan(double value)
{
	vector<elem*> filtered;
	for ( auto &it : this->elements) {
		if (it->consumedElectricity() < value)
			filtered.push_back(it);
	}
	this->filtered_elements = filtered;
	return filtered;
}

bool Repository::exists(elem e)
{
	for (auto& it : this->elements) {
		if (it->getId() == e.getId())
			return true;
	}
	return false;
}
