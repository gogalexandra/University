#include "Map.h"
#include "MapIterator.h"
#include <exception>
using namespace std;


MapIterator::MapIterator(Map& m) : map(m)
{
	this->curent = map.head;
}
//Theta(1)


void MapIterator::first() {
	
	this->curent = map.head;
}
//Theta(1)


void MapIterator::next() {
	
	if (this->curent == nullptr)
		throw exception();
	else
		this->curent = this->curent->getNext();
}
//Theta(1)


TElem MapIterator::getCurrent(){
	
	TElem e;
	if (this->curent == nullptr)
		throw exception();
	else {
		e = this->curent->getElem();
		return e;
	}
}
//Theta(1)


bool MapIterator::valid() const {
	
	if (this->curent == nullptr)
		return false;
	else
		return true;
}
//Theta(1)

TElem MapIterator::remove() {
	TElem old = this->curent->getElem();
	if (this->curent->getNext() == nullptr)
		throw exception();
	if (this->curent != map.tail) {
		this->curent->getNext()->setPrev(this->curent->getPrev());
		this->curent->getPrev()->setNext(this->curent->getNext());
		
	}
	else {
		this->curent = this->curent->getPrev();
		this->curent->setNext(nullptr);
		
	}
	this->next();
	
	return old;
}
//Theta(1);


