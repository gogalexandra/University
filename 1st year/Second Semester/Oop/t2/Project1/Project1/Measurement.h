#pragma once

#include <string>
#include <iostream>
using namespace std;

class Measurement {
protected:
    
    string date;

public:
    Measurement() {};
    Measurement(const string date);
    ~Measurement() {};
    string getDate();
    virtual string toString();
    virtual bool isNormalValue();
};