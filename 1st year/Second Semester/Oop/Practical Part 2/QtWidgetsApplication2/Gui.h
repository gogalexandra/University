#pragma once
#include <QtWidgets/QWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QSlider>
#include <QtWidgets/QLabel>

#include <QtWidgets/QTableView>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QScrollArea>

#include <QtWidgets/QApplication>

#include "TableModel.h"
#include "Observer.h"
#include "Service.h"
#include "Programmer.h"
#include <QSortFilterProxyModel>

class GUI : public QWidget, Observer {
    Q_OBJECT
private:
    Programmer current_programmer;
    Service& s;
    TableModel* table_model;
    QTableView* table;
    QLineEdit* sourceFile_name;
    QPushButton* add_sourceFile;
    QLabel* l1;
    QLabel l2;

    QSortFilterProxyModel* proxy1;

    QPushButton* review_sourceFile;

public:
    GUI(Programmer& p, Service&, QWidget* parent = Q_NULLPTR);

    void update() override;
    void connect();

    void add_sourceFile_gui();

    void review_sourceFile_gui();

    void update_sourceFile_gui();

    void update_programmer_gui();
    //~GUI();

};