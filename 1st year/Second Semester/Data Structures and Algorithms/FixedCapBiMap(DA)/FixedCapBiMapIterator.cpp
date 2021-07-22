#include "FixedCapBiMap.h"
#include "FixedCapBiMapIterator.h"
#include <exception>
using namespace std;


FixedCapBiMapIterator::FixedCapBiMapIterator(const FixedCapBiMap& d) : map(d)
{
	//TODO - Implementation
	this->current = 0;
}


void FixedCapBiMapIterator::first() {
	//TODO - Implementation
	this->current = 0;
}


void FixedCapBiMapIterator::next() {
	//TODO - Implementation
	if (this->current >= this->map.nrPairs) {
		throw exception();
	}
	else {
		this->current++;
	}
}


TElem FixedCapBiMapIterator::getCurrent(){
	//TODO - Implementation
	if (this->current > this->map.nrPairs) {
		throw exception();
	}
	return this->map.elements[this->current];
}


bool FixedCapBiMapIterator::valid() const {
	//TODO - Implementation
	if (this->current < this->map.nrPairs) {
		return true;
	}
	else
	{
		return false;
	}
	
}



