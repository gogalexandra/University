#include "Writer.h"
#include <sstream>

Writer::Writer()
{
}

Writer::Writer(std::string n, std::string t)
{
	this->name = n;
	this->expertize = t;
}

Writer::~Writer()
{
}

std::string Writer::getName()
{
	return this->name;
}

std::string Writer::getExpertize()
{
	return this->expertize;
}

void Writer::setName(std::string given_name)
{
    this->name = given_name;
}

void Writer::setExpertize(std::string given_type)
{
    this->expertize = given_type;
}

std::istream& operator>>(std::istream& input, Writer& w) {
    std::vector<std::string> tokens;
    std::string token;

    std::string line;
    getline(input, line);
    std::stringstream tokenStream(line);

    while (getline(tokenStream, token, ',')) {
        tokens.push_back(token);
    }

    if (tokens.size() != 2) {
        return input;
    }
    else {
        w.setName(tokens[0]);
        w.setExpertize(tokens[1]);
    }
    return input;
}

std::ostream& operator<<(std::ostream& os, Writer& w)
{
    os << w.getName() << "," << w.getExpertize() << std::endl;
    return os;
}
