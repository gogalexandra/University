#pragma once
#include <vector>
#include "Entity.h"

using namespace std;

class Repository
{
private:
	vector<Item> items;

public:

	Repository();

	void addItem(string given_category, string given_name, int given_quantity);

	void deleteItem(string name);

	vector<Item> filterItems(string given_category);

	vector<Item> getItems() const { return items; }

	void load();

	void save();

	friend ostream& operator<<(ostream& os, const Repository& r);
};

inline ostream& operator<<(ostream& os, const Repository& r) {
	vector<Item> all = r.getItems();
	for (auto& it : all) {
		os << it << "\n";
	}
	return os;
}