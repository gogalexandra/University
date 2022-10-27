cmake_minimum_required(VERSION 3.19)
project(untitled)

set(CMAKE_CXX_STANDARD 20)

add_executable(untitled
        "First Part/Car.h"
        "First Part/List.h"
        "First Part/main.cpp"
        "Second Part/Sale.cpp"
        "Second Part/Sale.h"
        "Second Part/SaleItem.cpp"
        "Second Part/SaleItem.h"
        "Second Part/Discount.h"
        "Second Part/ItemDiscount.cpp"
        "Second Part/ItemDiscount.h"
        "Second Part/SumDiscount.cpp"
        "Second Part/SumDiscount.h"
        "Second Part/main.cpp")

