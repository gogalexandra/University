#pragma once
#include <string>
#include <istream>
#include <QWidget>

class Ethnologists
{
public:
	Ethnologists();
	Ethnologists(std::string n, std::string t);
	~Ethnologists();
	std::string getName();
	std::string getThematicArea();
	void setName(std::string given_name);
	void setThematicArea(std::string given_thematicArea);

	friend std::istream& operator>>(std::istream& is, Ethnologists& e);
	friend std::ostream& operator<<(std::ostream& os, const Ethnologists& e);

private:
	std::string name;
	std::string thematicArea;
};