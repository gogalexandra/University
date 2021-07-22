#include "Bag.h"
#include "BagIterator.h"
#include <exception>
#include <iostream>
#include <cmath>

using namespace std;

int Bag::h(TElem e)
{
	return abs(e % this->m);
}

Bag::Bag() {

	this->m = 10;
	this->nrElems = 0;
	this->t = new Node*[this->m];
	int i = 0;
	for (i; i < this->m; i++) {
		this->t[i] = nullptr;
	}
}
//Theta(n)

void Bag::resize()
{
	this->m *= 2;
	Node** newElems = new Node*[this->m];
	int i = 0;
	for (i; i < this->m; i++) {
		newElems[i] = nullptr;
	}

	for (i = 0; i < this->m / 2; i++) {
		Node* currentNode = this->t[i];
		
		while (currentNode != nullptr) {
			int pos = this->h(currentNode->getKey());
			Node* newnode = new Node;
			newnode->setNext(nullptr);
			newnode->setKey(currentNode->getKey());
			if (newElems[pos] == nullptr)
				newElems[pos] = newnode;
			else {
				newnode->setNext(newElems[pos]);
				newElems[pos] = newnode;
			}
			currentNode = currentNode->getNext();
		}
	}
	delete[] this->t;
	this->t = newElems;
}
//Theta(n^2);

void Bag::rehash()
{
	Node** newElems = new Node*[this->m];
	int i = 0;
	for (i; i < this->m; i++) {
		newElems[i] = nullptr;
	}
	for (i = 0 ; i < this->m / 2; i++) {
		Node* currentNode = this->t[i];
		while (currentNode != nullptr) {
			int pos = this->h(currentNode->getKey());
			Node* newnode = new Node;
			newnode->setNext(nullptr);
			newnode->setKey(currentNode->getKey());
			if (newElems[pos] == nullptr)
				newElems[pos] = newnode;
			else {
				newnode->setNext(newElems[pos]);
				newElems[pos] = newnode;
			}
			currentNode = currentNode->getNext();
		}
	}
	delete[] this->t;
	this->t = newElems;
}


void Bag::add(TElem elem) {
	
	int pos = this->h(elem);
	
	Node* newnode = new Node;
	newnode->setNext(nullptr);
	newnode->setKey(elem);
	if (this->t[pos] == nullptr) 
		this->t[pos] = newnode;
	else {
		newnode->setNext(this->t[pos]);
		this->t[pos] = newnode;
	}
	this->nrElems += 1;
	if (0.7 <= this->nrElems / this->m)
		this->resize();
}
//Theta(1)


bool Bag::remove(TElem elem) {

	if (!search(elem))
		return false;
	int pos = this->h(elem);
	Node* currentNode = this->t[pos];
	Node* prev = nullptr;
	while (currentNode != nullptr && currentNode->getKey() != elem) {
		prev = currentNode;
		currentNode = currentNode->getNext();

	}
	if (currentNode != nullptr && prev == nullptr)
		this->t[pos] = this->t[pos]->getNext();
	else
		if (currentNode != nullptr) {
			prev->setNext(currentNode->getNext());
			currentNode->setNext(nullptr);
		}
	delete currentNode;
	this->nrElems -= 1;
	return true;
}
//Best case: Theta(1), Worst case: Theta(n) => Total Complexity: O(n)


bool Bag::search(TElem elem){

	int pos = this->h(elem);
	Node* currentNode = this->t[pos];
	while (currentNode != nullptr and currentNode->getKey() != elem) {
		currentNode = currentNode->getNext();
	}
	if (currentNode != nullptr)
		return true;
	return false;
}
//Best case: Theta(1), Worst case: Theta(n) => Total Complexity: O(n)

int Bag::nrOccurrences(TElem elem) const{
	
	int occur = 0;
	int i = 0;
	for (i; i < this->m; i++) {
		Node* currentNode = this->t[i];
		while (currentNode != nullptr) {
			if (currentNode->getKey() == elem)
				occur += 1;
			currentNode = currentNode->getNext();
		}
	}
	return occur;
}
//Theta(n^2)


int Bag::size() const {

	return this->nrElems;
}
//Theta(1)


bool Bag::isEmpty() const {
	
	if (this->nrElems == 0)
		return true;
	return false;
}
//Theta(1)

BagIterator Bag::iterator(){
	return BagIterator(*this);
}
//Theta(1)

Bag::~Bag() {
	int i = 0;
	for (i; i < this->m; i++) {
		Node* currentNode = this->t[i];
		while (this->t[i] != nullptr) {
			currentNode = this->t[i]->getNext();
			delete this->t[i];
			this->t[i] = currentNode;
		}
	}
	delete[] this->t;
}

void Node::setKey(TKey k)
{
	this->key = k;
}
//Theta(1)

void Node::setNext(Node* p)
{
	this->next = p;
}
//Theta(1)

TKey Node::getKey()
{
	return this->key;
}
//Theta(1)

Node* Node::getNext()
{
	return this->next;
}
//Theta(1)
