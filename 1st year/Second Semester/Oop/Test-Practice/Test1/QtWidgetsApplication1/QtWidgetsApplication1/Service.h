#pragma once
#include <iostream>
#include "Repo.h"


using namespace std;
class Service {

private:
	Repository* repo;

public:

	Service() {};

	Service(Repository& r);

	vector<Item> getRepo() const { return repo->getItems(); }

	void addItemToRepo(const string& category, const string& name, int quantity);

	void deleteItemFromRepo(string name);

	vector<Item> filterItemsFromRepo(string given_category);

	Service& operator=(const Service& s);

	friend ostream& operator<<(ostream& os, const Service& s);

	void openFile();
};

inline ostream& operator<<(ostream& os, const Service& s) {

	vector<Item> all = s.repo->getItems();
	for (auto& it : all) {
		os << "Category: " << it.getCategory() << " ";
		os << "Name: " << it.getName() << " ";
		os << "Quantity: " << it.getQuantity() << "\n";
	}
	return os;
}
