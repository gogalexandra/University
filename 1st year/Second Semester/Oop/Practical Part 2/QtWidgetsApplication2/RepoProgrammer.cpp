#include "RepoProgrammer.h"

RepoProgrammer::RepoProgrammer()
{
}

RepoProgrammer::~RepoProgrammer()
{
}

std::vector<Programmer> RepoProgrammer::getProgrammers()
{
	return this->programmers;
}

void RepoProgrammer::readProgrammers()
{
	ifstream input("programmers.txt", 0);
	string line;
	while (getline(input, line))
	{
		istringstream iss(line);
		Programmer p;
		if (!(iss >> p)) { break; } // error
		this->programmers.push_back(p);
	}
}