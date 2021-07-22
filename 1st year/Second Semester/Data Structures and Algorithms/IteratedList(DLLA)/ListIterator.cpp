#include "ListIterator.h"
#include "IteratedList.h"
#include <exception>

using namespace std;

ListIterator::ListIterator(const IteratedList& list) : list(list) {

	this->current = list.head;
}

void ListIterator::first() {

	this->current = list.head;
}

void ListIterator::next() {

	if (this->current == -1) {
		throw exception();
	}
	this->current = this->list.nodes[this->current].getNext();

}

bool ListIterator::valid() const {

	if (this->current == -1) {
		return false;
	}
	return  true;
}

TElem ListIterator::getCurrent() const {

	if (this->current == -1) {
		throw exception();
	}
	return this->list.nodes[this->current].getInfo();
}



