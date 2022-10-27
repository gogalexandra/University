#include "Pizza.h"
#include "BasicPizza.h"
#include "PizzaWithTopping.h"
#include "PizzaWithPepperoniTopping.h"
#include "PizzaWithExtraChesseTopping.h"
#include "Order.h"

int main() {
	BasicPizza p1{ "Cannibale", 10.0 };
	Pizza* p2 = new BasicPizza("Diavola", 8.0);
	Pizza* p2_t1 = new PizzaWithPepperoniTopping(p2);
	Pizza* p2_t2 = new PizzaWithExtraChesseTopping(p2_t1);

	Pizza* p3= new BasicPizza("Margherita", 6.0);
	Pizza* p3_t1 = new PizzaWithExtraChesseTopping(p3);

	BasicPizza p4{ "Mexicana", 12.0 };
	Order comands{};
	comands.addPizza(&p1);
	comands.addPizza(p2_t2);
	comands.addPizza(p3_t1);
	comands.addPizza(&p4);
	comands.printOrder();

	return 0;
}