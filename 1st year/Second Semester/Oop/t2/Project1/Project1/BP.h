#pragma once

#include <string>
#include <iostream>
#include "Measurement.h"

using namespace std;

class BP : public Measurement {

private:

    int systolicValue;
    int diastolicValue;

public:
    BP() {};
    BP(const string& date, const int sv, const int dv);
    ~BP() {};
    int getSystolic();
    int getDiastolic();
    string toString() override;
    bool isNormalValue() override;
};