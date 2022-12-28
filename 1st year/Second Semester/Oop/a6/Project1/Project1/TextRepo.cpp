#include "TextRepo.h"
#include <fstream>
#include <sstream>

TextRepo::TextRepo(const string& file_name_input, const string& file_name_output)
{
	this->file_name_i = file_name_input;
	this->file_name_o = file_name_output;
	this->load();
}

TextRepo::~TextRepo()
{
}

void TextRepo::save()
{
	ofstream output(this->file_name_o);
	output << *this;
	output.close();
}

void TextRepo::load()
{
    ifstream input(this->file_name_i, 0);
	string line;
	while (std::getline(input, line))
	{
		std::istringstream iss(line);
		Dog d;
		if (!(iss >> d)) { break; } // error

		this->addDog(d);
	}
}
