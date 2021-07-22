#pragma once
//DO NOT INCLUDE SMMITERATOR

//DO NOT CHANGE THIS PART
#include <vector>
#include <utility>
typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;
#define NULL_TVALUE -111111
#define NULL_TELEM pair<TKey, TValue>(-111111, -111111);
using namespace std;
class SMMIterator;
typedef bool(*Relation)(TKey, TKey);

class BSTNode {

private:
    TElem info;
    BSTNode* left;
    BSTNode* right;

public:
    BSTNode();
    ~BSTNode();

    //getters
    TElem getInfo() { return this->info; };
    BSTNode* getLeft() { return this->left; };
    BSTNode* getRight() { return this->right; };

    //setters
    void setInfo(TElem e);
    void setLeft(BSTNode* l);
    void setRight(BSTNode* r);
};




class SortedMultiMap {
	friend class SMMIterator;
    private:
        BSTNode* root;
        int nrElems;
        Relation r;

    public:

    // constructor
    SortedMultiMap(Relation r);

	//adds a new key value pair to the sorted multi map
    void add(TKey c, TValue v);

    BSTNode* insert_rec(BSTNode* node, TElem e);

	//returns the values belonging to a given key
    vector<TValue> search(TKey c) const;

    bool searchPair(TKey c, TValue v) const;

	//removes a key value pair from the sorted multimap
	//returns true if the pair was removed (it was part of the multimap), false if nothing is removed
    bool remove(TKey c, TValue v);

    BSTNode* delete_rec(BSTNode* node, TKey c, TValue v);

    BSTNode* minValue(BSTNode* node);

    BSTNode* maxValue(BSTNode* node);

    //returns the number of key-value pairs from the sorted multimap
    int size() const;

    //verifies if the sorted multi map is empty
    bool isEmpty() const;

    int getKeyRange();

    // returns an iterator for the sorted multimap. The iterator will returns the pairs as required by the relation (given to the constructor)	
    SMMIterator iterator() const;

    // destructor
    ~SortedMultiMap();
};
