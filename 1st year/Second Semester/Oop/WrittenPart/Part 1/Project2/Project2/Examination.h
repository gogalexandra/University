#pragma once
#include <string>
#include <iostream>

class Examination
{
public:
	Examination();
	Examination(std::string s, int n);
	Examination(std::string s);
	~Examination();
	friend std::ostream& operator<<(std::ostream& out, const Examination& e);
	friend bool operator>(const Examination& e1, const Examination& e2);


private:
	std::string type;
	int nr;
};

std::ostream& operator<<(std::ostream& out, const Examination& e) {
	if (e.nr != -1)
		out << "To the examination for discipline " << e.type << " the student obtained grade : " << e.nr << ".\n";
	else 
		out << "Student did not participate to the examination for discipline " << e.type << ".\n";
	return out;
}

bool operator>(const Examination& e1, const Examination& e2) {
	if (e1.nr > e2.nr)
		return true;
	return false;
}

Examination::Examination()
{
}

inline Examination::Examination(std::string s, int n)
{
	this->type = s;
	this->nr = n;
}

inline Examination::Examination(std::string s)
{
	this->type = s;
	this->nr = -1;
}

Examination::~Examination()
{
}
