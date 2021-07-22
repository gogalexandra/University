#pragma once
//DO NOT INCLUDE BAGITERATOR


//DO NOT CHANGE THIS PART
#define NULL_TELEM -111111;
typedef int TElem;
typedef int TKey;
//typedef int(*HashFunction)(TElem);
class BagIterator;

class Node {
private:
	TKey key;
	Node* next;

public:
	Node() {};
	~Node() {};
	void setKey(TKey k);
	void setNext(Node* p);
	TKey getKey();
	Node* getNext();
};

class Bag {

private:

	Node** t;
	int m;
	int nrElems;
	int h(TElem e) ;
	
	friend class BagIterator;
public:
	//constructor
	Bag();

	void resize();

	void rehash();

	//adds an element to the bag
	void add(TElem e);

	//removes one occurence of an element from a bag
	//returns true if an element was removed, false otherwise (if e was not part of the bag)
	bool remove(TElem e);

	//checks if an element appearch is the bag
	bool search(TElem e);

	//returns the number of occurrences for an element in the bag
	int nrOccurrences(TElem e) const;

	//returns the number of elements from the bag
	int size() const;

	//returns an iterator for this bag
	BagIterator iterator();

	//checks if the bag is empty
	bool isEmpty() const;

	//destructor
	~Bag();
};