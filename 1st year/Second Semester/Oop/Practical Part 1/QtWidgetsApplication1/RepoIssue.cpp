#include "RepoIssue.h"

RepoIssue::RepoIssue()
{
}

RepoIssue::~RepoIssue()
{
}

std::vector<Issue> RepoIssue::getIssues()
{
	return this->issues;
}

void RepoIssue::add(Issue i)
{
	this->issues.push_back(i);

}

void RepoIssue::remove(int index)
{
	this->issues.erase(this->issues.begin() + index);
	
}

void RepoIssue::update(int index, Issue i)
{
	this->issues.at(index) = i;
}


void RepoIssue::readIssues(std::string file_name)
{
	ifstream input("issues.txt", 0);
	string line;
	while (getline(input, line))
	{
		istringstream iss(line);
		Issue u;
		if (!(iss >> u)) { break; } // error
		this->issues.push_back(u);
	}
}

void RepoIssue::writeIssues(std::string file_name)
{
	ofstream file;
	file.open("../issues.txt");
	vector<Issue> all = this->getIssues();
	for (auto& i : all) {
		file << i;
	}
	file.close();
}
