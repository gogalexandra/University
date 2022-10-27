#include "User.h"
#include <sstream>

User::User()
{
}

User::User(std::string n, std::string t)
{
	this->name = n;
	this->type = t;
}

User::~User()
{
}

std::string User::getName()
{
	return this->name;
}

std::string User::getType()
{
	return this->type;
}

void User::setName(std::string given_name)
{
    this->name = given_name;
}

void User::setType(std::string given_type)
{
    this->type = given_type;
}

std::istream& operator>>(std::istream& input, User& u) {
    vector<std::string> tokens;
    string token;

    string line;
    getline(input, line);
    stringstream tokenStream(line);

    while (getline(tokenStream, token, ',')) {
        tokens.push_back(token);
    }

    if (tokens.size() != 2) {
        return input;
    }
    else {
        u.setName(tokens[0]);
        u.setType(tokens[1]);
    }
    return input;
}

ostream& operator<<(ostream& os, User& user)
{
    os << user.getName() << "," << user.getType() << endl;
    return os;
}
