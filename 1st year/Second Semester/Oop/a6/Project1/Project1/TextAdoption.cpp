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
	output << *this;
	output.close();
}

void TextAdoptions::load()
{
	ifstream input(this->file_name_i, 0);
	string line;
	while (std::getline(input, line))
	{
		std::istringstream iss(line);
		Dog d;
		if (!(iss >> d)) { break; } // error
		this->add(d);
	}

}

TextAdoptions& TextAdoptions::operator=(const TextAdoptions& adopt)
{
	this->file_name_i = adopt.file_name_i;
	this->file_name_o = adopt.file_name_o;
	this->adoptions = adopt.adoptions;
	return *this;
}
