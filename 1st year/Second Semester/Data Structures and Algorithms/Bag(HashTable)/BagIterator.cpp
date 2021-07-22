#include <exception>
#include "BagIterator.h"
#include "Bag.h"

using namespace std;


BagIterator::BagIterator(Bag& c): bag(c)
{
	this->currentPos = 0;
	while (this->currentPos < c.m && c.t[this->currentPos] == nullptr) {
		this->currentPos += 1;
	}
	if (this->currentPos < c.m)
		this->currentNode = c.t[this->currentPos];
	else{
		this->currentNode = nullptr;
	}
}
//Best case: Theta(1), Worst case: Theta(n) => Total Complexity: O(n)

void BagIterator::first() {

	this->currentPos = 0;
	while (this->currentPos < this->bag.m && this->bag.t[this->currentPos] == nullptr) {
		this->currentPos += 1;
	}
	if (this->currentPos < this->bag.m)
		this->currentNode = this->bag.t[this->currentPos];
	else
	{
		this->currentNode = nullptr;
	}
}
//Best case: Theta(1), Worst case: Theta(n) => Total Complexity: O(n)

void BagIterator::next() {

	if (!this->valid())
		throw exception();
	else {
		if (this->currentNode->getNext() != nullptr)
			this->currentNode = this->currentNode->getNext();
		else {
			this->currentPos += 1;
			while (this->currentPos < this->bag.m && this->bag.t[this->currentPos] == nullptr) {
				this->currentPos += 1;
			}
			if (this->currentPos < this->bag.m)
				this->currentNode = this->bag.t[this->currentPos];
			else
			{
				this->currentNode = nullptr;
			}
		}
	}
}
//Best case: Theta(1), Worst case: Theta(n) => Total Complexity: O(n)


bool BagIterator::valid() const {

	if (this->currentPos == bag.m)
		return false;
	else {
		return true;
	}
}
//Theta(1)


TElem BagIterator::remove()
{
	TElem old = this->getCurrent();

	if (!this->valid())
		throw exception();
	int pos = this->bag.h(old);
	Node* currentNode = this->bag.t[pos];
	Node* prev = nullptr;
	while (currentNode != nullptr && currentNode->getKey() != old) {
		prev = currentNode;
		currentNode = currentNode->getNext();

	}
	if (currentNode != nullptr && prev == nullptr)
		this->bag.t[pos] = this->bag.t[pos]->getNext();
	else
		if (currentNode != nullptr) {
			prev->setNext(currentNode->getNext());
			currentNode->setNext(nullptr);
		}
	this->bag.nrElems -= 1;
	this->next();
	delete currentNode;

	return old;
}
//Best case: Theta(1), Worst case: Theta(n) => Total Complexity: O(n)



TElem BagIterator::getCurrent() const
{
	if (! this->valid())
		throw exception();
	else
	{
		return this->currentNode->getKey();
	}
}
//Theta(1)
