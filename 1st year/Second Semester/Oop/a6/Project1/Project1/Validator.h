#pragma once
#include "Entity.h"

class ValidatorException {
public:
    explicit ValidatorException(string message) : message(std::move(message)) {};
    const string& getMessage() const {
        return this->message;
    }

private:
    string message;
};

class Validator {
public:
    static bool validateDog(const string& breed, const string& name, int age,const string& photo);
    static bool validateBreed(const string& breed);
    static bool validateName(const string& name);
    static bool validateAge(int age);
    static bool validatePhoto(const string& photo);

};