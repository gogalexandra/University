#pragma once
#include <string>

class SourceFile
{
public:
	SourceFile();
	SourceFile(std::string name, std::string status, std::string creator, std::string reviwer);
	~SourceFile();

	const std::string& getName() const;
	const std::string& getStatus() const;
	const std::string& getCreator() const;
	const std::string& getReviewer() const;

	void setName(std::string given_name);
	void setStatus(std::string given_status);
	void setCreator(std::string given_creator);
	void setReviewer(std::string given_reviewer);
	
	friend std::istream& operator>>(std::istream& input, SourceFile& sf);
	friend std::ostream& operator<<(std::ostream& os, const SourceFile& sf);

private:
	std::string name;
	std::string status;
	std::string creator;
	std::string reviewer;

};