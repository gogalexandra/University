#pragma once

#include "Person.h"
#include "BMI.h"
#include "BP.h"

class UI
{
private:
	Person p;

public:
	explicit UI(const Person& p) : p(p) {}

	void start();

private:
	static void printMenu();
	void add();
	void displayAll();
	void addBMI();
	void addBP();
	void check();
	void save();
};