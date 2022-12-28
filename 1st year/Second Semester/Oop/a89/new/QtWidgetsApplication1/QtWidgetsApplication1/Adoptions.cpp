#include "Adoptions.h"
#include "Repository.h"

Adoptions::Adoptions(const Adoptions& a)
{
	this->adoptions = a.adoptions;
}

void Adoptions::add(Dog& dog)
{
	/// <summary>
	/// the object dog is added to the adoption list (no validations because dogs that are already adopted are not shown)
	/// </summary>
	/// <param name="d">new object to be added</param>
	/// <returns>true is object is addes, false if it already exists</returns>
	this->adoptions.add(dog);
	this->save();
}

int Adoptions::getNrAdoptions()
{
	/// <summary>
	/// Gets nr of dogs adopted
	/// </summary>
	/// <returns>nr of elementes</returns>
	return this->adoptions.getNrElements();
}
