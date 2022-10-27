#pragma once
#include <vector>
#include "Programmer.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

using namespace std;

class RepoProgrammer
{
public:
	RepoProgrammer();
	~RepoProgrammer();

	std::vector<Programmer> getProgrammers();
	void readProgrammers();
	friend std::istream& operator>>(std::istream& is, Programmer& p);

private:
	std::vector<Programmer> programmers;
};
