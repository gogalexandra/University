#include "SortedSet.h"
#include "SortedSetIterator.h"
#include <cstdlib>

SortedSet::SortedSet(Relation r) {

	this->r = r;
	this->capacity = 1;
	this->nrElements = 0;
	this->elements = new TComp[this->capacity];
}
//Theta(1)

bool SortedSet::add(TComp elem) {

	if (search(elem)){
		return false;
	}

	if (this->nrElements == this->capacity) {
		this->capacity *= 2;
		TComp* newElements = new TComp[this->capacity];
		for (int i = 0; i < this->nrElements; i++) {
			newElements[i] = this->elements[i];
		}
		delete[] this->elements;
		this->elements = newElements;
	}
	int pos = return_position(this->elements, elem, 0, this->nrElements);
	this->nrElements++;

	for (int i = this->nrElements -1 ; i > pos; i--) {
		this->elements[i] = this->elements[i - 1];
	}
	this->elements[pos] = elem;

	return true;
}
//BestCase = Theta(1), WorstCase = Theta(n) => TotalComplexity O(n)


bool SortedSet::remove(TComp elem) {

	if (search(elem) == false) {
		return false;
	}
	else
	{
		int i = return_position(this->elements, elem, 0, this->nrElements) - 1;
		for (i; i < this->nrElements - 1; i++) {
			this->elements[i] = this->elements[i+1];
		}
		this->nrElements --;
		return true;
	}
}
//BestCase = Theta(1), WorstCase = Theta(n) => TotalComplexity O(n)


bool SortedSet::search(TComp elem) const {

	int i = 0;
	while (i < this->nrElements) {
		if (this->elements[i] == elem) {
			return true;
		}
		i ++;
	}
	return false;
}
//BestCase = Theta(1), WorstCase = Theta(n) => TotalComplexity O(n)


int SortedSet::size() const {

	return this->nrElements;
}
//Theta(1)


bool SortedSet::isEmpty() const {

	if (this->nrElements == 0) {
		return true;
	}
	else
	{
		return false;
	}
}
//Theta(1)

SortedSetIterator SortedSet::iterator() const {
	return SortedSetIterator(*this);
}
//Theta(1)


SortedSet::~SortedSet() {

	delete[] this->elements;
}
//Theta(1)


int SortedSet::return_position(TComp* arr,TComp elem, int l, int r)
{
	if (r <= l)
		return r;
	
	int mid = (r + l) / 2 ;
	if (this->elements[mid] == elem)	
		return mid +1 ;
	if (this->r(this->elements[mid], elem))
		return return_position(arr, elem, mid + 1, r);
	return return_position(arr, elem, l, mid);
	
}
//BestCase = Theta(1), WorstCase = Theta(log n) => TotalComplexity O(log n)

int SortedSet::getRange() const
{
	if (this->nrElements == 0) {
		return -1;
	}
	else {
		TComp val1 = this->elements[0];
		TComp val2 = this->elements[this->nrElements - 1];
		if (this->r(val1, val2) == true) {
			return val2 - val1;
		}
		else {
			return val1 - val2;
		}

		/*TComp val1 = 999999;
		TComp val2 = -999999;
		for (int i = 0; i < this->nrElements; i++) {
			if (this->elements[i] < val1) {
				val1 = this->elements[i];
			}
			else 
				if (this->elements[i] > val2) {
					val2 = this->elements[i];
				}
		}
		return val2 - val1;*/
	}
}



