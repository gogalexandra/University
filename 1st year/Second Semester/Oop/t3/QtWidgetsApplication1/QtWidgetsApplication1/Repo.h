#pragma once
#include <vector>
#include "Entity.h"

using namespace std;

class Repository
{
private:
	vector<TimeInterval> day;

public:

	Repository();

	vector<TimeInterval> filterProbability(int given_probability);

	vector<TimeInterval> filterDescription(vector<string> given_description);

	vector<TimeInterval> getItems() const { return day; }

	void load();

	void save();

	friend ostream& operator<<(ostream& os, const Repository& r);
};

inline ostream& operator<<(ostream& os, const Repository& r) {
	vector<TimeInterval> all = r.getItems();
	for (auto& it : all) {
		os << it << "\n";
	}
	return os;
}