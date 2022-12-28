#include <iostream>
#include "UI.h"
#include "Service.h"
#include "Repository.h"


int main() {
	
	Repository repo{};
	Appliance* a = new Refrigerator{ "1", 50.2, "A+", true };
	cout << a->toString() << endl;
	repo.add(a);
	Service serv{ repo };
	UI ui{ serv };
	ui.start();

	return 0;

}