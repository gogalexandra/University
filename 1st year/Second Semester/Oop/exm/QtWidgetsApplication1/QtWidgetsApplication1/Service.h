#pragma once
#include "RepoEthnologists.h"
#include "RepoBuildings.h"
#include "Observer.h"

class Service : public Observable
{
public:
	Service();
	Service(RepoEthnologists* e, RepoBuildings* b);
	Service(const Service& s);
	~Service();

	//for ethnologists
	void readEthnologists();
	RepoEthnologists* getRepoEthnologists();

	//for buildings
	void readBuildings();
	RepoBuildings* getRepoBuildings();

	void add_building(int id, std::string description, std::string thematicArea, std::vector<std::string> location);
	void update_building(const int& id, const std::string& description, const std::string& thematicArea, const std::vector<std::string>& location);
	int get_index_building_service(const int& id);
	
	

private:
	RepoEthnologists* re;
	RepoBuildings* rb;
};