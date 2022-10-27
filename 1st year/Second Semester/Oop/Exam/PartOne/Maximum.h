#pragma once
#include <vector>
#include <iostream>
#include <string>

template<typename T>
class maximum {
private:
    std::vector<T> db;
    int maxValue;

public:
    maximum(){};
    maximum(std::vector<T>& givenVector) : db(givenVector), maxValue(this->findMax()){};
    ~maximum(){};

    int findMax();
    friend std::ostream& operator<<(std::ostream& out, const maximum<T>& m);
};

template<typename T>
int maximum<T>::findMax() {
    int maxValue1 = -1;
    std::for_each(this->db.begin(), this->db.end(), [this, &maxValue1](auto& it){
        if (it > maxValue1) {
            maxValue1 = it.getGrade();
        }
    });
    return maxValue1;
}

template<typename T>
inline std::ostream &operator<<(std::ostream &out, const maximum<T> &m) {
    out << m.maxValue;
    return out;
}

