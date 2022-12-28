#pragma once

#include <string>
#include <iostream>
#include "Appliance.h"

using namespace std;

class Refrigerator: public Appliance {

private:
    
    string electricityUsageClass;
    bool hasFreezer;

public:
    Refrigerator();
    Refrigerator(const string& id, const double weight, const string& usage, const bool freezer);
    ~Refrigerator() {};
    string getelectricityUsageClass();
    bool gethasFreezer();
    string toString() override;
    double consumedElectricity() override;
};