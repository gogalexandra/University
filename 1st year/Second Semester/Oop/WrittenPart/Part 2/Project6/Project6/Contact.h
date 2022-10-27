#pragma once
#include "Message.h"

class Contact
{
public:
	Contact();
	Contact(std::string n);
	~Contact();
	virtual void sendMessage(Message m) = 0;
	virtual void addContact(Contact* c) = 0;

protected:
	std::string name;
};

Contact::Contact()
{
}

inline Contact::Contact(std::string n)
{
	this->name = n;
}

Contact::~Contact()
{
}