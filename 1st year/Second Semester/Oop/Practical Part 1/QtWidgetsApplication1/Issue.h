#pragma once
#include <string>
using namespace std;

class Issue
{
public:
	Issue();
	Issue(std::string description, std::string status, std::string reporter, std::string solver);
	~Issue();
	const std::string &getDescription() const;
	const std::string &getStatus() const;
	const std::string &getReporter() const;
	const std::string &getSolver() const ;
	std::string toString();

	void setDescription(std::string given_description);
	void setStatus(std::string given_status);
	void setReporter(std::string given_reporter);
	void setSolver(std::string given_solver);
	friend std::istream& operator>>(std::istream& input, Issue& i);
	friend std::ostream& operator<<(std::ostream& os, const Issue& issue);

private:
	std::string description;
	std::string status;
	std::string reporter;
	std::string solver;

};