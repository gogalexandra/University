#pragma once
#include "STLVector.h"
#include "Entity.h"

using namespace std;

class Adoptions {

protected:

	STLVector adoptions;

public:

	Adoptions() = default;

	Adoptions(const Adoptions& a);

	~Adoptions() {};

	void add(Dog& dog);

	int getNrAdoptions();

	STLVector getAdoptionsList() const { return adoptions; }

	virtual void save() {};

	friend ostream& operator<<(ostream& os, const Adoptions& r);

};

inline ostream& operator<<(ostream& os, const Adoptions& r) {
	vector<Dog> all = r.getAdoptionsList().getAll();
	for (auto& it : all) {
		os << it << "\n";
	}
	return os;
}