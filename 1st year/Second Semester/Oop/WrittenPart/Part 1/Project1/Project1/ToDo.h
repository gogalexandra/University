#pragma once
#include <vector>
#include <algorithm>
#include "Activity.h"

template<typename T>
class ToDo
{
public:
	ToDo();
	~ToDo();
	void operator+=(const T& elem);
	void reversePrint(std::ostream& stream);
	std::_Vector_iterator<std::_Vector_val<std::_Simple_types<T>>> begin();
	std::_Vector_iterator<std::_Vector_val<std::_Simple_types<T>>> end();

private:
	std::vector<T> elems;
};

template<typename T>
ToDo<T>::ToDo()
{
}

template<typename T>
ToDo<T>::~ToDo()
{
}

template<typename T>
void ToDo<T>::operator+=(const T& elem)
{
	this->elems.push_back(elem);
}

template<typename T>
void ToDo<T>::reversePrint(std::ostream& stream)
{
	/*std::vector<T>::template iterator it;
	for (it = this->elems.end() - 1; it >= this->elems.begin(); it--) {
		stream << "Activity" << it.getDescription() << "will take place at " << it.getTime() << ".\n";
	};*/
	std::vector<T> database = this->elems;
	std::for_each(database.rbegin(), database.rend(), [&stream](auto& it) {
		stream << "Activity " << it.getDescription() << " will take place at " << it.getTime() << ".\n";
		});
}

template<typename T>
std::_Vector_iterator<std::_Vector_val<std::_Simple_types<T>>> ToDo<T>::begin()
{
	return this->elems.begin();
}

template<typename T>
std::_Vector_iterator<std::_Vector_val<std::_Simple_types<T>>> ToDo<T>::end()
{
	return this->elems.end();
}
