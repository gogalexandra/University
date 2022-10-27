cmake_minimum_required(VERSION 3.19)
project(ExamPractice2)

set(CMAKE_CXX_STANDARD 20)

add_executable(ExamPractice2
        "PartOne/Activity.h"
        "PartOne/ToDo.h"
        "PartOne/main.cpp"
        "PartTwo/main.cpp"
        "PartTwo/Beverage.h"
        "PartTwo/BeverageMachine.h"
        "PartTwo/BeverageWithMilk.h"
        "PartTwo/Coffee.h"
        "PartTwo/Tea.h")