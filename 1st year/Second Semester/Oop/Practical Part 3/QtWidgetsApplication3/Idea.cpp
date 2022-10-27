#include "Idea.h"
#include <iostream>
#include <sstream>
#include <cstring>
#include <string>
#include <vector>

Idea::Idea()
{
}

Idea::Idea(std::string description, std::string status, std::string creator, int act)
{
	this->description = description;
	this->status = status;
	this->creator = creator;
	this->act = act;
}

Idea::~Idea()
{
}

const std::string& Idea::getDescription() const
{
	return this->description;
}

const std::string& Idea::getStatus() const
{
	return this->status;
}

const std::string& Idea::getCreator() const
{
	return this->creator;
}

const int& Idea::getAct() const
{
	return this->act;
}

void Idea::setDescription(std::string given_description)
{
    this->description = given_description;
}

void Idea::setStatus(std::string given_status)
{
	this->status = given_status;
}

void Idea::setCreator(std::string given_creator)
{
    this->creator = given_creator;
}

void Idea::setAct(int given_act)
{
	this->act = given_act;
}

std::istream& operator>>(std::istream& input, Idea& i) {
    std::vector<std::string> tokens;
    std::string token;

    std::string line;
    getline(input, line);
    std::stringstream tokenStream(line);

    while (getline(tokenStream, token, ',')) {
        tokens.push_back(token);
    }

    if (tokens.size() == 4) {
        i.setDescription(tokens[0]);
        i.setStatus(tokens[1]);
        i.setCreator(tokens[2]);
        int a = stoi(tokens[3]);
        i.setAct(a);
    }
    else if (tokens.size() == 3) {
        i.setDescription(tokens[0]);
        i.setStatus(tokens[1]);
        i.setCreator(tokens[2]);
        i.setAct(-1);
    }
    return input;
}

std::ostream& operator<<(std::ostream& os, const Idea& issue)
{
    os << issue.description << "," << issue.status << "," << issue.creator
        << "," << issue.act << std::endl;
    return os;
}
