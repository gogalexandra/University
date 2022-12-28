#include "UI.h"
#include <string>
#include <iostream>

using namespace std;


void UI::printMenu() {
	cout << endl;
	cout << "0. Exit." << endl;
	cout << "1. Administrator mode" << endl;
	cout << "2. User mode" << endl;
}


void UI::printAdministratorMenu() {
	cout << "\nAvailable commands:" << endl;
	cout << "\t0. Exit" << endl;
	cout << "\t1. Add a dog" << endl;
	cout << "\t2. Update a dog" << endl;
	cout << "\t3. Delete a dog" << endl;
	cout << "\t4. Show all dogs" << endl;

}


void UI::printUserMenu() {
	cout << "\nAvailable commands:" << endl;
	cout << "\t0. Exit" << endl;
	cout << "\t1. Show all adoptions" << endl;
	cout << "\t2. Filter possible dogs" << endl;
	cout << "\t3. Adopt" << endl;

}


void UI::addDogToRepo() {
	cout << "Give breed: ";
	string breed;
	getline(cin, breed);
	cout << "Give name: ";
	string name;
	getline(cin, name);
	int age = 0;
	cout << "Give age: ";
	cin >> age;
	cin.ignore();
	cout << "Give photography: ";
	string photography;
	getline(cin, photography);

	if (this->s.addDogToRepo(breed, name, age, photography))
		cout << "Adeed successfully \n";
	else
		cout << "Dog with this name already exists! \n";
}


void UI::updateDogFromRepo() {
	cout << "Give breed: ";
	string breed;
	getline(cin, breed);
	cout << "Give name: ";
	string name;
	getline(cin, name);
	int age = 0;
	cout << "Give age: ";
	cin >> age;
	cin.ignore();
	cout << "Give photography: ";
	string photography;
	getline(cin, photography);

	if (this->s.updateDogFromRepo(breed, name, age, photography)) {
		cout << "Updated successufully \n";
	}
	else
		cout << "No such dog \n";
}


void UI::deleteDogFromRepo() {
	cout << "Give name: ";
	string name;
	getline(cin, name);
	if (this->s.deleteDogFromRepo(name)) {
		cout << "Deleted successufully \n";
	}
	else
		cout << "No such dog \n";
}


void UI::displayAllDogsFromRepo() {

	if (this->s.getRepo().getNrElements() == 0) {
		cout << "There are no more doggies!\n";
		return;
	}
	int i = 1;
	for (auto& it : this->s.getRepo().getAll()) {
		cout << "Doggy " + to_string(i) + ": " + it.toString() << endl;
		i++;
	}
}


void UI::displayAllAdoptionsFromRepo() {

	if (this->s.getAdoptions().getNrElements() == 0) {
		cout << "There are no adopted dogs :( !\n";
		return;
	}
	int i = 1;
	for (auto& it : this->s.getAdoptions().getAll()) {
		string completeUrl = it.getPhotograph();
		cout << "Doggy " + to_string(i) + ": " + it.toStringV2() << endl;
		system(std::string("start " + completeUrl).c_str());
		i++;
	}
}


void UI::filterDogs()
{
	string given_breed;
	int given_age;
	cout << "Give a breed: ";
	getline(cin, given_breed);
	cout << "Give an age: ";
	cin >> given_age;
	STLVector filteredDogs = this->s.filterDogsFromRepo(given_breed, given_age);
	if (filteredDogs.getNrElements() == 0) {
		cout << "No such dogs!";
	}
	else 
		adoptDogs(filteredDogs);
}


void UI::adoptDogs(STLVector dv)
{
	STLVector dogsToAdopt = dv;
	bool done = true;
	int i = 0;
	string option;

	Dog doggy;
	while (done) {
		if (i == dogsToAdopt.getNrElements()) {
			i = 0;
		}
		doggy = dogsToAdopt.getAll()[i];

		cout << doggy.toStringV2() << endl;
		string completeUrl = doggy.getPhotograph();
		system(std::string("start " + completeUrl).c_str());

		cout << "Adopt, pass or done?   ";
		cin >> option;
		if (option == "adopt") {
			this->s.adoptDogToRepo(doggy.getBreed(), doggy.getName(), doggy.getAge(), doggy.getPhotograph());
			cout << "\n";
		}
		else
			if (option == "done") {
				cout << "Exit adoption";
				cout << "\n";
				done = false;
			}
		cout << "\n";
		i++;
	}

}


void UI::start() {
	bool done = true;

	while (done)
	{
		UI::printMenu();
		int command{ 0 };
		cout << "Choose an operating mode: ";
		cin >> command;
		cin.ignore();

		if (command == 0) {
			cout << "Bye-Bye!" << endl;
			done = false;
		}
		else 
			if (command == 1) {
				// administrator mode

				bool adm_mode = true;

				while (adm_mode) {
					UI::printAdministratorMenu();
					int commandRepo{ 0 };
					cout << "Choose command: ";
					cin >> commandRepo;
					cin.ignore();
					switch (commandRepo) {
					case 0:
						cout << "Left administrator mode \n";
						adm_mode = false;
						break;
					case 1:
						this->addDogToRepo();
						break;
					case 2:
						this->updateDogFromRepo();
						break;
					case 3:
						this->deleteDogFromRepo();
						break;
					case 4:
						this->displayAllDogsFromRepo();
						break;
					default:
						cout << "Wrong command \n";
						break;
					}

				}
			}
			else {
				if (command == 2) {
					bool user_mode = true;
					while (user_mode) {
						UI::printUserMenu();
						int user_cmd;
						cout << "\n Choose command: ";
						cin >> user_cmd;
						cin.ignore();
						switch (user_cmd) {
						case 0:
							cout << "Left user mode \n";
							user_mode = false;
							break;
						case 1:
							this->displayAllAdoptionsFromRepo();
							break;
						case 2:
							this->filterDogs();
							break;
						case 3:
							this->adoptDogs(this->s.getRepo());
							break;
						default:
							cout << "Wrong command \n";
							break;
						}
					}

				}
				else 
					cout << "Wrong command \n";
				
			}
	}
}
