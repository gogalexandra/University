#pragma once

template <typename T>
class SmartPointer
{
public:
	SmartPointer();
	SmartPointer(T arr[1]);
	~SmartPointer();
	T* getElem();

private:
	T* obj;
};

template<typename T>
inline SmartPointer<T>::SmartPointer()
{
}

template<typename T>
inline SmartPointer<T>::SmartPointer(T arr[1])
{
	this->obj = arr;
}

template<typename T>
inline SmartPointer<T>::~SmartPointer()
{
}

template<typename T>
inline T* SmartPointer<T>::getElem()
{
	return obj;
}
