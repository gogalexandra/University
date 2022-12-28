#include <gtest/gtest.h>
#include "../a45/DynamicVector.h"
#include "../a45/Service.h"
#include "../a45/Repository.h"
#include "../a45/Entity.h"
#include "../a45/DynamicVector.h"


TEST(TestDynamicVector, DynamicVector__Test) {
    EXPECT_EQ(1, 1);
    EXPECT_TRUE(true);
    DynamicVector dv;
    Dog dog1{ "Husky", "Bob", 1, "p1" };
    Dog dog2{ "Akita", "Alya", 2, "p2" };
    Dog dog3{ "Akita", "Gigel", 3, "p3" };
    dv.add(dog1);
    dv.add(dog2);
    EXPECT_EQ(dv.getNrElements(), 2);
    assert(dv.getNrElements() == 2);
    dv.remove(1);
    assert(dv.getNrElements() == 1);
}

TEST(TestRepository, TestRepository_Test) {
    Repository repo{};
    Dog dog1{ "Husky", "Bob", 1, "p1" };
    repo.addDog(dog1);
    assert(repo.findPosition(dog1.getName()) == 0);

    Dog dog2{ "Akita", "Alya", 2, "p2" };
    Dog dog3{ "Akita", "Gigel", 3, "p3" };
    repo.addDog(dog2);
    repo.addDog(dog3);
    assert(repo.addDog(dog1) == false);
    assert(repo.removeDog(dog2.getName()) == true);
    repo.updateDog("Husky", dog3.getName(), dog3.getAge(), dog3.getPhotograph());
    DynamicVector dogss = repo.getDogs();
    assert(dogss.getAll()[1].getBreed() == "Husky");
}

TEST(TestEntity, TestEntity__Test) {
    Dog dog1{ "Husky", "Bob", 1, "p1" };
    assert("Husky" == dog1.getBreed());
    assert("Bob" == dog1.getName());
    assert(1 == dog1.getAge());
    assert("p1" == dog1.getPhotograph());
}

TEST(TestService, TestService__Test) {
    Repository repo{};
    Service serv{ repo };
    serv.addDogToRepo("Husky", "Bob", 1, "p1");
    Repository dogss_repo = serv.getRepo();
    DynamicVector dogss = dogss_repo.getDogs();
    assert(dogss.getAll()[1].getBreed() == "Husky");
    assert(dogss.getNrElements() == 1);

    serv.deleteDogFromRepo("Bob");
    assert(dogss.getNrElements() == 0);

    assert(serv.deleteDogFromRepo("Caine"), false);
    serv.addDogToRepo("Husky", "Bob", 1, "p1");
    serv.addDogToRepo("Akita", "Alya", 2, "p2");

    dogss_repo = serv.getRepo();
    dogss = dogss_repo.getDogs();
    serv.updateDogFromRepo("Husky", "Alya", 2, "p2");
    assert(dogss.getAll()[2].getBreed() == "Husky");
    assert(dogss.getNrElements() == 2);

}