#pragma once
#include "RepoUser.h"
#include "RepoIssue.h"
#include "Observer.h"

class Service : public Observable
{
public:
	Service();
	Service(RepoUser* u, RepoIssue* i);
	Service(const Service& s);
	~Service();
	void add_issue(std::string description, std::string status, std::string reporter, std::string solver);
	void remove_issue(int index);
	void update_issue(const std::string& description, const std::string& status, const std::string& reporter, const std::string& solver);
	int get_index_issue_service(const std::string& description);
	void readIssues();
	void readUsers();
	RepoUser* getRepoUser();
	RepoIssue* getRepoIssue();

private:
	RepoUser* ru;
	RepoIssue* ri;
};

