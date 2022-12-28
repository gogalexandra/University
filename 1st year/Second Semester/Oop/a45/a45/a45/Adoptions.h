#pragma once
#include "STLVector.h"
#include "Entity.h"

class Adoptions {

private:

	STLVector adoptions;

public:

	Adoptions() = default;

	void add(Dog& dog);

	int getNrAdoptions();

	STLVector getAdoptionsList() const { return adoptions; }
};
