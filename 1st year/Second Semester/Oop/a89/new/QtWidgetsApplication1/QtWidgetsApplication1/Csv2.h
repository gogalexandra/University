#pragma once
#include "TextAdoption.h"

class Csv : public TextAdoptions {
public:
    Csv();
    ~Csv();

    void save() override;
    void open() override;
};