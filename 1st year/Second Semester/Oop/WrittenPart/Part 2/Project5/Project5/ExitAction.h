#pragma once
#include "Action.h"
#include <iostream>

class ExitAction : public Action
{
public:
	ExitAction();
	~ExitAction();

private:
	void execute() override;
};

ExitAction::ExitAction()
{
}

ExitAction::~ExitAction()
{
}

inline void ExitAction::execute()
{
	std::cout << "Exit action" << "\n";
}
