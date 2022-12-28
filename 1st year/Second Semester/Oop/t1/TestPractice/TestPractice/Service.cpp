#include "Service.h"
#include <vector>
#include <fstream>
#include <algorithm>


bool Service::addToRepo(elem* e)
{
	return this->repo.add(e);
}

void Service::write(string filename, double value)
{
	this->repo.getAllWithConsumedElectricityLessThan(value);
	this->repo.writeToFile(filename);
}

vector<elem*> Service::getElems()
{
	return this->repo.getAll();
}
