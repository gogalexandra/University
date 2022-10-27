#pragma once
#include <string>
#include <istream>
#include <QWidget>
#include "Observer.h"
#include "RepoIssue.h"

class User 
{
public:
	User();
	User(std::string n, std::string t);
	~User();
	std::string getName();
	std::string getType();
	void setName(std::string given_name);
	void setType(std::string given_type);

	friend std::istream& operator>>(std::istream& is, User& u);
	friend ostream& operator<<(ostream& os, const User& user);

private:
	std::string name;
	std::string type;
};


