#include "Service.h"


Service::Service(Repository& r)
{
	this->repo = &r;
}

void Service::addItemToRepo(const string& category, const string& name, int quantity)
{
	this->repo->addItem(category, name, quantity);
}

void Service::deleteItemFromRepo(string name)
{
	this->repo->deleteItem(name);
}

vector<Item> Service::filterItemsFromRepo(string given_category)
{
	return this->repo->filterItems(given_category);
}

Service& Service::operator=(const Service& s)
{
	this->repo = s.repo;
	return *this;
}

void Service::openFile()
{
	this->repo->load();
}

