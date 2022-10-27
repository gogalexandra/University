#pragma once
#include <vector>
#include "SourceFile.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include "Observer.h"

using namespace std;

class RepoSourceFile : public Observable
{
public:
	RepoSourceFile();
	~RepoSourceFile();

	std::vector<SourceFile> getSourceFiles();
	void add(SourceFile sf);
	void remove(int index);
	void update(int index, SourceFile sf);

	void readSourceFiles();
	void writeSourceFiles();


private:
	std::vector<SourceFile> sourceFiles;
};