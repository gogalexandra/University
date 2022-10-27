#pragma once
#include "Contact.h"
#include <iostream>

class Person : public Contact
{
public:
	Person();
	Person(std::string n);
	~Person();
	void sendMessage(Message m) override;
	void addContact(Contact* c) override;

private:

};

Person::Person()
{
}

inline Person::Person(std::string n)
{
	this->name = n;
}

Person::~Person()
{
}

inline void Person::sendMessage(Message m)
{
	std::cout << "Sending message " << m.getMessage() << " to "<< name << "\n";
}

inline void Person::addContact(Contact* c)
{
}
