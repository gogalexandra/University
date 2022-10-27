#include "SourceFile.h"
#include <vector>
#include <sstream>

SourceFile::SourceFile()
{
}

SourceFile::SourceFile(std::string name, std::string status, std::string creator, std::string reviwer)
{
	this->name = name;
	this->status = status;
	this->creator = creator;
	this->reviewer = reviwer;
}

SourceFile::~SourceFile()
{
}

const std::string& SourceFile::getName() const
{
	return this->name;
}

const std::string& SourceFile::getStatus() const
{
	return this->status;
}

const std::string& SourceFile::getCreator() const
{
	return this->creator;
}

const std::string& SourceFile::getReviewer() const
{
	return this->reviewer;
}

void SourceFile::setName(std::string given_name)
{
	this->name = given_name;
}

void SourceFile::setStatus(std::string given_status)
{
	this->status = given_status;
}

void SourceFile::setCreator(std::string given_creator)
{
	this->creator = given_creator;
}

void SourceFile::setReviewer(std::string given_reviewer)
{
	this->reviewer = given_reviewer;
}

std::istream& operator>>(std::istream& input, SourceFile& sf)
{
    std::vector<std::string> tokens;
    std::string token;

    std::string line;
    getline(input, line);
    std::stringstream tokenStream(line);

    while (getline(tokenStream, token, ',')) {
        tokens.push_back(token);
    }

    if (tokens.size() == 4) {
        sf.setName(tokens[0]);
        sf.setStatus(tokens[1]);
        sf.setCreator(tokens[2]);
        sf.setReviewer(tokens[3]);
    }
    else if (tokens.size() == 3) {
        sf.setName(tokens[0]);
        sf.setStatus(tokens[1]);
        sf.setCreator(tokens[2]);
        sf.setReviewer("");
    }
    return input;
}

std::ostream& operator<<(std::ostream& os, const SourceFile& sf)
{
    os << sf.getName() << "," << sf.getStatus() << "," << sf.getCreator() << "," << sf.getReviewer() << std::endl;
    return os;
}
