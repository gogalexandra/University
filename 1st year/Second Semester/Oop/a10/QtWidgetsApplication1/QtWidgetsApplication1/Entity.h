#include <string>
#include <iostream>
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
    friend ostream& operator<<(ostream& os, const Dog& d);
    friend istream& operator>>(istream& is, Dog& d);
};

inline ostream & operator<<(ostream& os, const Dog& d) {
    os << d.breed<< string(" ")
        << d.name << string(" ")
        << to_string(d.age) << string(" ")
        << d.photograph;
    return os;
}

inline istream& operator>>(istream& is, Dog& d) {
    string age;
    is >> d.breed;
    is >> d.name;
    is >> d.age;
    is >> d.photograph;
    return is;
}