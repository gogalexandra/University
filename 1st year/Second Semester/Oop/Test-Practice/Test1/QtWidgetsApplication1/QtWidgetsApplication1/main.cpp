#include "QtWidgetsApplication1.h"
#include <QtWidgets/QApplication>
#include "Gui.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    QtWidgetsApplication1 w;
    GUI gui;
    gui.resize(700, 400);
    gui.show();
    return a.exec();
}
