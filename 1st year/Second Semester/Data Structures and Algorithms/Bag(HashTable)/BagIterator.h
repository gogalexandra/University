#include "Bag.h"

class BagIterator
{
	friend class Bag;
	
private:
	Bag& bag;
	Node* currentNode;
	int currentPos;

	BagIterator(Bag& c);
public:
	void first();
	void next();
	TElem getCurrent() const;
	bool valid() const;
	TElem remove();
};
