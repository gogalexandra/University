#pragma once
#include <string>
using namespace std;

class Idea
{
public:
	Idea();
	Idea(std::string description, std::string status, std::string creator, int act);
	~Idea();

	const std::string &getDescription() const;
	const std::string &getStatus() const;
	const std::string &getCreator() const;
	const int &getAct() const ;

	void setDescription(std::string given_description);
	void setStatus(std::string given_status);
	void setCreator(std::string given_creator);
	void setAct(int given_act);
	
	friend std::istream& operator>>(std::istream& input, Idea& i);
	friend std::ostream& operator<<(std::ostream& os, const Idea& i);

private:
	std::string description;
	std::string status;
	std::string creator;
	int act;

};