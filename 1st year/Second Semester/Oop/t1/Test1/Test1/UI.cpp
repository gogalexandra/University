#include "UI.h"
#include <string>
#include <iostream>

using namespace std;

void UI::printMenu() {
	cout << endl;
	cout << "0. Exit." << endl;
	cout << "1. Add gene" << endl;
	cout << "2. Display all genes" << endl;
	cout << "3. Display genes with a specific sequence" << endl;
	cout << "4. Display longest common subsequence" << endl;
}

void UI::addGeneToRepo()
{
	cout << "Give organism: ";
	string organism;
	getline(cin, organism);
	cout << "Give name: ";
	string name;
	getline(cin, name);
	cout << "Give sequence: ";
	string sequence;
	getline(cin, sequence);

	if (this->s.addGeneToRepo(organism, name, sequence))
		cout << "Adeed successfully \n";
	else
		cout << "Gene with this organism and name already exists! \n";
}

void UI::displayAllGenesFromRepo()
{
	if (this->s.getRepo().getNrElements() == 0) {
		cout << "There are no genes!\n";
		return;
	}

	for (auto& it : this->s.getRepo().getAll()) {
		for (auto& it2 : this->s.getRepo().getAll()) {
			if (it.getSequence().length() < it2.getSequence().length())
			{
				UI::swap(&it, &it2);
			}
		}
	}
	int i = 1;

	for (auto& it : this->s.getRepo().getAll()) {
		cout << "Genes " + to_string(i) + ": " + it.toString() << endl;
		i++;
	}
}

void UI::displaySpecificGenesFromRepo()
{
	cout << "Give subsequence: ";
	string given_sequence;
	getline(cin, given_sequence);
	STLVector geness = this->s.filteredGenes(given_sequence);
	if (geness.getNrElements() == 0) {
		cout << "Nothing to print! \n";
	}
	else{

		int i = 1;
		for (auto& it : geness.getAll()) {
			cout <<  it.toString() << endl;
			i++;
		}
	}
	
}

void UI::swap(Gene *it, Gene *it2)
{
	Gene temp = *it;
	*it = *it2;
	*it2 = temp;
}

void UI::start()
{
	bool done = true;
	while (done) {
		UI::printMenu();
		int command;
		cout << "Choose command: ";
		cin >> command;
		cin.ignore();
		
		if (command == 0) {
			cout << "Bye-Bye!";
			done = false;
		}
		else {
			if (command == 1)
				this->addGeneToRepo();
			else
				if (command == 2)
					this->displayAllGenesFromRepo();
				else
					if (command == 3) {
						this->displaySpecificGenesFromRepo();
					}
					else
						if (command == 4) {

						}
						else
							cout << "Wrong command! \n";
		
		}
	}
}

