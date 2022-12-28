#pragma once
#include <string>
#include <iostream>
using namespace std;

class TimeInterval {
private:
    
    int start;
    int end;
    int precip;
    string description;

public:

    TimeInterval() {};
    TimeInterval(const int start, const int end, const int precip, const string& description);
    ~TimeInterval() {};

    int getStart();
    int getEnd();
    int getPrecip();
    string getDescription();
    string toString();
    friend ostream& operator<<(ostream& os, const TimeInterval& i);
    friend istream& operator>>(istream& is, TimeInterval& i);
};

inline ostream& operator<<(ostream& os, const TimeInterval& i) {
    os << i.start << string(" ")
        << i.end << string(" ")
        << i.precip << string(" ")
        << i.description << string(" ");
    return os;
}

inline istream& operator>>(istream& is, TimeInterval& i) {
    string age;
    is >> i.start;
    is >> i.end;
    is >> i.precip;
    is >> i.description;
    return is;
}