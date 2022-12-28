#include "QtWidgetsApplication1.h"
#include <QtWidgets/QApplication>
#include "Gui.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    RepoEthnologists re;
    RepoBuildings rb;
    re.readEthnologists();
    rb.readBuildings();
    Service s{ &re, &rb };

    std::vector<Ethnologists> all_ethnologists= s.getRepoEthnologists()->getEthnologists();
    std::vector<GUI*> guis;
    for (auto& a : all_ethnologists) {
        guis.push_back(new GUI(a, s));
    }

    for (auto& a : guis) {
        a->show();
    }

    return a.exec();
}
