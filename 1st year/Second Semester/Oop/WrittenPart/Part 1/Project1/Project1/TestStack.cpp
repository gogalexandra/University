#include <string>
#include <cassert>
#include "Stack.h"
using namespace std;

void testStack() {

	Stack<string> s{ 2 };
	assert(s.getMaxCapacity() == 2);
	try {
		s = s + "examination";
		s = s + "oop";
		s = s + "test";
	}
	catch (std::exception& e) {
		assert(strcmp(e.what(), "Stack is full!") == 0);
	}
	assert(s.pop() == "oop");
	assert(s.pop() == "examination");
}

//int main() {
//	testStack();
//	return 0;
//
//}