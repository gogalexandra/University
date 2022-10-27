#pragma once
#include <string>
#include <iostream>
#include <list>
#include <iterator>
#pragma once
using namespace std;

class Vegetable {
private:

    string family;
    string name;
    list<string> parts;

public:

    Vegetable();
    Vegetable(const string& family, const string& name, list<string> parts);
    ~Vegetable() {};

    string getFamily();
    string getName();
    list<string> getParts();
    friend ostream& operator<<(ostream& os, const Vegetable& v);
    friend istream& operator>>(istream& is, Vegetable& v);
};

inline ostream& operator<<(ostream& os, const Vegetable& v) {
    os << v.family << string(" ")
        << v.name << string(" ");
        //<< v.parts << string(" ");
    return os;
}

inline istream& operator>>(istream& is, Vegetable& v) {
    string age;
    is >> v.family;
    is >> v.name;
    //is >> v.parts;
    return is;
}