#pragma once
#include "Action.h"
#include <iostream>

class CreateAction : public Action
{
public:
	CreateAction();
	~CreateAction();

private:
	void execute() override;
};

CreateAction::CreateAction()
{
}

CreateAction::~CreateAction()
{
}

inline void CreateAction::execute()
{
	std::cout << "Create Action" << "\n";
}
