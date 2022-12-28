#include "RepoBuildings.h"

RepoBuildings::RepoBuildings()
{
}

RepoBuildings::~RepoBuildings()
{
}

std::vector<Buildings> RepoBuildings::getBuildings()
{
	return this->buildings;
}

void RepoBuildings::add(Buildings b)
{
	this->buildings.push_back(b);
}

void RepoBuildings::update(int index, Buildings b)
{
	this->buildings.at(index) = b;
}

void RepoBuildings::readBuildings()
{
	std::ifstream input("buildings.txt", 0);
	std::string line;
	while (getline(input, line))
	{
		std::istringstream iss(line);
		Buildings b;
		if (!(iss >> b)) { break; } // error
		this->buildings.push_back(b);
	}
}

void RepoBuildings::writeBuildings()
{
	std::ofstream file;
	file.open("../buildings.txt");
	std::vector<Buildings> all = this->getBuildings();
	for (auto& i : all) {
		file << i;
	}
	file.close();
}
