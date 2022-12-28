#pragma once
#include "TextAdoption.h"

class Html2: public TextAdoptions {
public:
    Html2();
    ~Html2();

    void save() override;
    void open() override;
};