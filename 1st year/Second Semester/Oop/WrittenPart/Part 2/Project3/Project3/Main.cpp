#include "Icecream.h"
#include "SimpleIcecream.h"
#include "IcecreamWithTopping.h"
#include "IcecreamWithChocolateTopping.h"
#include "IceCreamWithCaramelTopping.h"
#include "Order.h"

int main() {
	SimpleIcecream i1{"vanilla", 3.0};
	SimpleIcecream i2{ "hazelnuts", 3.0 };
	SimpleIcecream i3{ "pistachio", 6.0 };
	//IcecreamWithChocolateTopping(i3);
	Icecream* i4 = new SimpleIcecream("pistachio", 6.0);
	Icecream* i4_t1 = new IcecreamWithChocolateTopping(i4);
	Icecream* i4_t2 = new IcecreamWithCaramelTopping(i4_t1);
	Icecream* i5 = new SimpleIcecream("chocolate", 5.0);
	Icecream* i5_t1 = new IcecreamWithChocolateTopping(i5);
	Order comands{};
	comands.addIcecream(&i1);
	comands.addIcecream(&i2);
	comands.addIcecream(i4_t2);
	comands.addIcecream(i5_t1);
	comands.printOrder();

	return 0;
}