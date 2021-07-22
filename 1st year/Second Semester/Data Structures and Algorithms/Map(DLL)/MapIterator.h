#pragma once
#include "Map.h"
class MapIterator
{
	//DO NOT CHANGE THIS PART
	friend class Map;
private:
	const Map& map;
	DLLNode* curent;
	//TODO - Representation

	MapIterator(Map& m);
public:
	void first();
	void next();
	TElem getCurrent();
	bool valid() const;
	TElem remove();
};


