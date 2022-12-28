#include "STLVector.h"

void STLVector::add(elem& e)
{
    this->elements.push_back(e);
}

vector<Gene> STLVector::getAll()
{
    return this->elements;
}

int STLVector::getNrElements()
{
    return this->elements.size();
}