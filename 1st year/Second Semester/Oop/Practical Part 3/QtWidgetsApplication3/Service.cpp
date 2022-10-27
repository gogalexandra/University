#include "Service.h"

Service::Service()
{
}

Service::Service(RepoWriter* w, RepoIdea* i)
{
	this->ri = i;
	this->rw = w;
}

Service::Service(const Service& s)
{
	this->ri = s.ri;
	this->rw = s.rw;
}

Service::~Service()
{
}

void Service::add_idea(string description, string status, string creator, int act)
{
	Idea i{ description, status, creator, act };

	if (description == "" || (act != 1 && act != 2 && act != 3) )
		throw runtime_error("Not ok");
	else {
		if (this->get_index_idea_service(i.getDescription(), i.getAct() == -1)) {
			this->ri->add(i);
			this->notify();
			this->ri->writeIdeas();
		}
		else 
			throw runtime_error("Already exists");
	}
}

void Service::remove_idea(int index)
{
	this->ri->remove(index);
	this->notify();
	this->ri->writeIdeas();
}

void Service::update_idea(const std::string& description, const std::string& status, const std::string& creator, const int& act)
{
	Idea i{ description, status, creator, act };
	this->ri->update(this->get_index_idea_service(i.getDescription(), i.getAct()), i);
	this->notify();
	this->ri->writeIdeas();
}

int Service::get_index_idea_service(const std::string& description, int act)
{
	int i = 0;
	for (auto& a : this->getRepoIdea()->getIdeas()) {
		if (a.getDescription() == description && a.getAct() == act) {
			return i;
		}
		i++;
	}
	return -1;
}

void Service::readIdeas()
{
	this->ri->readIdeas();
}

void Service::readWriters()
{
	this->rw->readWriters();
}

RepoWriter* Service::getRepoWriter()
{
	return this->rw;
}

RepoIdea* Service::getRepoIdea()
{
	return this->ri;
}