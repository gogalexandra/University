#include "RepoUser.h"

RepoUser::RepoUser()
{
}

RepoUser::~RepoUser()
{
}

std::vector<User> RepoUser::getUsers()
{
	return this->users;
}

void RepoUser::readUsers(std::string file_name)
{
	ifstream input("users.txt", 0);
	string line;
	while (getline(input, line))
	{
		istringstream iss(line);
		User u;
		if (!(iss >> u)) { break; } // error
		this->users.push_back(u);
	}
}