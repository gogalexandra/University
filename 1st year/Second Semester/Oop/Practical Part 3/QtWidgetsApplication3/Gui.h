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
#include "Writer.h"
#include <QSortFilterProxyModel>

class GUI : public QWidget, Observer {
    Q_OBJECT
private:
    Writer current_writer;
    Service& s;
    TableModel* table_model;
    QTableView* table;
    QLineEdit* idea_descrption;
    QLineEdit* idea_act;
    QPushButton* add_idea;

    QSortFilterProxyModel* proxy1;

    QPushButton* remove_idea;

    QPushButton* approve_idea;

public:
    GUI(Writer& w, Service&, QWidget* parent = Q_NULLPTR);

    void update() override;
    void connect();

    void add_idea_gui();

    void remove_idea_gui();

    void approve_idea_gui();

    void update_idea_gui();
    //~GUI();

};