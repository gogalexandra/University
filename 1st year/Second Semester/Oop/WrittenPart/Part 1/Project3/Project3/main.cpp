#include "Header.h"
#include <iostream>
using namespace std;

//void function2() {
//	Adder<int> add{ 5 }; // build a new Adder, with initial value 5
//	add = add + 5 + 2;	// add values 5 and 2
//	++add;		// adds the last added value (2) again
//	add + 8;	// add value 8
//	cout << add.sum() << "\n"; // print sum, so far: 22 (5+5+2+2+8)
//	--add; // eliminate last added value
//	cout << add.sum() << "\n"; // print modified sum: 14 (5+5+2+2)
//	--(--add); // eliminate the two previously added values
//	cout << add.sum() << "\n"; // print modified sum: 10
//	try {
//		--(--(--add));
//	}
//	catch (std::runtime_error& ex) {
//		cout << ex.what(); // prints "No more values!";
//	}
//}

int fct1(int x) {
	if (x % 10 == x) {
		throw std::runtime_error{ "Number contains a single digit" };
	}
	stringstream s;
	s << x;
	string str = s.str();
	int y = str.size();
	int i = 0;
	while (i < y - 1 && str[i] > str[i + 1]) {
		i++;
	}
	return (i == y - 1);
}


int main() {

	//function2();
	return 0;
}