#include <fstream>
#include <Windows.h>
#include <shellapi.h>
#include "Csv2.h"

using namespace std;

Csv::Csv()
{
    //this->load();
}

Csv::~Csv()
{
}

void Csv::save() {
    std::ofstream output("Adoptions.csv");
    output << *this;
    output.close();

    ofstream output2("adoptions.txt");
    output2 << *this;
    output2.close();
}

void Csv::open() {
    /*ShellExecuteA(NULL, NULL, "notepad.exe", "Adoptions.csv", NULL, SW_SHOWMAXIMIZED);
    system("PAUSE");*/
}