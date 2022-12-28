#pragma once
#include <string>

using namespace std;

class Gene {
private:

    string organism;
    string name;
    string sequence;

public:

    Gene();
    Gene(const string& organism, const string& name, const string& sequence);
    ~Gene();

    string getOrganism();
    string getName();
    string getSequence();
    string toString();
};