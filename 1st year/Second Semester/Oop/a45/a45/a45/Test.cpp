#include "Test.h"
#include "Repository.h"
#include "Service.h"
#include "Entity.h"
#include "Templated.h"
#include <cassert>
#include <iostream>

void Test::runAll()
{
	/*TestTemplated();
	TestEntity();
	TestRepository();
	TestService();
	TestAdoptions();
	cout << "Tested all";*/
}

//void Test::TestTemplated()
//{
//	DynamicVector<Dog> dv;
//	Dog dog1{ "Husky", "Bob", 1, "p1" };
//	Dog dog2{ "Akita", "Alya", 2, "p2" };
//	Dog dog3{ "Akita", "Gigel", 3, "p3" };
//	Dog dog4("Chow-Chow", "Sierra", 2, "link");
//	Dog dog5("Golden Retriever", "Loki", 3, "link");
//	Dog dog6("Akita", "Layla", 5, "link");
//	Dog dog7("Bulldog", "Chase", 2, "link");
//	Dog dog8("Husky", "Phoebe", 1, "link");
//	Dog dog9("Husky", "Ziggy", 1, "link");
//	Dog dog10("Pomeraniam", "Chico", 3, "link");
//	dv.add(dog1);
//	dv.add(dog2);
//	dv.add(dog3);
//	dv.add(dog4);
//	dv.add(dog5);
//	dv.add(dog6);
//	dv.add(dog7);
//	dv.add(dog8);
//	dv.add(dog9);
//	dv.add(dog10);
//
//	assert(dv.getNrElements() == 10);
//	assert(dv.getCapacity() == 10);
//
//	Dog dog11("Pomeraniam", "Gigi", 3, "link");
//	dv.add(dog11);
//	assert(dv.getCapacity() == 20);
//
//	dv.remove(1);
//	assert(dv.getNrElements() == 10);
//}
//
//void Test::TestEntity()
//{
//	Dog dog1{ "Husky", "Bob", 1, "p1" };
//
//	assert("Husky" == dog1.getBreed());
//	assert("Bob" == dog1.getName());
//	assert(1 == dog1.getAge());
//	assert("p1" == dog1.getPhotograph());
//	string s = dog1.toString();
//	string s2 = dog1.toStringV2();
//	assert(s == "Breed: Husky; Name: Bob; Age: 1; Photograph: p1");
//	assert(s2 == "Breed: Husky; Name: Bob; Age: 1");
//}
//
//void Test::TestRepository()
//{
//	Repository repo{};
//	Dog dog1{ "Husky", "Bob", 1, "p1" };
//	repo.addDog(dog1);
//
//	assert(repo.findPosition(dog1.getName()) == 0);
//
//	Dog dog2{ "Akita", "Alya", 2, "p2" };
//	Dog dog3{ "Akita", "Gigel", 3, "p3" };
//	repo.addDog(dog2);
//	assert(repo.addDog(dog3)== true);
//	assert(repo.addDog(dog1) == false);
//	assert(repo.removeDog(dog2.getName()) == true);
//	Dog dog4{ "Husky", "Gigel", 3, "p3" };
//	repo.updateDog(dog4);
//	DynamicVector<Dog> dogss = repo.getDogs();
//	assert(dogss.getAll()[1].getBreed() == "Husky");
//	Dog dog5{ "Husky", "Nuexista", 3, "p3" };
//	assert(repo.updateDog(dog5) == false);
//	assert(repo.removeDog("nuexista") == false);
//
//	DynamicVector<Dog> filtered_dogs = repo.filterDog("Husky", 5);
//	assert(filtered_dogs.getNrElements() == 2);
//	DynamicVector<Dog> filtered_dogs2 = repo.filterDog("", 5);
//	assert(filtered_dogs2.getNrElements() == 2);
//}
//
//void Test::TestService()
//{
//	Repository repo{};
//	Service serv{ repo };
//	serv.addDogToRepo("Husky", "Bob", 1, "p1");
//	Repository dogss_repo = serv.getRepo();
//	DynamicVector<Dog> dogss = dogss_repo.getDogs();
//	assert(dogss.getAll()[0].getBreed() == "Husky");
//	assert(dogss.getNrElements() == 1);
//
//	serv.deleteDogFromRepo("Bob");
//
//	assert(serv.deleteDogFromRepo("Caine") == false);
//	serv.addDogToRepo("Husky", "Bob", 1, "p1");
//	serv.addDogToRepo("Akita", "Alya", 2, "p2");
//	serv.adoptDogToRepo("Husky", "Bob", 1, "p1");
//	assert(serv.getAdoptions().getNrAdoptions() == 1);
//
//	serv.updateDogFromRepo("Bischon", "Alya", 4, "p2");
//	DynamicVector<Dog> dogss2 = serv.getRepo().getDogs();
//	assert(dogss2.getAll()[1].getBreed() == "Bischon");
//	
//	DynamicVector<Dog> filtered = serv.filterDogsFromRepo("Bischon", 10);
//	assert(1 == filtered.getNrElements());
//}
//
//void Test::TestAdoptions() 
//{
//	Adoptions adopt;
//	Dog dog1{ "Husky", "Bob", 1, "p1" };
//	adopt.add(dog1);
//	assert(1 == adopt.getNrAdoptions());
//	assert(dog1.getName() == adopt.getAdoptionsList().getAll()[0].getName());
//
//}

