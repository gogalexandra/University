#include "Buildings.h"
#include <iostream>
#include <sstream>
#include <cstring>
#include <string>
#include <vector>

Buildings::Buildings()
{
}

Buildings::Buildings(int id, std::string description, std::string thematicArea, std::vector<std::string> location)
{
	this->id = id;
	this->description = description;
	this->thematicArea = thematicArea;
	this->location = location;
}

Buildings::~Buildings()
{
}

const int& Buildings::getId() const
{
	return this->id;
}

const std::string& Buildings::getDescription() const
{
	return this->description;
}

const std::string& Buildings::getThematicArea() const
{
	return this->thematicArea;
}

const std::vector<std::string>& Buildings::getLocation() const
{
	return this->location;
}

void Buildings::setId(int given_id)
{
	this->id = given_id;
}

void Buildings::setDescription(std::string given_description)
{
	this->description = given_description;
}

void Buildings::setThematicArea(std::string given_thematicArea)
{
	this->thematicArea = given_thematicArea;
}

void Buildings::setLocation(std::vector<std::string> given_location)
{
	this->location = given_location;
}

std::istream& operator>>(std::istream& input, Buildings& b)
{
    std::vector<std::string> tokens;
    std::string token;

    std::string line;
    getline(input, line);
    std::stringstream tokenStream(line);

    while (getline(tokenStream, token, ';')) {
        tokens.push_back(token);
    }

    int a = stoi(tokens[0]);
    b.setId(a);
    b.setDescription(tokens[1]);
    b.setThematicArea(tokens[2]);
    std::vector<std::string> v;
    for (int i = 3; i < tokens.size(); i++)
        v.push_back(tokens[i]);
	b.setLocation(v);
    return input;
}

std::ostream& operator<<(std::ostream& os, const Buildings& b)
{
	os << b.id << ";" << b.description << ";" << b.thematicArea;
	for (auto i : b.location) {
		os << ";" << i << std::endl;
	}
	return os;
}
