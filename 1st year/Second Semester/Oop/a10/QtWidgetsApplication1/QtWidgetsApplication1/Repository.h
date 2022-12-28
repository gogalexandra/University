#pragma once
#include "STLVector.h"
#include "Entity.h"

using namespace std;

class Repository
{
private:
	STLVector dogs;

public:

	Repository() = default;

	bool addDog(Dog& d);

	bool removeDog(string name);

	int findPosition(string name);

	bool updateDog(Dog& d);

	STLVector getDogs() const { return dogs; }

	STLVector filterDog(string given_breed, int given_age);

	virtual void save() {};

	friend ostream& operator<<(ostream& os, const Repository& r);
};

inline ostream& operator<<(ostream& os, const Repository& r) {
	vector<Dog> all = r.getDogs().getAll();
	for (auto & it:all) {
		os << it << "\n";
	}
	return os;
}