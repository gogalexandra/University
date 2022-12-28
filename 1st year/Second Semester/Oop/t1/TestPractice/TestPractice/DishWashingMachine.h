#pragma once

#include <string>
#include <iostream>
#include "Appliance.h"

using namespace std;

class DishWashingMachine: public Appliance {
private:
    double washingCycleLength;
    double consumedElectricityForOneHour;

public:
    DishWashingMachine();
    DishWashingMachine(const string id, double weight, double length, double e);
    ~DishWashingMachine() {};
    double getwashingCycleLength();
    double getconsumedElectricity();
    string toString() override;
    double consumedElectricity() override;
};