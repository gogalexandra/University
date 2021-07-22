#pragma once

#include <stack>
#include "SortedMultiMap.h"


class SMMIterator{
	friend class SortedMultiMap;
private:
	const SortedMultiMap& map;
	SMMIterator(const SortedMultiMap& map);
	BSTNode* current;
	stack<BSTNode*> s;

public:
	void first();
	void next();
	bool valid() const;
   	TElem getCurrent() const;
};

