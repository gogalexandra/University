#pragma once
//DO NOT INCLUDE SETITERATOR

//DO NOT CHANGE THIS PART
typedef int TElem;
typedef TElem TComp;
typedef bool(*Relation)(TComp, TComp);
constexpr auto NULL_TELEM = -11111;
class SortedSetIterator;


class SortedSet {
	friend class SortedSetIterator;
	private:
		//TODO - Representation
		TComp* elements; 
		int nrElements;
		int capacity;
		Relation r;
		 
	public:
		//constructor
		SortedSet(Relation r);

		//adds an element to the sorted set
		//if the element was added, the operation returns true, otherwise (if the element was already in the set) 
		//it returns false
		bool add(TComp e);

	
		//removes an element from the sorted set
		//if the element was removed, it returns true, otherwise false
		bool remove(TComp e);

		//checks if an element is in the sorted set
		bool search(TComp elem) const;


		//returns the number of elements from the sorted set
		int size() const;

		//checks if the sorted set is empty
		bool isEmpty() const;

		//returns an iterator for the sorted set
		SortedSetIterator iterator() const;

		// destructor
		~SortedSet();

		//swaps two elements
		//void swap(TComp* elem1, TComp* elem2);

		int return_position(TComp* arr, TComp elem, int l, int r);

		int getRange() const;

};
