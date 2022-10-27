#include "Programmer.h"
#include <vector>
#include <sstream>

Programmer::Programmer()
{
}

Programmer::Programmer(std::string name, int files1, int files2)
{
	this->name = name;
	this->revisedFiles = files1;
	this->totalFiles = files2;
}

Programmer::~Programmer()
{
}

std::string Programmer::getName()
{
	return this->name;
}

int Programmer::getRevisedFiles()
{
	return this->revisedFiles;
}

int Programmer::getTotalFiles()
{
	return this->totalFiles;
}

void Programmer::setName(std::string n)
{
	this->name = n;
}

void Programmer::setRevisedFiles(int files)
{
	this->revisedFiles = files;
}

void Programmer::setTotalFiles(int files)
{
	this->totalFiles = files;
}

std::istream& operator>>(std::istream& input, Programmer& p)
{
    std::vector<std::string> tokens;
    std::string token;

    std::string line;
    getline(input, line);
    std::stringstream tokenStream(line);

    while (getline(tokenStream, token, ',')) {
        tokens.push_back(token);
    }

    if (tokens.size() != 3) {
        return input;
    }
    else {
        p.setName(tokens[0]);
        int files1 = std::stoi(tokens[1]);
        int files2 = std::stoi(tokens[2]);
        p.setRevisedFiles(files1);
        p.setTotalFiles(files2);
    }
    return input;
}

std::ostream& operator<<(std::ostream& os, Programmer& p)
{
    os << p.getName() << "," << p.getRevisedFiles() << "," << p.getTotalFiles() << std::endl;
    return os;
}
