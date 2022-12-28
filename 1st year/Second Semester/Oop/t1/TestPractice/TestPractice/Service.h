#pragma once

#include <vector>
#include "Repository.h"

typedef Appliance elem;

class Service {
private:
	Repository repo;

public:

	Service() {};
	explicit Service(Repository r) : repo{ move(r) } {}
	~Service() {};
	bool addToRepo(elem* e);
	void write(string filename, double value);
	vector<elem*> getElems();

};