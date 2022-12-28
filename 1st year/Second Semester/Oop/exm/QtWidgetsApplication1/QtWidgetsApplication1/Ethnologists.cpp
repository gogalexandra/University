#include "Ethnologists.h"
#include <sstream>

Ethnologists::Ethnologists()
{
}

Ethnologists::Ethnologists(std::string n, std::string t)
{
	this->name = n;
	this->thematicArea = t;
}

Ethnologists::~Ethnologists()
{
}

std::string Ethnologists::getName()
{
	return this->name;
}

std::string Ethnologists::getThematicArea()
{
	return this->thematicArea;
}

void Ethnologists::setName(std::string given_name)
{
    this->name = given_name;
}

void Ethnologists::setThematicArea(std::string given_thematicArea)
{
    this->thematicArea = given_thematicArea;
}

std::istream& operator>>(std::istream& input, Ethnologists& e) {
    std::vector<std::string> tokens;
    std::string token;

    std::string line;
    getline(input, line);
    std::stringstream tokenStream(line);

    while (getline(tokenStream, token, ';')) {
        tokens.push_back(token);
    }

    if (tokens.size() != 2) {
        return input;
    }
    else {
        e.setName(tokens[0]);
        e.setThematicArea(tokens[1]);
    }
    return input;
}

std::ostream& operator<<(std::ostream& os, Ethnologists& w)
{
    os << w.getName() << ";" << w.getThematicArea() << std::endl;
    return os;
}