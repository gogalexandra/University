#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <iostream>
#include <vector>
#include <exception>
using namespace std;

SortedMultiMap::SortedMultiMap(Relation r) {
	this->root = nullptr;
	this->nrElems = 0;
	this->r = r;
}
//Theta(1)

void SortedMultiMap::add(TKey c, TValue v) {
	TElem e;
	e.first = c;
	e.second = v;
	this->root = this->insert_rec(this->root, e);
	this->nrElems += 1;
}
//best case: Theta (1), Worst case:O(n) -> Avarage Case: O(n) Takes complexity of insert_rec

BSTNode* SortedMultiMap::insert_rec(BSTNode* node, TElem e)
{
	if (node == nullptr) {
		BSTNode* aux = new BSTNode;
		aux->setInfo(e);
		return aux;
	}
	else
		if (! this->r(node->getInfo().first, e.first))
			node->setLeft(this->insert_rec(node->getLeft(), e));
		else
			node->setRight(this->insert_rec(node->getRight(), e));
	return node;
}
//best case: Theta (1), Worst case:O(n) -> Avarage Case: O(n)

vector<TValue> SortedMultiMap::search(TKey c) const {
	BSTNode* currentNode = this->root;
	vector<TValue> values;
	while (currentNode != nullptr) {
		if (currentNode->getInfo().first == c)
			values.push_back(currentNode->getInfo().second);
			
		if (this->r(currentNode->getInfo().first, c))
			currentNode = currentNode->getRight();
		else
			currentNode = currentNode->getLeft();
	}
	return values;
}
// O(n)

bool SortedMultiMap::searchPair(TKey c, TValue v) const
{
	BSTNode* currentNode = this->root;
	bool found = false;
	while (currentNode != nullptr && found == false) {
		if (currentNode->getInfo().first == c && currentNode->getInfo().second == v)
			found = true;

		if (this->r(currentNode->getInfo().first, c))
			currentNode = currentNode->getRight();
		else
			currentNode = currentNode->getLeft();
	}
	return found;
}
// O(n)

bool SortedMultiMap::remove(TKey c, TValue v) {
	if (!searchPair(c, v))
		return false;
	else {
		this->root = this->delete_rec(this->root, c, v);
		this->nrElems -= 1;
		return true;
	}
}
//Gets the time comp from delete_rec


BSTNode* SortedMultiMap::delete_rec(BSTNode* node, TKey c, TValue v)
{
	if (node == nullptr)
		return node;
	if (!this->r(c, node->getInfo().first))
		node->setLeft(this->delete_rec(node->getLeft(), c, v));
	else
		
		if (this->r(c, node->getInfo().first))
			node->setRight(this->delete_rec(node->getRight(), c, v));
		else {
			BSTNode* temp = new BSTNode;
			if (node->getRight() == nullptr && node->getLeft())
				return nullptr;
			else
				if (node->getLeft() == nullptr) {
					temp = node->getRight();
					free(this->root);
					return temp;
				}
				else
					if (node->getRight() == nullptr) {
						temp = node->getLeft();
						free(this->root);
						return temp;
					}
			temp = this->minValue(node->getRight());
			node->setInfo(temp->getInfo());
			node->setRight(this->delete_rec(node->getRight(), temp->getInfo().first, temp->getInfo().second));
		}
	return node;
}
//BestCase : theta(1); WorstCase: O(n): n the heigth of the tree


BSTNode* SortedMultiMap::minValue(BSTNode* node)
{
	BSTNode* current = node;
	while (current->getLeft() != nullptr) {
		current = current->getLeft();
	}
	return current;
}
//O(n);

BSTNode* SortedMultiMap::maxValue(BSTNode* node)
{
	BSTNode* current = node;
	while (current->getRight() != nullptr) {
		current = current->getRight();
	}
	return current;
}
//O(n);


int SortedMultiMap::size() const {
	return this->nrElems;
}
//Theta(1)

bool SortedMultiMap::isEmpty() const {
	if (this->nrElems == 0)
		return true;
	return false;
}
//Theta(1)


int SortedMultiMap::getKeyRange() {
	if (this->isEmpty())
		return -1;
	else {
		TKey min, max;
		TElem e;
		BSTNode* temp;
		temp = this->minValue(this->root);
		e = temp->getInfo();
		min = e.first;
		temp = this->maxValue(this->root);
		e = temp->getInfo();
		max = e.first;
		if (min > max)
			return min - max;
		else
			return max - min;
	}
}
//O(n)

SMMIterator SortedMultiMap::iterator() const {
	return SMMIterator(*this);
}

SortedMultiMap::~SortedMultiMap() {
}

BSTNode::BSTNode()
{
	this->info = NULL_TELEM;
	this->left = nullptr;
	this->right = nullptr;
}

BSTNode::~BSTNode()
{
}

void BSTNode::setInfo(TElem e)
{
	this->info = e;
}

void BSTNode::setLeft(BSTNode* l)
{
	this->left = l;
}

void BSTNode::setRight(BSTNode* r)
{
	this->right = r;
}
