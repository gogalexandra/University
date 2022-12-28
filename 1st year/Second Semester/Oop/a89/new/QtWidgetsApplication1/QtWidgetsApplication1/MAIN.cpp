#include "QtWidgetsApplication1.h"
#include <QtWidgets/QApplication>
#include <QLabel>
#include "GUI2.h"
#include "ui.h"
#include <QApplication>
#include <QLabel>
#include <QLineEdit>
#include <QPushButton>
#include <QListWidget>
#include <QHBoxLayout>
#include <QFormLayout>

int main(int argc, char* argv[])
{
    QApplication a(argc, argv);
    //QtGUIApplication w;
   /* QLabel label{ "Hello world!" };
    label.show();*/
    //w.show();
    GUI gui;
    gui.resize(300, 300);
    gui.show();
    return a.exec();

}