#pragma once
#include <string>
#include <ostream>
class Programmer
{
public:
	Programmer();
	Programmer(std::string name, int files1, int files2);
	~Programmer();
	
	std::string getName();
	int getRevisedFiles();
	int getTotalFiles();

	void setName(std::string n);
	void setRevisedFiles(int files);
	void setTotalFiles(int files);

	friend std::istream& operator>>(std::istream& is, Programmer& p);
	friend std::ostream& operator<<(std::ostream& os, const Programmer& p);

private:
	std::string name;
	int revisedFiles;
	int totalFiles;
};
