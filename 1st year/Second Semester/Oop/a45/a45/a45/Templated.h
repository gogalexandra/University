#pragma once

template <class T>

class DynamicVector {

private:
    // Resizes the current DynamicVector, multiplying its capacity by a given factor (real number).
    void resize();

    //TElement* elems;
    T* elements;
    int nrElements;
    int capacity;


public:
    // default constructor for a DynamicVector
    DynamicVector(int capacity = 10);

    // copy constructor for a DynamicVector
    DynamicVector(const DynamicVector& v);
    ~DynamicVector();

    // assignment operator for a DynamicVector
    DynamicVector& operator=(const DynamicVector& v);

    DynamicVector& operator+(T& element);

    // Adds an element to the current DynamicVector.
    //void add(TElement e);
    void add(T &e);
    void remove(int index);
    void update(int index, T &e);
    
    int getNrElements() const;
    int getCapacity() const;
    T* getAll() const;

    /*
    Overloading the subscript operator
    Input: pos - a valid position within the vector.
    Output: a reference to the element o position pos.
    */
    //TElement& operator[](int pos);
    T& operator[](int pos);

};

template <class T>
DynamicVector<T>::DynamicVector(int capacity) {
    this->nrElements = 0;
    this->capacity = capacity;
    this->elements = new T[capacity];
}


template <class T>
DynamicVector<T>::DynamicVector(const DynamicVector& v) {
    this->nrElements = v.nrElements;
    this->capacity = v.capacity;
    this->elements = new T[this->capacity];
    for (int i = 0; i < this->nrElements; i++)
        this->elements[i] = v.elements[i];
}


template <class T>
DynamicVector<T>::~DynamicVector() {
    delete[] this->elements;
}


template <class T>
DynamicVector<T>& DynamicVector<T>::operator=(const DynamicVector<T>& v) {
    if (this == &v)
        return *this;

    this->nrElementes = v.nrElements;
    this->capacity = v.capacity;

    delete[] this->elements;
    //this->elems = new TElement[this->capacity];
    this->elements = new T[this->capacity];
    for (int i = 0; i < this->nrElements; i++)
        this->elements[i] = v.elements[i];

    return *this;
}


template <class T>
DynamicVector<T>& DynamicVector<T>::operator+(T& element) {
    this->add(element);

    return *this;
}


template <class T>
DynamicVector<T>& operator+(T& element, DynamicVector<T>& dynamicVector) {
    dynamicVector.add(element);

    return dynamicVector;
}


template <class T>
//TElement& DynamicVector::operator[](int pos) {
T& DynamicVector<T>::operator[](int pos) {
    return this->elements[pos];
}


template <class T>
void DynamicVector<T>::resize() {
    this->capacity *= 2;

    //TElement* els = new TElement[this->capacity];
    T* new_elems = new T[this->capacity];
    for (int i = 0; i < this->nrElements; i++)
        new_elems[i] = this->elements[i];

    delete[] this->elements;
    elements = new_elems;
}


template <class T>
void DynamicVector<T>::add(T& e) {

    if (this->nrElements == this->capacity)
        this->resize();
    this->elements[this->nrElements] = e;
    this->nrElements++;
}

template <class T>
void DynamicVector<T>::remove(int index) {
    for (; index < this->nrElements - 1; ++index) {
        this->elements[index] = this->elements[index + 1];
    }

    this->nrElements--;
}

template <class T>
void DynamicVector<T>::update(int index, T& e) {

    this->elements[index] = e;
}


template <class T>
int DynamicVector<T>::getNrElements() const {
    return this->nrElements;
}


template <class T>
int DynamicVector<T>::getCapacity() const {
    return this->capacity;
}


template <class T>
T* DynamicVector<T>::getAll() const {
    return this->elements;
}
