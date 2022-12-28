#include <iostream>
#include <iomanip>
#include <cstring>
#include "Entity.h"
#include <cassert>

using namespace std;

Dog::Dog() : breed(""), name(""), age{ 0 }, photograph(""){}

Dog::Dog(const string& breed, const string& name, const int age, const string& photograph) {
    /// <summary>
    /// Creates the object with given parameters
    /// </summary>
    /// <param name="breed"></param>
    /// <param name="name"></param>
    /// <param name="age"></param>
    /// <param name="photograph"></param>

    this->breed = breed;
    this->name = name;
    this->age = age;
    this->photograph = photograph;
}

Dog::~Dog(){

}

string Dog::getBreed() {
    /// <summary>
    /// Gets the breed of object
    /// </summary>
    /// <returns>the breed</returns>
    return this->breed;
}

string Dog::getName() {
    /// <summary>
    /// Gets the name of object
    /// </summary>
    /// <returns>the name</returns>
    return this->name;
}

int Dog::getAge() {
    /// <summary>
    /// Gets the age of object
    /// </summary>
    /// <returns>the age</returns>
    return this->age;
}

string Dog::getPhotograph() {
    /// <summary>
    /// Gets the photo of object
    /// </summary>
    /// <returns>the photo</returns>
    return this->photograph;
}


string Dog::toString() {
    /// <summary>
    /// Creates a string with the info of the object
    /// </summary>
    /// <returns>the string</returns>
    string s;/*
    inline std::string encoded(const std::string &this->photograph);*/
    s = "Breed: " + this->breed + "; Name: " + this->name + "; Age: " + to_string(this->age) + "; Photograph: " + this->photograph;
    return s;
} 

string Dog::toStringV2() {
    /// <summary>
    /// Creates a string with the info of the object
    /// </summary>
    /// <returns>the string</returns>
    string s;/*
    inline std::string encoded(const std::string &this->photograph);*/
    s = "Breed: " + this->breed + "; Name: " + this->name + "; Age: " + to_string(this->age);
    return s;
}