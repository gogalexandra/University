#pragma once

#include "Service.h"

class UI
{
private:
	Service s;

public:
	explicit UI(const Service& s) : s(s) {}

	void start();

private:
	static void printMenu();
	void addGeneToRepo();
	void displayAllGenesFromRepo();
	void displaySpecificGenesFromRepo();
	void swap(Gene *it, Gene *it2);
};