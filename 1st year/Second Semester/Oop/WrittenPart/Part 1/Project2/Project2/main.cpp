#include "Car.h"
#include "List.h"
#include "Examination.h"
#include "maximum.h"
#include "Complex.h"
#include "Vector.h"
#include "SmartPointer.h"
#include <cassert>
#include <stdexcept>
using namespace std;

//void function1() {
//	List<Car, int> list1;
//	try {
//		list1.sortByFirstComponent();
//		assert(false);
//	}
//	catch (runtime_error& e) { assert(strcmp(e.what(), "List is empty!") == 0); }
//
//	list1.add(Car{ "Audi", 20 }, 8);
//	list1.add(Car{ "Volkswagen", 10 }, 9);
//	list1.add(Car{ "Bentley", 300 }, 10);
//
//	list1.sortByFirstComponent();
//	list1.printReverse(std::cout); // prints: Bentley 300 -> 10; Audi 20 -> 8; Volkswagen 10 -> 9 
//	list1.sortBySecondComponent();
//	list1.printReverse(std::cout); // prints: Bentley 300 -> 10; Volkswagen 10 -> 9; Audi 20 -> 8;
//}

//void function()
//{
//	std::vector<int> v1{ 6, 3, 9, 2 };
//	std::cout << maximum<int>(v1) << "\n"; // Prints 9
//
//	std::vector<Examination> v2{};
//
//	Examination examinationFP{ "FP", 8 };
//	std::cout << examinationFP; // Prints: To the examination for discipline FP the student obtained grade: 8.
//	Examination examinationOOP{ "OOP", 9 };
//	Examination examinationDS{ "DS" };
//
//	std::cout << examinationDS; // Prints: Student did not participate to the examination for discipline DS.
//
//	assert(examinationOOP > examinationFP);
//
//	v2.push_back(examinationFP);
//	v2.push_back(examinationOOP);
//	v2.push_back(examinationDS);
//
//	std::cout << maximum<Examination>(v2); // Prints: To the examination for discipline OOP the student obtained the grade: 9.
//}

//void complex() {
//	Complex a{}, b{ 1, 2 }, c{ 6,4 }, d{ b };
//	assert(a.getReal() == 0 && a.getImaginary() == 0);
//	assert(c.getImaginary() == 4);
//	assert(b == d);
//	Complex res1 = c / 2;
//	cout << res1 << "\n"; //print 3+2i
//	try {
//		Complex res2 = b / 0;
//	}
//	catch (runtime_error& e) {
//		assert(strcmp(e.what(), "Division by zero!") == 0);
//	}
//	Vector<string> v1{ std::vector<string>{"hello", "bye"} };
//	v1.printAll(std::cout);
//
//	Vector<Complex> v2{ std::vector<Complex>{a, b, c, d} };
//	v2.printAll(std::cout);
//
//}

void function2() {
	SmartPointer<int> i1{ new int {1} };
	SmartPointer<int> i2{ new int {2} };
	SmartPointer<int> i3{ new int {3} };
	
	Vector<SmartPointer<int>> v1{};
	v1.add(i1).add(i2).add(i3);
	for (auto e : v1) {
		//cout << *e << ", "; //Prints 1, 2, 3,
	
	}
	SmartPointer<string> s1{ new string{"A"} };
	SmartPointer<string> s2 = s1;
	SmartPointer<string> s3{ new string{"c"} };

	Vector<SmartPointer<string>> v2{};
	v2.add(s2).add(s1);
	try {
		v2 = v2 - s2;
		v2 = v2 - s3;
	}
	catch (std::exception& ex) {
		cout << ex.what(); //Prints Element does not exist!
	}
}

int main() {
	//function1();
	//function();
	//complex();
	function2();
	return 0;
}