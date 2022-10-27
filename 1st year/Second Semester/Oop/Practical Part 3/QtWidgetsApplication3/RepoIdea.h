#pragma once
#include <vector>
#include "Idea.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include "Observer.h"

using namespace std;

class RepoIdea : public Observable
{
public:
	RepoIdea();
	~RepoIdea();

	std::vector<Idea> getIdeas();
	void add(Idea  i);
	void remove(int index);
	void update(int index, Idea i);

	void readIdeas();
	void writeIdeas();

private:
	std::vector<Idea> ideas;
};

