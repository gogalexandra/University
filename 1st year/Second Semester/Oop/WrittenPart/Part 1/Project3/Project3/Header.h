#pragma once
#include <vector>
#include <ostream>

template <typename T>
class Adder
{
public:
	Adder();
	Adder(T elem);
	~Adder();

	Adder<T> operator+(const T& e);
	Adder<T> operator()();
	void operator++();
	void operator--();
	void sum();
	friend std::ostream& operator<<(std::ostream& out, const T& t);
	//friend std::ostream& operator<< <> (std::ostream& o, const Adder<T>& x);

private:
	T elem;
	std::vector<T> all;
};

template<typename T>
inline Adder<T>::Adder()
{
}

template<typename T>
inline Adder<T>::Adder(T elem)
{
	this->elem = elem;
	this->all.push_back(elem);
}

template<typename T>
inline Adder<T>::~Adder()
{
}

template<typename T>
inline Adder<T> Adder<T>::operator+(const T& e)
{
	this->elem += e;
	this->all.push_back(e);
	return this*;
}

template<typename T>
inline Adder<T> Adder<T>::operator()()
{
	return *this;
}

template<typename T>
inline void Adder<T>::operator++()
{
	this->elem += this->all.back();
}

template<typename T>
inline void Adder<T>::operator--()
{
	if (this->all.size() == 0)
		throw runtime_error("No more values!");
	else {
		this->elem -= this->all.back();
		this->all.pop_back();
	}
}

template<typename T>
inline void Adder<T>::sum()
{
	return this->elem();
}

template<typename T>
std::ostream& operator<<(std::ostream& out, const T& t)
{
	out << t << "\n";
	return out;
}