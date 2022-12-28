#include "DynamicVector.h"


DynamicVector::DynamicVector() {
	/// <summary>
	/// Initialize the dynamic vector
	/// </summary>
	this->nrElements = 0;
	this->capacity = 10;
	this->elements = new TElem[capacity];
}

DynamicVector::DynamicVector(const DynamicVector& v) {
	/// <summary>
	/// 
	/// </summary>
	/// <param name="v"></param>
	this->nrElements = v.nrElements;
	this->capacity = v.capacity;
	this->elements = new TElem[this->capacity];
	for (int i = 0; i < this->nrElements; i++)
		this->elements[i] = v.elements[i];
}

DynamicVector::~DynamicVector() {
	/// <summary>
	/// Destory the vector
	/// </summary>
	delete[] this->elements;
}

void DynamicVector::add(TElem& e) {
	/// <summary>
	/// Adds a new elem to vector(resize if necessary)
	/// </summary>
	/// <param name="e">e is the new elemenet</param>
	if (this->nrElements == this->capacity)
		this->resize();
	this->elements[this->nrElements] = e;
	this->nrElements++;
}

void DynamicVector::update(int position, const TElem& e) {
	/// <summary>
	/// Element from position pos is getting the new info stored as a new object 
	/// </summary>
	/// <param name="position">position of the object that needs to be changed</param>
	/// <param name="e">e is an object having new info </param>
	this->elements[position] = e;
}


void DynamicVector::remove(int position) {
	/// <summary>
	/// from position pos every element is moved to the left(overwrittes the elem that needs to be deleted)
	/// </summary>
	/// <param name="position">position from where the overwrites start</param>
	for (int i = position; i < this->nrElements - 1; i++)
		this->elements[i] = this->elements[i + 1];

	this->nrElements--;
}

void DynamicVector::resize() {
	/// <summary>
	/// Doubles up the capacity, creates a new array, moves the elements in the new array and destroys the old one
	/// </summary>
	this->capacity *= 2;

	TElem* new_elems = new TElem[this->capacity];
	for (int i = 0; i < this->nrElements; i++)
		new_elems[i] = this->elements[i];

	delete[] this->elements;
	this->elements = new_elems;
}

TElem* DynamicVector::getAll() const 
{
	/// <summary>
	/// Gets all elements
	/// </summary>
	/// <returns>a vector of all elemes</returns>
	return this->elements;
}

int DynamicVector::getNrElements() const
{
	/// <summary>
	/// Gets nr of elems
	/// </summary>
	/// <returns>the number of elems(not capacity)</returns>
	return this->nrElements;
}

int DynamicVector::getCapacity() const
{
	/// <summary>
	/// Gets the capacity
	/// </summary>
	/// <returns>the capacity(not actual nr of elements)</returns>
	return this->capacity;
}