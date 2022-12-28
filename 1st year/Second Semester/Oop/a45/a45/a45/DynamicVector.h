#pragma once
#include "Entity.h"

typedef Dog TElem;

class DynamicVector {

private:
	TElem* elements;//vector of elements
	int nrElements; //actual number of elements
	int capacity; //maximum number = capacity= memory allocated

public:
	// default constructor for a DynamicVector
	explicit DynamicVector();

	// copy constructor for a DynamicVector
	DynamicVector(const DynamicVector& v);
	~DynamicVector();

	// assignment operator for a DynamicVector
	/*DynamicVector& operator=(const DynamicVector& v);*/

	// Adds an element to the current DynamicVector.
	void add(TElem& e);
	
	//Removes an element to the current DynamicVector
	void remove(int position);

	//Updates an element to the current DynamicVector
	void update(int position, const TElem& e);

	int getNrElements() const;

	int getCapacity() const;

	/*int findElemPosition(const TElem elem);*/
	TElem* getAll() const;

	//bool search(const TElem e);

private:
	void resize();
};