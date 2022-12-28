#include "TextAdoption.h"
#include <fstream>
#include <sstream>


TextAdoptions::TextAdoptions(const string& file_name_input, const string& file_name_output)
{
	this->file_name_i = file_name_input;
	this->file_name_o = file_name_output;
	this->load();
}

TextAdoptions::TextAdoptions(const TextAdoptions& text_adopt)
{
	this->file_name_i = text_adopt.file_name_i;
	this->file_name_o = text_adopt.file_name_o;
	this->adoptions = text_adopt.adoptions;
}

TextAdoptions::~TextAdoptions()
{
}

void TextAdoptions::save()
{
	ofstream output(this->file_name_o);
	/*vector<Dog> all_dogs = this->getAdoptionsList().getAll();
	vector<Dog> ::iterator it = all_dogs.begin();
	Dog d = all_dogs[all_dogs.size() - 1];
	for (auto& it : all_dogs) {
		if (it.getName() == d.getName())
			output << it.getBreed() + " " + it.getName() + " " + to_string(it.getAge()) + " " + it.getPhotograph();
		else
			output << it.getBreed() + " " + it.getName() + " " + to_string(it.getAge()) + " " + it.getPhotograph() + "\n";
	}*/
	output << *this;

	output.close();
}

void TextAdoptions::load()
{
	/*ifstream input(this->file_name_i, 0);
	vector<Dog> all_dogs;
	while (input.peek() != EOF) {
		string breed, name, age, photo;
		input >> breed >> name >> age >> photo;
		Dog d(breed, name, stoi(age), photo);
		all_dogs.push_back(d);
		
	}*/

	ifstream input(this->file_name_i, 0);
	string line;
	vector<Dog> all_dogs;
	while (std::getline(input, line))
	{
		std::istringstream iss(line);
		Dog d;
		if (!(iss >> d)) { break; } // error

		all_dogs.push_back(d);
	}

	for (auto& it : all_dogs) {
		this->add(it);
	}

}

TextAdoptions& TextAdoptions::operator=(const TextAdoptions& adopt)
{
	this->file_name_i = adopt.file_name_i;
	this->file_name_o = adopt.file_name_o;
	this->adoptions = adopt.adoptions;
	return *this;
}
