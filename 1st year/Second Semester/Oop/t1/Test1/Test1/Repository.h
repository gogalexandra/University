#pragma once
#include "STLVector.h"
#include "Entity.h"

using namespace std;

class Repository
{
private:
	STLVector genes;

public:

	Repository() = default;

	bool addGene(Gene& g);

	int findPosition(string org, string name);

	STLVector getGenes() const { return genes; }

	STLVector filterGene(string given_sequence);

};
