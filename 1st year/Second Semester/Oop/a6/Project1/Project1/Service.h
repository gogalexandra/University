#pragma once
#include "TextRepo.h"
#include "TextAdoption.h"
#include "Csv2.h"
#include "Html2.h"

using namespace std;

class Service {

private:
	TextRepo* repo;
	TextAdoptions* adopt;

public:

	//Service() {};

	Service(TextRepo* r, TextAdoptions* a);

	Service(const Service& s);

	void setRepo(const std::string& type);

	TextRepo* getRepo2() const { return repo; }

	STLVector getRepo() const { return repo->getDogs(); }

	STLVector getAdoptions() const { return adopt->getAdoptionsList(); }

	bool addDogToRepo(const string& breed, const string& name, int age, const string& photography);

	bool deleteDogFromRepo(string name);
	
	void deletebyindex(int indx);

	bool updateDogFromRepo(const string& breed, const string& name, int age, const string& photography);

	void adoptDogToRepo(const string& breed, const string& name, int age, const string& photography);

	STLVector filterDogsFromRepo(string given_breed, int given_age);

	Service& operator=(const Service& s);

	friend ostream& operator<<(ostream& os, const Service& s);

	void openFile();
};

inline ostream& operator<<(ostream& os, const Service& s) {

	vector<Dog> all = s.repo->getDogs().getAll();
	for (auto& it : all) {
		os << "Breed: " << it.getBreed() << " ";
		os << "Name: " << it.getName() << " ";
		os << "Age: " << it.getAge() << " ";
		os << "Photo: " << it.getPhotograph() << "\n";
	}
	return os;
}