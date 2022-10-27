#pragma once
#include "RepoWriter.h"
#include "RepoIdea.h"
#include "Observer.h"

class Service : public Observable
{
public:
	Service();
	Service(RepoWriter* w, RepoIdea* i);
	Service(const Service& s);
	~Service();
	
	void add_idea(std::string description, std::string status, std::string creator, int act);
	void remove_idea(int index);
	void update_idea(const std::string& description, const std::string& status, const std::string& creator, const int& act);
	int get_index_idea_service(const std::string& description, int act);
	void readIdeas();
	void readWriters();
	RepoWriter* getRepoWriter();
	RepoIdea* getRepoIdea();

private:
	RepoWriter* rw;
	RepoIdea* ri;
};

