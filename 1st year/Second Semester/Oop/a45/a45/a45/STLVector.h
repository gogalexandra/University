#pragma once
#include <vector>
#include "Entity.h"

typedef Dog elem;


class STLVector {
public:

	STLVector() = default;
	void add(elem& e);
	void remove(int position);
	void update(int position, elem& e);

	vector<Dog> getAll();
	int getNrElements();


private:
	vector<Dog> elements;
};
