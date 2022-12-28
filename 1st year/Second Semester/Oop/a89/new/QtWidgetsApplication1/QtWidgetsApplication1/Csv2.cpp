#include <fstream>
#include <Windows.h>
#include <shellapi.h>
#include "Csv2.h"

using namespace std;

Csv::Csv()
{
}

Csv::~Csv()
{
}

void Csv::save() {
    std::ofstream output("Adoptions.csv");
    output << *this;
    output.close();
}

void Csv::open() {
    ShellExecuteA(NULL, NULL, "notepad.exe", "Adoptions.csv", NULL, SW_SHOWMAXIMIZED);
    system("PAUSE");
}