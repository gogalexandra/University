#pragma once
#include "Repository.h"
#include "Adoptions.h"

using namespace std;

class Service {

private:
	Repository repo;
	Adoptions adopt;

public:
	explicit Service(Repository r) : repo{ move(r) } {}

	STLVector getRepo() const { return repo.getDogs(); }

	STLVector getAdoptions() const { return adopt.getAdoptionsList(); }

	bool addDogToRepo(const string& breed, const string& name, int age, const string& photography);

	bool deleteDogFromRepo(string name);

	bool updateDogFromRepo(const string& breed, const string& name, int age, const string& photography);

	void adoptDogToRepo(const string& breed, const string& name, int age, const string& photography);

	STLVector filterDogsFromRepo(string given_breed, int given_age);
};
