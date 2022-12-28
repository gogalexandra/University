#include "STLVector.h"

void STLVector::add(elem& e)
{
    this->elements.push_back(e);
}

void STLVector::remove(int position)
{
    this->elements.erase(this->elements.begin() + position);
}

void STLVector::update(int position, elem& e)
{
    this->elements[position] = e;
}

vector<Dog> STLVector::getAll()
{
    return this->elements;
}

int STLVector::getNrElements()
{
    return this->elements.size();
}
