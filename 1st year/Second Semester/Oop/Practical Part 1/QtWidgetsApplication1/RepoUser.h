#pragma once
#include <vector>
#include "User.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

using namespace std;

class RepoUser
{
public:
	RepoUser();
	~RepoUser();
	std::vector<User> getUsers();
	void readUsers(std::string file_name);
	friend std::istream& operator>>(std::istream& is, User& u);

private:
	std::vector<User> users;
};

