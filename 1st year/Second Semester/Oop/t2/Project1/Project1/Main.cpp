#include <iostream>
#include "UI.h"
#include "Person.h"
#include "Measurement.h"
#include "BMI.h"
#include "BP.h"

int main() {

	Person p{ "Ion" };
	Measurement* m = new BMI{ "2021.04.22", 19.4};
	Measurement* m1 = new BMI{ "2021.03.12", 25.6 };
	Measurement* m2 = new BMI{ "2021.02.02", 23.4 };
	Measurement* m3 = new BP{ "2021.02.12", 100, 70 };
	Measurement* m4 = new BP{ "2021.05.22", 100, 89 };
	p.addMeasurement(m);
	p.addMeasurement(m1);
	p.addMeasurement(m2);
	p.addMeasurement(m3);
	p.addMeasurement(m4);
	UI ui{ p };
	ui.start();

	return 0;

}