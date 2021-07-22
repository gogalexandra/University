#pragma once
#include <utility>
//DO NOT INCLUDE MAPITERATOR


//DO NOT CHANGE THIS PART
typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;
#define NULL_TVALUE -111111

class MapIterator;

class DLLNode {
private:
	TElem elem;
	DLLNode* prev;
	DLLNode* next;
public:
	DLLNode();
	~DLLNode();
	void setElem(TElem m);
	void setPrev(DLLNode* p);
	void setNext(DLLNode* p);
	void setTValue(TValue v);
	TElem getElem();
	DLLNode* getPrev();
	DLLNode* getNext();
};

class Map {
	friend class MapIterator;

	private:
		DLLNode* head;
		DLLNode* tail;


	public:

	// implicit constructor
	Map();

	// adds a pair (key,value) to the map
	//if the key already exists in the map, then the value associated to the key is replaced by the new value and the old value is returned
	//if the key does not exist, a new pair is added and the value null is returned

	TValue add(TKey c, TValue v);


	//searches for the key and returns the value associated with the key if the map contains the key or null: NULL otherwise
	TValue search(TKey c);

	//searches for the key and returns the node associated with the key if the map contains the key or null: NULL otherwise
	DLLNode* search_node(TKey c);

	//removes a key from the map and returns the value associated with the key if the key existed or null: NULL otherwise
	TValue remove(TKey c);

	//returns the number of pairs (key,value) from the map
	int size() const;

	//checks whether the map is empty or not
	bool isEmpty() const;

	//returns an iterator for the map
	MapIterator iterator();

	// destructor
	~Map();

};



