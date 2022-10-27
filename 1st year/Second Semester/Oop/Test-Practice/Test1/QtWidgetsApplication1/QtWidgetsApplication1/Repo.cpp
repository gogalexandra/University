#include <fstream>
#include <sstream>
#include "Repo.h"

Repository::Repository()
{
	this->load();
}

void Repository::addItem(string given_category, string given_name, int given_quantity)
{
	Item i(given_category, given_name, given_quantity);
	this->items.push_back(i);
}

void Repository::deleteItem(string name)
{
	int idx = 0;
	for (auto& it : this->items) {
		if (it.getName() == name)
			break;
		idx += 1;
	}
	this->items.erase(this->items.begin() + idx);
	this->save();
}

vector<Item> Repository::filterItems(string given_category)
{
	vector<Item> filtered;
	if (given_category == "")
		return this->items;
	else {
		for (auto& it : this->items) {
			if (it.getCategory() == given_category)
				filtered.push_back(it);
		}
		return filtered;
	}
	
}

void Repository::load()
{
	ifstream input("info.txt", 0);
	string line;
	while (std::getline(input, line))
	{
		std::istringstream iss(line);
		Item i;
		if (!(iss >> i)) { break; } // error
		this->addItem(i.getCategory(), i.getName(), i.getQuantity());
	}
}

void Repository::save()
{
	ofstream output("info.txt");
	output << *this;
	output.close();
}
