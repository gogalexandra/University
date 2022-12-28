#pragma once

#include "Service.h"
#include "Refrigerator.h"
#include "DishWashingMachine.h"

class UI
{
private:
	Service s;

public:
	explicit UI(const Service& s) : s(s) {}

	void start();

private:
	static void printMenu();
	void addAppliance();
	void displayAll();
	void displaySorted();
	void addR();
	void addD();
	void save();
	bool compare(Appliance *a1, Appliance *a2);
};