#pragma once
#include <vector>

template<typename T>
class ToDo {
private:
    std::vector<T> db;

public:
    ToDo();
    ~ToDo();

    void operator+=(const T& elem);
    std::__wrap_iter<T*> begin();
    std::__wrap_iter<T*> end();
    void reversePrint(std::ostream& stream);
};

template<typename T>
ToDo<T>::ToDo() {

}

template<typename T>
ToDo<T>::~ToDo() {

}

template<typename T>
void ToDo<T>::operator+=(const T& elem) {
    this->db.push_back(elem);
}

template<typename T>
void ToDo<T>::reversePrint(std::ostream &stream) {
    std::vector<T> database = this->db;
    std::for_each(database.rbegin(), database.rend(), [&stream](auto &it){
        stream << "Activity " << it.getTitle() << " will take place at " << it.getHour() << ".\n";
    });
}

template<typename T>
std::__wrap_iter<T*> ToDo<T>::begin() {
    return this->db.begin();
}

template<typename T>
std::__wrap_iter<T*> ToDo<T>::end() {
    return this->db.end();
}




