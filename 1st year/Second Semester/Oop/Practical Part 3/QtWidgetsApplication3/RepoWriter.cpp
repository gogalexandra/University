#include "RepoWriter.h"

RepoWriter::RepoWriter()
{
}

RepoWriter::~RepoWriter()
{
}

std::vector<Writer> RepoWriter::getWriters()
{
	return this->writers;
}

void RepoWriter::readWriters()
{
	ifstream input("writers.txt", 0);
	string line;
	while (getline(input, line))
	{
		istringstream iss(line);
		Writer w;
		if (!(iss >> w)) { break; } // error
		this->writers.push_back(w);
	}
}