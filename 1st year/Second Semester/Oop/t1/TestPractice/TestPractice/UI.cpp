#include "UI.h"
#include <algorithm>
#include <vector>

using namespace std;


void UI::printMenu()
{
	cout << endl;
	cout << "0. Exit." << endl;
	cout << "1. Add appliance" << endl;
	cout << "2. Display all" << endl;
	cout << "3. Display sorted" << endl;
	cout << "4. Save specific appliances" << endl;
}

void UI::addAppliance()
{
	cout << "\n	a. Add refrigerator";
	cout << "\n	b. Dish washing machine";
	cout << "\n ---> ";
	string op;
	cin >> op;
	if (op == "a")
		this->addR();
	else
		this->addD();

}

void UI::displayAll()
{
	if (this->s.getElems().size() == 0) {
		cout << "\n Nothing to display" << endl;
	}
	else
		for (auto& it : this->s.getElems()) {
			cout <<"\n" + it->toString() << endl;
		}
}

void UI::displaySorted()
{
	vector<elem*> sorted;
	for (auto& it : this->s.getElems()) {
		sorted.push_back(it);
	}


	if (this->s.getElems().size() == 0) {
		cout << "\n Nothing to display" << endl;
	}
	else {
		//std::sort(sorted.begin(), sorted.end(), compare);
		for (auto& it : sorted) {
			cout << "\n" + it->toString() << endl;
		}
	}
}

void UI::save()
{
	cout << "Give maxim quantity of consumed electricity: ";
	double x;
	cin >> x;
	cout << "Give file name where to be save: ";
	string file;
	cin >> file;
	this->s.write(file, x);
}

bool UI::compare(Appliance *a1, Appliance *a2)
{
	return (a1->getWeight() < a2->getWeight());
}

void UI::addR()
{
	cout << "Give id: ";
	string id;
	cin >> id;
	cout << "Give weigth: ";
	double weigth;
	cin >> weigth;
	cout << "Give electricity usage class(A, A+, A++): ";
	string usage;
	cin >> usage;
	cout << "Has freezer? ";
	string op;
	cin >> op;
	bool freezer;
	if (op == "yes")
		freezer = true;
	else
		freezer = false;
	Appliance *a = new Refrigerator{ id, weigth, usage, freezer};
	if (this->s.addToRepo(a))
		cout << "\n Added succesfully";
	else
		cout << "\n Id already exists";

}

void UI::addD()
{
	cout << "Give id: ";
	string id;
	getline(cin, id);
	cout << "Give weigth: ";
	double weigth;
	cin >> weigth;
	cout << "Give washing cycle length: ";
	double length;
	cin >> length;
	cout << "Give electricy cosume per hour: ";
	double consume;
	cin >> consume;
	Appliance* a = new DishWashingMachine{ id, weigth, length, consume};
	if (this->s.addToRepo(a))
		cout << "Added succesfully";
	else
		cout << "Id already exists";
}

void UI::start()
{
	bool done = true;
	while (done) {
		this->printMenu();
		cout << "\n Choose an option: ";
		int cmd;
		cin >> cmd;
		switch (cmd)
		{
		case 0:
			cout << "BYE \n";
			done = false;
			break;
		case 1:
			this->addAppliance();
			break;
		case 2:
			this->displayAll();
			break;
		case 3:
			this->displaySorted();
			break;
		case 4:
			this->save();
			break;
		default:
			cout << "Wrong command \n";
			break;

		}
	}
}

