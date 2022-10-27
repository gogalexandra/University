#pragma once
#include <vector>
using namespace std;


template<typename T>
class Stack{
public:
	Stack();
	Stack(int cap);
	~Stack();

	int getMaxCapacity();
	Stack<T> operator+(const T& elem);
	void operator=(Stack<T> v);
	T pop();

private:
	int maxCapacity;
	vector<T> elems;
};

template<typename T>
Stack<T>::Stack()
{
}

template<typename T>
Stack<T>::Stack(int cap)
{
	this->maxCapacity = cap;
}


template<typename T>
Stack<T>::~Stack()
{
}

template<typename T>
int Stack<T>::getMaxCapacity()
{
	return this->maxCapacity;
}

template<typename T>
T Stack<T>::pop()
{
	T aux = this->elems.back();
	this->elems.pop_back();
	return aux;
}

template<typename T>
Stack<T> Stack<T>::operator+(const T& elem)
{
	if (this->elems.size() == this->maxCapacity) {
		throw exception("Stack is full!");
	}
	else {
		Stack<T> aux(this->maxCapacity);
		aux.elems.push_back(elem);
		return aux;
	}
}

template<typename T>
void Stack<T>::operator=(Stack<T> st)
{
	this->elems.push_back(st.elems.back());
}