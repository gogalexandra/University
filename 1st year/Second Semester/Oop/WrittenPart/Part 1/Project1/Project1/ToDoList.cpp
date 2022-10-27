#include "Activity.h"
#include "ToDo.h"

void ToDoList() {
	ToDo<Activity> todo{};
	Activity tiff{ "go to TIFF movie", "20:00" };
	todo += tiff;
	Activity project{ "present project assigment", "09:20" };
	todo += project;


	for (auto a : todo)
		std::cout << a << '\n';

	todo.reversePrint(std::cout);
}

int main() {
	ToDoList();
	return 0;
}