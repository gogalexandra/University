#include "Validator.h"
#include <iostream>

bool Validator::validateDog(const string& breed, const string& name, int age, const string& photo)
{
    try {
        Validator::validateBreed(breed) && Validator::validateName(name) && Validator::validateAge(age) && Validator::validatePhoto(photo);
    }
    catch (const ValidatorException& e) {
        cout << e.getMessage() << endl;
        return false;
    }

    return true;
}

bool Validator::validateBreed(const string& breed)
{
    if (breed.empty()) {
        throw ValidatorException("[NOT VALID] Breed is empty");
    }

    return true;
}

bool Validator::validateName(const string& name)
{
    if (name.empty()) {
        throw ValidatorException("[NOT VALID] Name is empty");
    }

    return true;
}

bool Validator::validateAge(int age)
{
    if (age <= 0 || age > 99) {
        throw ValidatorException("[NOT VALID] age between 0 and 99");
    }

    return true;
}

bool Validator::validatePhoto(const string& photo)
{
    if (photo.empty()) {
        throw ValidatorException("[NOT VALID] Photography is empty");
    }

    return true;
}

