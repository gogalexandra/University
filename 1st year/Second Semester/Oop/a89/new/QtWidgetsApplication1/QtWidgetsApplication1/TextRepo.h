#pragma once
#include "Repository.h"

class TextRepo :public Repository {
public: 
	TextRepo(const string& file_name_input, const string& file_name_output);
	~TextRepo();
	void save() override;
	void load();

private:
	string file_name_i;
	string file_name_o;
};

