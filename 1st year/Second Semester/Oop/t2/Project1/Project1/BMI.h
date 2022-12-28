#pragma once
#include <string>
#include <iostream>
#include "Measurement.h"

using namespace std;

class BMI : public Measurement {

private:

    double value;

public:
    BMI() {};
    BMI(const string& date, const double value);
    ~BMI() {};
    double getValue();
    string toString() override;
    bool isNormalValue() override;
};