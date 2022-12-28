#include <iostream>
#include <iomanip>
#include <cstring>
#include "Entity.h"
#include <cassert>

using namespace std;

Gene::Gene() : organism(""), name(""), sequence(""){}

Gene::Gene(const string& organism, const string& name, const string& sequence) {
  

    this->organism = organism;
    this->name = name;
    this->sequence = sequence;
}

Gene::~Gene() {

}

string Gene::getOrganism() {
    return this->organism;
}

string Gene::getName() {
    return this->name;
}

string Gene::getSequence() {
    return this->sequence;
}


string Gene::toString() {
    string s;
    s = this->organism + " |  " + this->name + " | " + this->sequence;
    return s;
}