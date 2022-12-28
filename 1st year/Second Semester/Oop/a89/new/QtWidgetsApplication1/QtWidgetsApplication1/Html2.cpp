#include "Html2.h"
#include <Windows.h>
#include <shellapi.h>
#include <fstream>

Html2::Html2()
{
}


Html2::~Html2()
{
}

void Html2::save()
{
    std::ofstream f("list.html");

    f << "<!DOCTYPE html>" << "\n";
    f << "<html>" << "\n";

    f << "<head>" << "\n";
    f << "<title>Adoption List</title>" << "\n";
    f << "</head>" << "\n";

    f << "<body>" << "\n";

    f << "<table border=\"1\">" << "\n";

    f << "<tr>" << "\n";
    f << "<td>Dog's name</td>" << "\n";
    f << "<td>Breed</td>" << "\n";
    f << "<td>Age</td>" << "\n";
    f << "<td>Photo</td>" << "\n";
    f << "</tr>" << "\n";

    for (auto i : this->getAdoptionsList().getAll())
    {
        f << "<tr>" << "\n";

        f << "<td>" << i.getName() << "</td>" << "\n";
        f << "<td>" << i.getBreed() << "</td>" << "\n";
        f << "<td>" << i.getAge() << "</td>" << "\n";
        f << "<td><a href =" << i.getPhotograph() << ">Link</a></td>";

        f << "</tr>" << "\n";
    }

    f << "</table>" << "\n";

    f << "</body>" << "\n";

    f << "</html>" << "\n";
    f.close();

}

void Html2::open()
{
    ShellExecuteA(NULL, NULL, "chrome.exe", "adoptions.html", NULL, SW_SHOWMAXIMIZED);
    system("PAUSE");
}