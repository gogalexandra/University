#pragma once
#include <stdexcept>

class Complex
{
public:
	Complex();
	Complex(int r, int i);
	Complex(const Complex& c);
	~Complex();
	int getReal();
	int getImaginary();
	void setReal(int r);
	void setImaginary(int i);
	friend Complex operator/(const Complex& c, const int& x);
	Complex& operator=(const Complex& c);
	friend bool operator==(Complex c1, Complex c2);
	friend std::ostream& operator<<(std::ostream& out, const Complex& c);

private:
	int real;
	int img;
};

std::ostream& operator<<(std::ostream& out, const Complex& c) {
	out << c.real << " + " << c.img << "i" ;
	return out;
}

Complex operator/(const Complex& c, const int& x)
{
	if (x == 0) {
		throw std::runtime_error("Division by zero!");
	}
	else {
		Complex res{ c.real / x, c.img / x };
		return res;
	}
}

inline bool operator==(Complex c1, Complex c2)
{
	if (c1.getReal() == c2.getReal() && c2.getImaginary() == c2.getImaginary())
		return true;
	return false;
}


Complex::Complex()
{
	this->real = 0;
	this->img = 0;
}

inline Complex::Complex(int r, int i)
{
	this->real = r;
	this->img = i;
}

inline Complex::Complex(const Complex& c)
{
	this->real = c.real;
	this->img = c.img;
}

Complex::~Complex()
{
}

inline int Complex::getReal()
{
	return this->real;
}

inline int Complex::getImaginary()
{
	return this->img;
}

inline void Complex::setReal(int r)
{
	this->real = r;
}

inline void Complex::setImaginary(int i)
{
	this->img = i;
}

inline Complex& Complex::operator=(const Complex& c)
{
	this->setReal(c.real);
	this->setImaginary(c.img);
	return *this;
}
