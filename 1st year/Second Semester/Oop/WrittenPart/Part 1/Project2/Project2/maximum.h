#pragma once
#include <iostream>
#include <vector>

template <typename T>
T maximum(std::vector<T> v) {
	T max = v.front();
	for (auto i : v) {
		if (i > max)
			max = i;
	}
	return max;
}


//class maximum
//{
//public:
//	maximum();
//	maximum(std::vector<T> v);
//	~maximum();
//	friend std::ostream& operator<< <>(std::ostream& out, const maximum<T>& m);
//	T getmax();
//
//private:
//	std::vector<T> vec;
//	T max;
//};
//
//template <typename T>
//std::ostream& operator<< <>(std::ostream& out, const maximum<T>& m) {
//	out << m.max << "\n";
//	return out;
//}
//
//template<typename T>
//inline maximum<T>::maximum()
//{
//}
//
//template<typename T>
//inline maximum<T>::maximum(std::vector<T> v)
//{
//	this->vec = v;
//	this->max = getmax();
//}
//
//template<typename T>
//inline maximum<T>::~maximum()
//{
//}
//
//template<typename T>
//inline T maximum<T>::getmax()
//{
//	T max = this->vec.front();
//	for (auto i : this->vec) {
//		if (i > max)
//			max = i;
//	}
//	return max;
//}
