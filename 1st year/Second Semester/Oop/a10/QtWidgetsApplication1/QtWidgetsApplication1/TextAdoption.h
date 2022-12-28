#pragma once
#include "Adoptions.h"

class TextAdoptions:public Adoptions{
public:
	TextAdoptions(const string& file_name_input, const string& file_name_output);
	TextAdoptions(const TextAdoptions& text_repo);
	TextAdoptions() = default;
	~TextAdoptions();
	void save() override;
	void load();
	virtual void open() {};

	TextAdoptions& operator=(const TextAdoptions& adopt);

private:
	string file_name_i;
	string file_name_o;
};
