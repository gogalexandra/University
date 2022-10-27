#pragma once
#include "BeverageWithMilk.h"
#include "Tea.h"
#include "Coffee.h"

class BeverageMachine
{
public:
	BeverageMachine();
	~BeverageMachine();
	void prepare(std::string beverageType, int milkCount);
private:

};

BeverageMachine::BeverageMachine()
{
}

BeverageMachine::~BeverageMachine()
{
}

inline void BeverageMachine::prepare(std::string beverageType, int milkCount)
{
	if (beverageType == "Tea") {
		Tea T;
		BeverageWithMilk b{ &T, milkCount };
		b.print();
	}
	else
		if (beverageType == "Coffee") {
			Coffee C;
			BeverageWithMilk b{ &C, milkCount };
			b.print();
		}
}
