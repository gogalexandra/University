#include "Service.h"

Service::Service()
{
}

Service::Service(RepoUser* u, RepoIssue* i)
{
	this->ri = i;
	this->ru = u;
}

Service::Service(const Service& s)
{
	this->ri = s.ri;
	this->ru = s.ru;
}

Service::~Service()
{
}

void Service::add_issue(string description, string status, string reporter, string solver)
{
	Issue i{ description, status, reporter, solver };
	if (this->get_index_issue_service(i.getDescription()) == -1) {
		this->ri->add(i);
		this->notify();
		this->ri->writeIssues("issues.txt");
	}
	else {
		throw runtime_error("Already exists");
	}
}

void Service::remove_issue(int index)
{
	return this->ri->remove(index);
	this->notify();
	this->ri->writeIssues("issues.txt");
}

void Service::update_issue(const std::string& description, const std::string& status, const std::string& reporter, const std::string& solver)
{
	Issue i{ description, status, reporter, solver };
	this->ri->update(this->get_index_issue_service(i.getDescription()), i);
	this->notify();
	this->ri->writeIssues("issues.txt");
}

int Service::get_index_issue_service(const std::string& description)
{
	int i = 0;
	for (auto& a : this->getRepoIssue()->getIssues()) {
		if (a.getDescription() == description) {
			return i;
		}
		i++;
	}
	return -1;
}

void Service::readIssues()
{
	this->ri->readIssues("issues.txt");
}

void Service::readUsers()
{
	this->ru->readUsers("users.txt");
}

RepoUser* Service::getRepoUser()
{
	return this->ru;
}

RepoIssue* Service::getRepoIssue()
{
	return this->ri;
}