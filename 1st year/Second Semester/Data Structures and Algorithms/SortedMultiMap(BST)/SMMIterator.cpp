#include "SMMIterator.h"
#include "SortedMultiMap.h"

SMMIterator::SMMIterator(const SortedMultiMap& d) : map(d){
	BSTNode* node = this->map.root;
	while (node != nullptr) {
		this->s.push(node);
		node = node->getLeft();
	}
	if (!this->s.empty())
		this->current = this->s.top();
	else
		this->current = nullptr;
}

void SMMIterator::first(){
	/*if (this->s.empty()) {
		BSTNode* node = this->map.root;
		while (node != nullptr) {
			this->s.push(node);
			node = node->getLeft();
		}
		if (!this->s.empty())
			this->current = this->s.top();
		else
			this->current = nullptr;
	}*/
	stack<BSTNode*> s2;
	this->s = s2;
	BSTNode* node = this->map.root;
	while (node != nullptr) {
		this->s.push(node);
		node = node->getLeft();
	}
	if (!this->s.empty())
		this->current = this->s.top();
	else
		this->current = nullptr;
}

void SMMIterator::next(){
	if (!this->valid())
		throw exception();
	BSTNode* node = this->s.top();
	this->s.pop();
	if (node->getRight() != nullptr) {
		node = node->getRight();
		while (node != nullptr){
			this->s.push(node);
			node = node->getLeft();
		}
	}
	if (! this->s.empty())
		this->current = this->s.top();
	else
		this->current = nullptr;
}

bool SMMIterator::valid() const{
	if (this->current == nullptr)
		return false;
	else
		return true;
}

TElem SMMIterator::getCurrent() const{
	if (!this->valid())
		throw exception();
	else
		return this->current->getInfo();
}


