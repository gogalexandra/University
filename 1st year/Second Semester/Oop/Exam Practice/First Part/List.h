#pragma once
#include <vector>

template<typename T1, typename T2>
class List {
private:
    std::vector<std::pair<T1, T2>> db;

public:
    List();
    ~List();

    void add(T1 c, T2 n);
    void sortByFirstComponent();
    void sortBySecondComponent();
    void printReverse(std::ostream& stream);

};

template<typename T1, typename T2>
List<T1, T2>::List() {

}

template<typename T1, typename T2>
List<T1, T2>::~List() {

}

template<typename T1, typename T2>
void List<T1, T2>::add(T1 c, T2 n) {
    this->db.push_back(std::pair<T1, T2>(c, n));
}

template<typename T1, typename T2>
void List<T1, T2>::sortByFirstComponent() {
    if (this->db.empty())
        throw std::runtime_error("List is empty!");

    std::sort(this->db.begin(), this->db.end(), [](auto &it1, auto &it2) {
        return it1.first.getNumber() < it2.first.getNumber();
    });
}

template<typename T1, typename T2>
void List<T1, T2>::sortBySecondComponent() {
    if (this->db.empty())
        throw std::runtime_error("Empty list");

    std::sort(this->db.begin(), this->db.end(), [](auto &it1, auto &it2) {
        return it1.second < it2.second;
    });
}

template<typename T1, typename T2>
void List<T1, T2>::printReverse(std::ostream& stream) {
    std::vector<std::pair<T1, T2>> database = this->db;
    std::for_each(database.rbegin(), database.rend(), [&stream](auto &it){stream << it.first << " -> " << it.second << "; ";});
    stream << "\n";
}

