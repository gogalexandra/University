#pragma once
#include "RepoProgrammer.h"
#include "RepoSourceFile.h"
#include "Observer.h"

class Service : public Observable
{
public:
	Service();
	Service(RepoProgrammer* rp, RepoSourceFile* rs);
	Service(const Service& s);
	~Service();
	
	void add_sourceFile(std::string name, std::string status, std::string creator, std::string reviewer);
	void remove_sourceFile(int index);
	void update_sourceFile(std::string name, std::string status, std::string creator, std::string reviewer);
	int get_index_sourceFile(const std::string& name);
	
	
	void readProgrammers();
	void readSourceFiles();
	RepoProgrammer* getRepoProgrammer();
	RepoSourceFile* getRepoSourceFile();

private:
	RepoProgrammer* rp;
	RepoSourceFile* rs;
};