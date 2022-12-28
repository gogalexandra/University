#include "Service.h"

bool Service::addGeneToRepo(const string& organism, const string& name, const string& sequence)
{
	Gene g{ organism, name, sequence };
	return this->repo.addGene(g);
}

STLVector Service::filteredGenes(string given_sequence)
{
	return this->repo.filterGene(given_sequence);
}
