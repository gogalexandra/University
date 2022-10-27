#pragma once
#include "Contact.h"
#include <vector>

class Group: public Contact
{
public:
	Group();
	~Group();
	void addContact(Contact* c)override;
	void sendMessage(Message m) override;

private:
	std::vector <Contact*> all;
};

Group::Group()
{
}

Group::~Group()
{
}

inline void Group::addContact(Contact* c)
{
	this->all.push_back(c);
}

inline void Group::sendMessage(Message m)
{
	for (auto i : this->all){
		i->sendMessage(m);
	}
}
