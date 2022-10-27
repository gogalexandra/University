#pragma once
#include "Menu.h"
#include <vector>

class MenuBar
{
public:
	MenuBar();
	~MenuBar();
	void add(Menu* m);
	void print();

private:
	std::vector<Menu*> menus;
};

MenuBar::MenuBar()
{
}

MenuBar::~MenuBar()
{
}

inline void MenuBar::add(Menu* m)
{
	this->menus.push_back(m);
}

inline void MenuBar::print()
{
	for (auto i : this->menus) {
		std::cout << i << "\n";
		i->print();
	}
}
