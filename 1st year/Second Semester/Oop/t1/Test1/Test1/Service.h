#pragma once
#include "Repository.h"

using namespace std;

class Service {

private:
	Repository repo;

public:
	explicit Service(Repository r) : repo{ move(r) } {}

	STLVector getRepo() const { return repo.getGenes(); }

	bool addGeneToRepo(const string& organism, const string& name, const string& sequence);

	STLVector filteredGenes(string given_sequence);
};