#include "QtWidgetsApplication2.h"
#include <QtWidgets/QApplication>
#include "Gui.h"
#include "Statistics.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    RepoProgrammer rp;
    RepoSourceFile rs;
    rp.readProgrammers();
    rs.readSourceFiles();
    Service s{ &rp, &rs };

    std::vector<Programmer> all_programmers= s.getRepoProgrammer()->getProgrammers();
    std::vector<GUI*> guis;
    for (auto& a : all_programmers) {
        guis.push_back(new GUI(a, s));
    }

    std::vector<Statistics*> stat;
    for (auto& a : all_programmers)
        stat.push_back(new Statistics(a, s));
    for (auto& a : stat) {
        a->show();
    }

    for (auto& a : guis) {
        a->show();
    }

    return a.exec();
}
