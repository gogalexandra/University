#pragma once
#include <vector>
#include <algorithm>
#include "Icecream.h"
#include <iostream>

class Order 
{
public:
	Order();
	~Order();
	void addIcecream(Icecream* i);
	void printOrder();

private:
	std::vector<Icecream*> all;
};

Order::Order()
{
}

Order::~Order()
{
}

inline void Order::addIcecream(Icecream* i)
{
	this->all.push_back(i);
}

inline void Order::printOrder()
{
	std::sort(this->all.begin(), this->all.end(), [](auto& it1, auto& it2) {
		return it1->computePrice() > it2->computePrice();
		});
	for (auto i : this->all) {
		std::cout << i->getDescription()<< " " << i->computePrice() << "\n";
	}
}
