#pragma once
#include <vector>
#include "Writer.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

using namespace std;

class RepoWriter
{
public:
	RepoWriter();
	~RepoWriter();
	std::vector<Writer> getWriters();
	void readWriters();
	friend std::istream& operator>>(std::istream& is, Writer& w);

private:
	std::vector<Writer> writers;
};

