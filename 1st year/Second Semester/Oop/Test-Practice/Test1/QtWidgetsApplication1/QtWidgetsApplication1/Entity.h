#pragma once
#include <string>
#include <iostream>
using namespace std;

class Item {
private:

    string category;
    string name;
    int quantity;

public:

    Item() {};
    Item(const string& category, const string& name, const int quantity);
    ~Item() {};

    string getCategory();
    string getName();
    int getQuantity();
    string toString();
    friend ostream& operator<<(ostream& os, const Item& i);
    friend istream& operator>>(istream& is, Item& i);
};

inline ostream& operator<<(ostream& os, const Item& i) {
    os << i.category << string(" ")
        << i.name << string(" ")
        << to_string(i.quantity);
    return os;
}

inline istream& operator>>(istream& is, Item& i) {
    string age;
    is >> i.category;
    is >> i.name;
    is >> i.quantity;
    return is;
}