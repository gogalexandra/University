#include "FixedCapBiMap.h"
#include "FixedCapBiMapIterator.h"
#include <exception>

using namespace std;

FixedCapBiMap::FixedCapBiMap(int capacity) {
	//TODO - Implementation
	if (capacity <= 0) {
		throw exception();
	}
	this->capacity = capacity;
	this->nrPairs = 0;
	this->elements = new TElem[capacity];
}

FixedCapBiMap::~FixedCapBiMap() {
	//TODO - Implementation
	delete[] this->elements;
}

bool FixedCapBiMap::add(TKey c, TValue v){
	//TODO - Implementation
	if (this->capacity == this->nrPairs) {
		throw exception();
	}
	int index = 0, count = 0;
	while (count < 2 && index < this->nrPairs) {
		if (this->elements[index].first == c) {
			count++;
		}
		index++;
	}
	if (count == 2) {
		return false;
	}
	else
	{
		this->elements[this->nrPairs].first = c;
		this->elements[this->nrPairs].second = v;
		this->nrPairs++;
		return true;
	}
}

ValuePair FixedCapBiMap::removeKey(TKey k) const {
	int index = 0, count = 0, index_1 = -1, index_2 = -1;
	TValue v1 = NULL_TVALUE, v2= NULL_TVALUE;

	while (index < this->nrPairs) {
		if (this->elements[index].first == k) {
			count++;
			if (count == 1) {
				v1 = this->elements[index].second;
				index_1 = index;
			}
			else
			{
				v2 = this->elements[index].second;
				index_2 = index;
			}
		}
		index++;
		
	}
	if (index_1 != 0) {
		this->elements[index_1] = this->elements[this->nrPairs - 1];
		this->nrPairs--;
	}
	if (index_2 != 0) {
		this->elements[index_2] = this->elements[this->nrPairs - 1];
		this->nrPairs--;
	}
	ValuePair vp(v1, v2);
	return vp;
}

ValuePair FixedCapBiMap::search(TKey c) const{
	//TODO - Implementation
	return std::pair<TValue, TValue>(NULL_TVALUE, NULL_TVALUE);
}

bool FixedCapBiMap::remove(TKey c, TValue v){
	//TODO - Implementation
	return false;
}


int FixedCapBiMap::size() const {
	//TODO - Implementation
	return this->nrPairs;
}

bool FixedCapBiMap::isEmpty() const{
	//TODO - Implementation
	return false;
}

bool FixedCapBiMap::isFull() const {
	//TODO - Implementation
	return false;
}

FixedCapBiMapIterator FixedCapBiMap::iterator() const {
	return FixedCapBiMapIterator(*this);
}



