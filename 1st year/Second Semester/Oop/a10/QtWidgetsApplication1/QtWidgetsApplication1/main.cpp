#include "QtWidgetsApplication1.h"
#include <QtWidgets/QApplication>
#include "GUI2.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    QtWidgetsApplication1 w;
    GUI gui;
    gui.resize(300, 300);
    gui.show();
    return a.exec();
}
