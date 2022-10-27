#include "QtWidgetsApplication3.h"
#include <QtWidgets/QApplication>
#include "Gui.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    RepoIdea ri;
    RepoWriter rw;
    ri.readIdeas();
    rw.readWriters();
    Service s{ &rw, &ri };

    std::vector<Writer> all_users = s.getRepoWriter()->getWriters();
    std::vector<GUI*> guis;
    for (auto& a : all_users) {
        guis.push_back(new GUI(a, s));
    }

    for (auto& a : guis) {
        a->show();
    }

    return a.exec();
}
