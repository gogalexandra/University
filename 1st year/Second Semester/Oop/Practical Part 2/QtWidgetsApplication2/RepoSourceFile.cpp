#include "RepoSourceFile.h"

RepoSourceFile::RepoSourceFile()
{
}

RepoSourceFile::~RepoSourceFile()
{
}

std::vector<SourceFile> RepoSourceFile::getSourceFiles()
{
	return this->sourceFiles;
}

void RepoSourceFile::add(SourceFile sf)
{
	this->sourceFiles.push_back(sf);
}

void RepoSourceFile::remove(int index)
{
	this->sourceFiles.erase(this->sourceFiles.begin() + index);
}

void RepoSourceFile::update(int index, SourceFile sf)
{
	this->sourceFiles.at(index) = sf;
}

void RepoSourceFile::readSourceFiles()
{
	ifstream input("sourcefiles.txt", 0);
	string line;
	while (getline(input, line))
	{
		istringstream iss(line);
		SourceFile sf;
		if (!(iss >> sf)) { break; } // error
		this->sourceFiles.push_back(sf);
	}
}

void RepoSourceFile::writeSourceFiles()
{
	ofstream file;
	file.open("../sourcefiles.txt");
	vector<SourceFile> all = this->getSourceFiles();
	for (auto& i : all) {
		file << i;
	}
	file.close();
}
