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
	static void printAdministratorMenu();
	static void printUserMenu();
	void addDogToRepo();
	void deleteDogFromRepo();
	void updateDogFromRepo();
	void displayAllDogsFromRepo();
	void displayAllAdoptionsFromRepo();
	void filterDogs();
	void adoptDogs(STLVector dv);
};