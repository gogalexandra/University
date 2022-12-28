#pragma once
#include "TextAdoption.h"

class HtmlRepo : public TextAdoptions {
public:
    HtmlRepo();
    ~HtmlRepo();

    void save() override;
    void open() override;
};