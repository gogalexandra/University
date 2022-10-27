#include "Discount.h"

class SumDiscount : public Discount{
private:
    std::vector<Discount*> discounts;

public:
    void add(Discount* d);
    double calc(Sale &s) override;
};


