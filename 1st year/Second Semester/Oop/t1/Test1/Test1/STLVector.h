#pragma once
#include <vector>
#include "Entity.h"

typedef Gene elem;


class STLVector {
public:

	STLVector() = default;
	void add(elem& e);

	vector<Gene> getAll();
	int getNrElements();


private:
	vector<Gene> elements;
};

