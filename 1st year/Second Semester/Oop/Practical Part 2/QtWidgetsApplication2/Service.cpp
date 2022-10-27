#include "Service.h"

Service::Service()
{
}

Service::Service(RepoProgrammer* rp, RepoSourceFile* rs)
{
	this->rp = rp;
	this->rs = rs;
}

Service::Service(const Service& s)
{
	this->rp = s.rp;
	this->rs = s.rs;
}

Service::~Service()
{
}

void Service::add_sourceFile(std::string name, std::string status, std::string creator, std::string reviewer)
{
	SourceFile sf{ name, status, creator, reviewer };
	if (name == "")
		throw runtime_error("Name empty!");
	else {
		if (this->get_index_sourceFile(name) == -1) {
			this->rs->add(sf);
			this->notify();
			this->rs->writeSourceFiles();
		}
		else
			throw runtime_error("Already exists!");
	}
}

void Service::remove_sourceFile(int index)
{
	this->rs->remove(index);
	this->notify();
	this->rs->writeSourceFiles();
}

void Service::update_sourceFile(std::string name, std::string status, std::string creator, std::string reviewer)
{
	SourceFile sf{ name, status, creator, reviewer };
	this->rs->update(this->get_index_sourceFile(name), sf);
	this->notify();
	this->rs->writeSourceFiles();
}

int Service::get_index_sourceFile(const std::string& name)
{
	int i = 0;
	for (auto& it : this->getRepoSourceFile()->getSourceFiles()) {
		if (it.getName() == name) {
			return i;
		}
		i++;
	}
	return -1;
}

void Service::readProgrammers()
{
	this->rp->readProgrammers();
}

void Service::readSourceFiles()
{
	this->rs->readSourceFiles();
}

RepoProgrammer* Service::getRepoProgrammer()
{
	return this->rp;
}

RepoSourceFile* Service::getRepoSourceFile()
{
	return this->rs;
}
