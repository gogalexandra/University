#pragma once
#include <vector>
#include <iostream>

template<typename T>
class Vector
{
public:
	Vector();
	Vector( const std::vector<T>& v);
	~Vector();
	//void printAll(std::ostream& stream);
	Vector<T> add(const T& elem);
	Vector<T> operator-(const T& e);
	void operator=(Vector<T> v1);
	std::_Vector_iterator<std::_Vector_val<std::_Simple_types<T>>> begin();
	std::_Vector_iterator<std::_Vector_val<std::_Simple_types<T>>> end();


private:
	std::vector<T> elems;
};

template <typename T>
Vector<T> Vector<T>::operator-(const T& e) {
	std::vector<T>::iterator<T> it = std::find(this->elems.begin(), this->elems.end(), e);
	if (it == this->elems.end()) {
		throw exception("Element does not exist!");
	}
	else
	{
		int index = std::distance(this->elems.begin(), it);
		this->elems.erase(this->elems.begin() + index);
	}
	return *this;
}

template <typename T>
void Vector<T>::operator=(Vector<T> v1) {
	this->elems.clear();
	for (auto i : v1) {
		this.pushback(i);
	}
}

template<typename T>
inline std::_Vector_iterator<std::_Vector_val<std::_Simple_types<T>>> Vector<T>::begin()
{
	return this->elems.begin();
}

template<typename T>
inline std::_Vector_iterator<std::_Vector_val<std::_Simple_types<T>>> Vector<T>::end()
{
	return this->elems.end();
}

template<typename T>
inline Vector<T>::Vector()
{
}

template<typename T>
inline Vector<T>::Vector(const std::vector<T>& v)
{
	this->elems = v;
}

template<typename T>
inline Vector<T>::~Vector()
{
}

template<typename T>
inline Vector<T> Vector<T>::add(const T& e)
{
	this->elems.push_back(e.getElem());
	return *this;
}


//template<typename T>
//void Vector<T>::printAll(std::ostream& stream)
//{
//	vector<T> database = this->elems;
//	for (auto i : database) {
//		stream << i << " ";
//	}
//	stream << "\n";
//}
