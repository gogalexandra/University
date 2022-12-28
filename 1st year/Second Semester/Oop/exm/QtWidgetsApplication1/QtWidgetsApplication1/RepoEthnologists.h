#pragma once
#include <vector>
#include "Ethnologists.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

class RepoEthnologists
{
public:
	RepoEthnologists();
	~RepoEthnologists();
	std::vector<Ethnologists> getEthnologists();
	void readEthnologists();
	friend std::istream& operator>>(std::istream& is, Ethnologists& e);

private:
	std::vector<Ethnologists> ethnologists;
};