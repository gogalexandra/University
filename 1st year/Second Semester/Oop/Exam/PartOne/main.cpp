#include <iostream>
#include "Examination.h"
#include "Maximum.h"
#include <vector>

void function()
{
    std::vector<int> v1{ 6, 3, 9, 2 };
    std::cout<<maximum<int>(v1); // Prints 9

    std::vector<Examination> v2{};

    Examination examinationFP{ "FP", 8 };
    std::cout<<examinationFP; // Prints: To the examination for discipline FP the student obtained grade: 8.
    Examination examinationOOP{ "OOP", 9 };
    Examination examinationDS{ "DS" };

    std::cout<<examinationDS; // Prints: Student did not participate to the examination for discipline DS.

    assert (examinationOOP > examinationFP);

    v2.push_back(examinationFP);
    v2.push_back(examinationOOP);
    v2.push_back(examinationDS);

    std::cout<<maximum<Examination>(v2); // Prints: To the examination for discipline OOP the student obtained the grade: 9.
}

//int main() {
//    std::cout << "---Testing---" << std::endl;
//    function();
//    return 0;
//}
