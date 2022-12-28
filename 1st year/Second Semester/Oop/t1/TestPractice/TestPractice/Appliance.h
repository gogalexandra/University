#pragma once

#include <string>
#include <iostream>
using namespace std;

class Appliance {
protected:
    
    string id;
    double weight;

public:
    Appliance() {};
    Appliance(const string id, double w);
    ~Appliance() {};
    string getId();
    double getWeight();
    virtual string toString();
    virtual double consumedElectricity() { return 0.0; };
};