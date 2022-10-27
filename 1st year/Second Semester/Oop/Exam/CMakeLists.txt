cmake_minimum_required(VERSION 3.19)
project(Exam)

set(CMAKE_CXX_STANDARD 20)

add_executable(Exam
        "PartOne/main.cpp"
        "PartTwo/main.cpp"
        "PartOne/Examination.h"
        "PartOne/Maximum.h"
        "PartTwo/fct.h"
        "PartThree/Pizza.h"
        "PartThree/BasicPizza.h"
        "PartThree/PizzaWithTopping.h"
        "PartThree/PizzaWithCheeseTopping.h"
        "PartThree/PizzaWithPepperoniTopping.h"
        "PartThree/Order.h"
        "PartThree/main.cpp")