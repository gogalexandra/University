#include "Service.h"

bool Service::addDogToRepo(const string& breed, const string& name, int age, const string& photography){
	/// <summary>
	/// Calls function from repo
	/// </summary>
	/// <param name="breed"></param>
	/// <param name="name"></param>
	/// <param name="age"></param>
	/// <param name="photography"></param>
	/// <returns>true if addes, false if already exists</returns>
	Dog doggy{breed, name, age, photography};
	return this->repo.addDog(doggy);
}


bool Service::deleteDogFromRepo(string name) {
	/// <summary>
	/// Calls function from repo
	/// </summary>
	/// <param name="name">name of the object </param>
	/// <returns>true if deleted, false if not found</returns>
	return this->repo.removeDog(name);
}

bool Service::updateDogFromRepo(const string& breed, const string& name, int age, const string& photography) {
	/// <summary>
	/// Calls function from repo
	/// </summary>
	/// <param name="name">name of the object </param>
	/// <returns>true if updated, false if not found</returns>
	Dog doggy{ breed, name, age, photography };
	return this->repo.updateDog(doggy);
}

void Service::adoptDogToRepo(const string& breed, const string& name, int age, const string& photography)
{
	/// <summary>
	/// Calls the function from the dynamicvector to add a dog to the list of adoptions
	/// </summary>
	/// <param name="breed">dog breed</param>
	/// <param name="name">dog name</param>
	/// <param name="age">dog age</param>
	/// <param name="photography"> dog photography</param>
	Dog doggy{ breed, name, age, photography };
	this->adopt.add(doggy);
	this->repo.removeDog(name);
}


STLVector Service::filterDogsFromRepo(string given_breed, int given_age)
{
	/// <summary>
	/// Calls function from Adopt to filter the dogs by the users parameters
	/// </summary>
	/// <param name="given_breed">breed given by the user</param>
	/// <param name="given_age">age given by the user</param>
	/// <returns>the list of dogs that respect the conditions</returns>
	return this->repo.filterDog(given_breed, given_age);
}
