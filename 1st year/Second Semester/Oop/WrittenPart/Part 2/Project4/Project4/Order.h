#pragma once
#include <vector>
#include <algorithm>
#include "Pizza.h"
#include <iostream>

class Order
{
public:
	Order();
	~Order();
	void addPizza(Pizza* p);
	void printOrder();

private:
	std::vector<Pizza*> all;
};

Order::Order()
{
}

Order::~Order()
{
}

inline void Order::addPizza(Pizza* p)
{
	this->all.push_back(p);
}

inline void Order::printOrder()
{
	std::sort(this->all.begin(), this->all.end(), [](auto& it1, auto& it2) {
		return it1->computePrice() > it2->computePrice();
		});
	for (auto i : this->all) {
		std::cout << i->getDescription() << " " << i->computePrice() << "\n";
	}
}
