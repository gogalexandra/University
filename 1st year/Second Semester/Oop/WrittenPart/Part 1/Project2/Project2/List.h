#pragma once
#include <vector>
#include <stdexcept>
#include <string>
#include <algorithm>
using namespace std;

template<typename T1, typename T2>
class List
{
public:
	List();
	~List();
	void add(T1 c, T2 v);
	void sortByFirstComponent();
	void sortBySecondComponent();
	void printReverse(ostream& stream);

private:
	vector<pair<T1, T2>> elems;
};


template<typename T1, typename T2>
List<T1, T2>::List()
{
}

template<typename T1, typename T2>
List<T1, T2>::~List()
{
}

template<typename T1, typename T2>
void List<T1, T2>::add(T1 c, T2 v)
{
	this->elems.push_back(pair<T1, T2>(c, v));
}

template<typename T1, typename T2>
void List<T1, T2>::sortByFirstComponent()
{
	if (this->elems.size() == 0)
		throw runtime_error("List is empty!");
	else {
		sort(this->elems.begin(), this->elems.end(), [](auto& it1, auto& it2) {
			return it1.first.getNr() < it2.first.getNr();
			});
	}
}

template<typename T1, typename T2>
void List<T1, T2>::sortBySecondComponent()
{
	if (this->elems.size() == 0)
		throw runtime_error("List is empty!");
	else {
		sort(this->elems.begin(), this->elems.end(), [](auto& it1, auto& it2) {
			return it1.second < it2.second;
			});
	}
}

template<typename T1, typename T2>
void List<T1, T2>::printReverse(ostream& stream)
{
	vector<pair<T1, T2>> database = this->elems;
	for_each(database.rbegin(), database.rend(), [&stream](auto& it) {
		stream << it.first.getType() << " " << it.first.getNr() << " -> " << it.second << "\n";
		});
}