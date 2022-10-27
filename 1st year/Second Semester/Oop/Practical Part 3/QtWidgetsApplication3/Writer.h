#pragma once
#include <string>
#include <istream>
#include <QWidget>

class Writer 
{
public:
	Writer();
	Writer(std::string n, std::string t);
	~Writer();
	std::string getName();
	std::string getExpertize();
	void setName(std::string given_name);
	void setExpertize(std::string given_expertize);

	friend std::istream& operator>>(std::istream& is, Writer& w);
	friend std::ostream& operator<<(std::ostream& os, const Writer& w);

private:
	std::string name;
	std::string expertize;
};


