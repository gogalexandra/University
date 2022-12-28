#include <string>
#pragma once
using namespace std;

class Dog {
private:

    string breed;
    string name;
    int age;
    string photograph;

public:

    Dog();
    Dog(const string& breed, const string& name, const int age, const string& photograph);
    ~Dog();

    string getBreed();
    string getName();
    int getAge();
    string getPhotograph();
    string toString();
    string toStringV2();
};


