#include "RepoIdea.h"

RepoIdea::RepoIdea()
{
}

RepoIdea::~RepoIdea()
{
}

std::vector<Idea> RepoIdea::getIdeas()
{
	return this->ideas;
}

void RepoIdea::add(Idea i)
{
	this->ideas.push_back(i);

}

void RepoIdea::remove(int index)
{
	this->ideas.erase(this->ideas.begin() + index);
	
}

void RepoIdea::update(int index, Idea i)
{
	this->ideas.at(index) = i;
}


void RepoIdea::readIdeas()
{
	ifstream input("ideas.txt", 0);
	string line;
	while (getline(input, line))
	{
		istringstream iss(line);
		Idea i;
		if (!(iss >> i)) { break; } // error
		this->ideas.push_back(i);
	}
}

void RepoIdea::writeIdeas()
{
	ofstream file;
	file.open("../ideas.txt");
	vector<Idea> all = this->getIdeas();
	for (auto& i : all) {
		file << i;
	}
	file.close();
}
