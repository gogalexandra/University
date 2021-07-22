
#include <exception>
#include "ListIterator.h"
#include "IteratedList.h"

using namespace std;

IteratedList::IteratedList() {

	this->cap = 10;
	this->nodes = new DLLANode[this->cap];
	this->head = -1;
	this->tail = -1;
	for (int i = 1; i < this->cap - 1; i++) {
		this->nodes[i].setPrev(i - 1);
		this->nodes[i].setNext(i + 1);
	}

	// setting the links for the first position
	this->nodes[0].setPrev(-1);
	this->nodes[0].setNext(1);

	// setting the links for the last position
	this->nodes[this->cap - 1].setPrev(this->cap - 2);
	this->nodes[this->cap - 1].setNext(-1);

	// setting first empty to be the first element

	this->firstEmpty = 0;
	this->nrElems = 0;
}

int IteratedList::size() const {
	return this->nrElems;
}

bool IteratedList::isEmpty() const {
	if (this->size() == 0)
		return true;
	return false;
}

ListIterator IteratedList::first() const {

	return ListIterator(*this);
}

TElem IteratedList::getElement(ListIterator pos) const {

	if (!pos.valid()) {
		throw exception();
	}
	return pos.getCurrent();
}

TElem IteratedList::remove(ListIterator& pos) {
	if (!pos.valid()) {
		throw exception();
	}
	TElem info = getElement(pos);
	int nodC = this->head;
	while (this->nodes[nodC].getInfo() != pos.getCurrent()) {
		nodC = this->nodes[nodC].getNext();
	}

	if (nodC == this->head)
		if (nodC == this->tail) {
			this->head = -1;
			this->tail = -1;
		}
		else {
			this->head = this->nodes[this->head].getNext();
			this->nodes[this->head].setPrev(-1);
		}
	else
		if (nodC == this->tail) {
			this->tail = this->nodes[this->tail].getNext();
			this->nodes[this->nodes[nodC].getPrev()].setNext(-1);
		}
		else {
			this->nodes[this->nodes[nodC].getPrev()].setNext(this->nodes[nodC].getNext());
			this->nodes[this->nodes[nodC].getNext()].setPrev(this->nodes[nodC].getPrev());
		}
	//this->firstEmpty = nodC;
	free(nodC);
	this->nrElems -= 1;
	return info;
}

ListIterator IteratedList::search(TElem e)const {
	int current = this->head;
	while (current != -1 && this->nodes[current].getInfo() != e) {
		current = this->nodes[current].getNext();
	}
	return ListIterator(*this);	
}

void IteratedList::resize()
{
	this->cap *= 2;
	DLLANode* newElements = new DLLANode[this->cap];
	for (int i = 0; i < this->nrElems; i++) {
		newElements[i] = this->nodes[i];
	}
	for (int i = this->nrElems + 1; i < this->cap - 1; i++) {
		newElements[i].setPrev(i - 1);
		newElements[i].setNext(i + 1);
	}
	newElements[this->nrElems].setPrev(-1);
	newElements[this->nrElems].setNext(this->nrElems + 1);

	newElements[this->cap - 1].setPrev(this->cap - 2);
	newElements[this->cap - 1].setNext(-1);

	delete[] this->nodes;
	this->nodes = newElements;
	this->firstEmpty = this->nrElems;
}

TElem IteratedList::setElement(ListIterator pos, TElem e) {

	if (!pos.valid()) {
		throw exception();
	}
	TElem old = this->getElement(pos);

	int current = this->head;
	while (this->nodes[current].getInfo() != old) {
		current = this->nodes[current].getNext();
	}
	this->nodes[current].setInfo(e);
	return old;
}

void IteratedList::addToPosition(ListIterator& pos, TElem e) {

	if (!pos.valid()) {
		throw exception();
	}
	int newElem = allocate();
	if (newElem == -1) {
		resize();
		newElem = allocate();
	}
	pos.next();
	int poz = pos.getCurrent();

	this->nodes[newElem].setInfo(e);
	int nodC = this->head;
	int pozC = 0;
	while (nodC != -1 && pozC < poz - 1) {
		nodC = this->nodes[nodC].getNext();
		pozC += 1;
	}
	if (nodC != -1) {
		int nodNext = this->nodes[nodC].getNext();
		this->nodes[newElem].setNext(nodNext);
		this->nodes[newElem].setPrev(nodC);
		this->nodes[nodC].setNext(newElem);
		if (nodNext == -1)
			this->tail = newElem;
		else
			this->nodes[nodNext].setPrev(newElem);
		this->nrElems += 1;
	}
	
}

void IteratedList::addToEnd(TElem e) {

	int newElem = allocate();
	if (newElem == -1) {
		resize();
		newElem = allocate();
	}

	this->nodes[newElem].setInfo(e);
	this->nodes[newElem].setNext(-1);
	this->nodes[newElem].setPrev(this->tail);
	

	if (this->head == -1) {
		this->head = newElem;
		this->tail = newElem;
	}
	else {
		this->nodes[this->tail].setNext(newElem);
		this->tail = newElem;
	}
	this->nrElems += 1;
}

void IteratedList::addToBeginning(TElem e)
{
	int newElem = allocate();
	if (newElem == -1) {
		resize();
		newElem = allocate();
	}
	this->nodes[newElem].setInfo(e);
	this->nodes[newElem].setNext(this->head);
	this->nodes[newElem].setPrev(-1);
	if (this->nrElems == 0) {
		this->head = newElem;
		this->tail = newElem;
	}
	else {
		this->nodes[this->head].setPrev(newElem);
		this->head = newElem;
	}
	
	this->nrElems += 1;
}


IteratedList::~IteratedList() {
	delete[] this->nodes;
}

int IteratedList::allocate()
{
	int newElem = this->firstEmpty;
	if (newElem != -1) {
		this->firstEmpty = this->nodes[this->firstEmpty].getNext();
		if (this->firstEmpty != -1)
			this->nodes[this->firstEmpty].setPrev(-1);
		this->nodes[newElem].setNext(-1);
		this->nodes[newElem].setPrev(-1);
	}
	return newElem;
}

void IteratedList::free(int pos)
{
	this->nodes[pos].setNext(this->firstEmpty);
	this->nodes[pos].setPrev(-1);
	if (this->firstEmpty != -1)
		this->nodes[this->firstEmpty].setPrev(pos);
	this->firstEmpty = pos;
}

//For DLLANode

DLLANode::DLLANode()
{
	this->prev = -1;
	this->next = -1;
	//this->info = NULL_TELEM;
}

DLLANode::~DLLANode()
{
}

void DLLANode::setInfo(TElem e)
{
	this->info = e;
}

void DLLANode::setPrev(int p)
{
	this->prev = p;
}

void DLLANode::setNext(int n)
{
	this->next = n;
}
