#pragma once
#include <vector>
#include "Buildings.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include "Observer.h"


class RepoBuildings : public Observable
{
public:
	RepoBuildings();
	~RepoBuildings();

	std::vector<Buildings> getBuildings();
	void add(Buildings b);
	void update(int index, Buildings b);

	void readBuildings();
	void writeBuildings();

private:
	std::vector<Buildings> buildings;
};
