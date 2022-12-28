#include "Service.h"

Service::Service()
{
}

Service::Service(RepoEthnologists* e, RepoBuildings* b)
{
	this->re = e;
	this->rb = b;
}

Service::Service(const Service& s)
{
	this->re = s.re;
	this->rb = s.rb;
}

Service::~Service()
{
}

void Service::readEthnologists()
{
	this->re->readEthnologists();
}

RepoEthnologists* Service::getRepoEthnologists()
{
	return this->re;
}

void Service::readBuildings()
{
	this->rb->readBuildings();
}

RepoBuildings* Service::getRepoBuildings()
{
	return this->rb;
}

void Service::add_building(int id, std::string description, std::string thematicArea, std::vector<std::string> location)
{
	if (this->get_index_building_service(id) != -1 || description == "")
		throw std::runtime_error("Not ok");
	else {
		Buildings b{ id, description, thematicArea, location };
		this->rb->add(b);
		this->notify();
		this->rb->writeBuildings();
	}
}

void Service::update_building(const int& id, const std::string& description, const std::string& thematicArea, const std::vector<std::string>& location)
{
	Buildings b{ id, description, thematicArea, location };
	this->rb->update(this->get_index_building_service(id), b);
	this->notify();
	this->rb->writeBuildings();
}

int Service::get_index_building_service(const int& id)
{
	int i = 0;
	for (auto& a : this->getRepoBuildings()->getBuildings()) {
		if (a.getId() == id) {
			return i;
		}
		i++;
	}
	return -1;
}
