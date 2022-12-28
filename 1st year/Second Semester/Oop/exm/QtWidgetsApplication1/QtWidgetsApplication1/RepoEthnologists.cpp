#include "RepoEthnologists.h"

RepoEthnologists::RepoEthnologists()
{
}

RepoEthnologists::~RepoEthnologists()
{
}

std::vector<Ethnologists> RepoEthnologists::getEthnologists()
{
	return this->ethnologists;
}

void RepoEthnologists::readEthnologists()
{
	std::ifstream input("ethnologists.txt", 0);
	std::string line;
	while (getline(input, line))
	{
		std::istringstream iss(line);
		Ethnologists e;
		if (!(iss >> e)) { break; } // error
		this->ethnologists.push_back(e);
	}
}
