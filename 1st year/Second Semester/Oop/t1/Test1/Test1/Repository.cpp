#include "Repository.h"
#include <string>

using namespace std;

bool Repository::addGene(Gene& g)
{
	/// <summary>
	/// Checks if the object already exists(name and organism should be unique), is so its addes else no
	/// </summary>
	/// <param name="g">new object to be added</param>
	/// <returns>true is object is addes, false if it already exists</returns>
	int position = this->findPosition(g.getOrganism(), g.getName());
	if (position != -1)
		return false;

	this->genes.add(g);
	return true;
}


int Repository::findPosition(string org, string name) {
	/// <summary>
	/// Search throw all objects if one of them has the name name and organism org
	/// </summary>
	/// <returns>position of the object that has the same name and organism, -1 otherwise </returns>
	vector<Gene>::iterator it2;
	for (int i = 0; i < this->genes.getNrElements(); i++) {
		if (this->genes.getAll().at(i).getName() == name  && this->genes.getAll().at(i).getOrganism() == org)
			return i;
	}
	return -1;
}

STLVector Repository::filterGene(string given_sequence)
{
	STLVector array = this->getGenes();
	STLVector filtered{};

	for (auto& it : this->genes.getAll()) {
		if (given_sequence.empty()) {
				filtered.add(it);
		}
		else {
			std::size_t found = it.getSequence().find(given_sequence);
			if (found != std::string::npos)
				filtered.add(it);
		}
	}

	return filtered;
}
