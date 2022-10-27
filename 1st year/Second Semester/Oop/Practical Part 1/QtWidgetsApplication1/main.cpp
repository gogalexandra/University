#include "QtWidgetsApplication1.h"
#include <QtWidgets/QApplication>
#include "Gui.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    RepoIssue ri;
    RepoUser ru;
    ri.readIssues("issues.txt");
    ru.readUsers("users.txt");
    Service s{&ru, &ri};

    std::vector<User> all_users = s.getRepoUser()->getUsers();
    std::vector<GUI*> guis;
    for (auto& a : all_users) {
        guis.push_back(new GUI(a, s));
    }

    for (auto& a : guis) {
        a->show();
    }

    return a.exec();
}
