#include "UI.h"

void UI::printMenu()
{
	cout << endl;
	cout << "0. Exit." << endl;
	cout << "1. Add measurement" << endl;
	cout << "2. Display all" << endl;
	cout << "3. Check if healthy" << endl;
	cout << "4. Save" << endl;
}

void UI::add()
{
	cout << "\n	a. Add BMI";
	cout << "\n	b. Add BP";
	cout << "\n ---> ";
	string op;
	cin >> op;
	if (op == "BMI")
		this->addBMI();
	else
		if (op == "BP")
			this->addBP();
		else
			cout << "\n Type not correct";
}

void UI::displayAll()
{
	cout << "Name: " + p.getName() << endl;
	if (this->p.getMeasuremnts().size() == 0) {
		cout << "\n Nothing to display" << endl;
	}
	else
		for (auto& it : this->p.getMeasuremnts()) {
			cout << "\n" + it->toString() << endl;
		}
}

void UI::addBMI()
{
	cout << "Give date: ";
	string date;
	cin >> date;
	if (date.size() != 10)
		cout << "\n Date not valid";
	else {
		cout << "Give value: ";
		double value;
		cin >> value;
		Measurement* m = new BMI{ date, value };
		this->p.addMeasurement(m);
		if (m->isNormalValue())
			cout << "\n Results: ok";
		else
			cout << "\n Results: not ok";
	}
}

void UI::addBP()
{
	cout << "Give date: ";
	string date;
	cin >> date;
	if (date.size() != 10)
		cout << "\n Date not valid";
	else {
		cout << "Give systolic value: ";
		int sv;
		cin >> sv;
		cout << "Give diastolic value: ";
		int dv;
		cin >> dv;
		Measurement* m = new BP{ date, sv, dv };
		this->p.addMeasurement(m);
		if (m->isNormalValue())
			cout << "\n Results: ok";
		else
			cout << "\n Results: not ok";
	}
}

void UI::check()
{
	cout << "Give month: ";
	int m;
	cin >> m;
	this->p.isHealthy(m);
}

void UI::save()
{
	cout << "Give date: ";
	string date;
	cin >> date;
	cout << "Give file name where to be save: ";
	string file;
	cin >> file;
	this->p.WriteToFile(file, date);
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
			this->add();
			break;
		case 2:
			this->displayAll();
			break;
		case 3:
			this->check();
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