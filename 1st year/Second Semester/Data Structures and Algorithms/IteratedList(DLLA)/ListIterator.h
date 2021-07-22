#pragma once
#include "IteratedList.h"

class IteratedList;
typedef int TElem;

class ListIterator{
	friend class IteratedList;
private:
	int current;

	const IteratedList& list;
	ListIterator(const IteratedList& lista);
public:
	void first();
	void next();
	bool valid() const;
    TElem getCurrent() const;

};


