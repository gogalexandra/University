#pragma once
class Action
{
public:
	Action();
	~Action();
	virtual void execute() = 0;
};

Action::Action()
{
}

Action::~Action()
{
}