#pragma once
#include <vector>
#include "Issue.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include "Observer.h"

using namespace std;

class RepoIssue : public Observable
{
public:
	RepoIssue();
	~RepoIssue();
	std::vector<Issue> getIssues();
	void add(Issue i);
	void remove(int index);
	void update(int index, Issue i);

	void readIssues(std::string file_name);
	void writeIssues(std::string file_name);

	//friend std::istream& operator>>(std::istream& is, Issue& i);

private:
	std::vector<Issue> issues;
};

