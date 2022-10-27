#pragma once
#include "Action.h"
#include <string>
#include <iostream>

class MenuItem : public Action
{
public:
	MenuItem();
	MenuItem(std::string t);
	MenuItem(Action* ac);
	~MenuItem();
	void print();
	void clicked();
	void execute() override;

protected:
	Action* a;
private:
	std::string text;
};

MenuItem::MenuItem()
{
}

inline MenuItem::MenuItem(std::string t)
{
	this->text = t;
}

inline MenuItem::MenuItem(Action* ac)
{
	this->a = ac;
}


MenuItem::~MenuItem()
{
}

inline void MenuItem::print()
{
	std::cout << text << "\n";
}

inline void MenuItem::clicked()
{
	std::cout << "Clicked" << "\n";
	//this->execute();
}

void MenuItem::execute()
{
	a->execute();
}
