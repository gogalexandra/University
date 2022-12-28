#include <fstream>
#include <sstream>
#include "Repo.h"

Repository::Repository()
{
	this->load();
}

vector<TimeInterval> Repository::filterProbability(int given_probability)
{
	vector<TimeInterval> filtered;
	for (auto& it : this->day) {
		if (it.getPrecip() < given_probability)
			filtered.push_back(it);
	}
	return filtered;
}

vector<TimeInterval> Repository::filterDescription(vector<string> given_description)
{
	vector<TimeInterval> filtered;
	vector<string>:: iterator it2;
	for (auto& it : this->day) {
		it2 = find(given_description.begin(), given_description.end(), it.getDescription());
		if (it2 != given_description.end())
			filtered.push_back(it);
	}
	return filtered;
}

void Repository::load()
{
	ifstream input("Info.txt", 0);
	string line;
	while (std::getline(input, line))
	{
		std::istringstream iss(line);
		TimeInterval i;
		if (!(iss >> i)) { break; } // error
		this->day.push_back(i);
	}
}

void Repository::save()
{
	ofstream output("Info.txt");
	output << *this;
	output.close();
}
