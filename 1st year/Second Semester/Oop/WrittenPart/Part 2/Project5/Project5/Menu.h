#pragma once
#include "MenuItem.h"
#include <vector>

class Menu: public MenuItem
{
public:
	Menu();
	~Menu();
	void add(MenuItem* m);
	void print();

private:
	std::vector<MenuItem*> all;
};

Menu::Menu()
{
}

Menu::~Menu()
{
}

inline void Menu::add(MenuItem* m)
{
	this->all.push_back(m);
}

inline void Menu::print()
{
	for (auto i : this->all) {
		std::cout << i << "\n";
		i->print();
	}
}
