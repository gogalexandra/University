#include "Map.h"
#include "MapIterator.h"
#include <iostream>

Map::Map() {
	this->head = nullptr;
	this->tail = nullptr;
}
//Theta(1)

Map::~Map() {
}


TValue Map::add(TKey c, TValue v) {
	DLLNode* p = search_node(c);
	if (p != nullptr){
		TValue old = p->getElem().second;
		p->setTValue(v);
		return old;
	}
	else {
		DLLNode* newnode = new DLLNode;
		TElem e{ c, v };
		newnode->setElem(e);
		newnode->setPrev(this->tail);
		newnode->setNext(nullptr);
		if (this->head == nullptr) {
			this->head = newnode;
			this->tail = newnode;
		}
		else {
			this->tail->setNext(newnode);
			this->tail = newnode;
		}
	}
	return NULL_TVALUE;
}
//Theta(1)


TValue Map::search(TKey c){

	DLLNode* p = this->head;
	while (p != nullptr && p->getElem().first != c) {
		p = p->getNext();
	}
	if (p != nullptr)
		return p->getElem().second;
	else
		return NULL_TVALUE;
}
//Best case: Theta(1), Worst case: Theta(n) => Total Complexity: O(n) 

DLLNode* Map::search_node(TKey c)
{
	DLLNode* p = this->head;
	while (p != nullptr && p->getElem().first != c) {
		p = p->getNext();
	}
	if (p != nullptr)
		return p;
	else
		return nullptr;
}
//Best case: Theta(1), Worst case: Theta(n) => Total Complexity: O(n) 

TValue Map::remove(TKey c){

	DLLNode* p = search_node(c);
	if (p == nullptr)
		return NULL_TVALUE;
	else {
		if (p == this->head)
			if (p == this->tail) {
				this->head = nullptr;
				this->tail = nullptr;
			}
			else {
				this->head = this->head->getNext();
				this->head->setPrev(nullptr);
			}
		else
			if (p == this->tail) {
				this->tail = this->tail->getPrev();
				this->tail->setNext(nullptr);
			}
			else {
				p->getNext()->setPrev(p->getPrev());
				p->getPrev()->setNext(p->getNext());
			}
		return p->getElem().second;
	}
}
//Best case: Theta(1), Worst case: Theta(n) => Total Complexity: O(n) 

int Map::size() const {

	int dim = 0;
	DLLNode* p = this->head;
	while (p != nullptr) {
		dim++;
		p = p->getNext();
	}
	return dim;
}
//O(n)

bool Map::isEmpty() const{
	if (this->head == nullptr && this->tail == nullptr)
		return true;
	else
		return false;
}
//Theta (1)


MapIterator Map::iterator(){

	return MapIterator(*this);
}
//Theta(1)

DLLNode::DLLNode()
{
	this->next = nullptr;
	this->prev = nullptr;
}
//Theta(1)

DLLNode::~DLLNode()
{
}

void DLLNode::setElem(TElem m)
{
	this->elem = m;
}
//Theta(1)

void DLLNode::setPrev(DLLNode* p)
{
	this->prev = p;
}
//Theta(1)

void DLLNode::setNext(DLLNode* p)
{
	this->next = p;
}
//Theta(1)

void DLLNode::setTValue(TValue v)
{
	this->elem.second = v;
}
//Theta(1)

TElem DLLNode::getElem()
{
	return this->elem;
}
//Theta(1)

DLLNode* DLLNode::getPrev()
{
	return this->prev;
}
//Theta(1)

DLLNode* DLLNode::getNext()
{
	return this->next;
}
//Theta(1)
