#include "TextRepo.h"
#include <string>
#include "STLVector.h"
#include <istream>
#include "Validator.h"

using namespace std;

bool Repository::addDog(Dog& d)
{
	/// <summary>
	/// Checks if the object already exists(name should be unique), is so its addes else no
	/// </summary>
	/// <param name="d">new object to be added</param>
	/// <returns>true is object is addes, false if it already exists</returns>
	int position = this->findPosition(d.getName());
	if (position != -1)
		throw ValidatorException("[ERROR] Name is taken");

	this->dogs.add(d);
	this->save();
	return true;
}

bool Repository::removeDog(string name) {
	/// <summary>
	/// Checks if the object exists, if so it s deleted, else nothing happens
	/// </summary>
	/// <param name="name">name of the object that is gonna be deleted</param>
	/// <returns>true if the object is deleted, false if it does not exist</returns>
	int position = this->findPosition(name);
	if (position == -1)
		throw ValidatorException("[ERROR] Name not found");

	this->dogs.remove(position);
	this->save();
	return true;
}

bool Repository::updateDog(Dog& d) {
	/// <summary>
	/// Checks if the object exist, if so it recieves the new info, else nothing happens
	/// </summary>
	/// <param name="breed">new info</param>
	/// <param name="name">name of the object that needs to be modified</param>
	/// <param name="age">new age</param>
	/// <param name="photography">new photography</param>
	/// <returns>true if modified, false if object does not exist</returns>
	int position = this->findPosition(d.getName());
	if (position == -1)
		throw ValidatorException("[ERROR] Name not found");

	this->dogs.update(position, d);
	this->save();
	return true;

}

STLVector Repository::filterDog(string given_breed, int given_age)
{
	/// <summary>
	/// This function parses the vector of all entities and creates a new dynamic vector with the ones that have the breed field the same as givenbreed
	/// and the age smaller than given age 
	/// </summary>
	/// <param name="given_breed">string givenby the user</param>
	/// <param name="given_age">int given by the user</param>
	/// <returns>a dynamic array with entities that respect the conditions</returns>
	STLVector array = this->getDogs();
	STLVector filtered{};

	for (auto& it : this->dogs.getAll()) {
		if (given_breed.empty()) {
			if (it.getAge() < given_age)
				filtered.add(it);
		}
		else
			if (it.getBreed() == given_breed)
				if (it.getAge() < given_age)
					filtered.add(it);
	}

	return filtered;
}

int Repository::findPosition(string name) {
	/// <summary>
	/// Search throw all objects if one of them has the name "name"
	/// </summary>
	/// <param name="name">the name that is looking for</param>
	/// <returns>position of the object that has the name "name", -1 otherwise </returns>
	vector<Dog>::iterator it2;
	for (int i = 0; i < this->dogs.getNrElements(); i++) {
		if (this->dogs.getAll().at(i).getName() == name)
			return i;
	}
	return -1;
}