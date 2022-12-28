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
#include "Ethnologists.h"
#include <QSortFilterProxyModel>

class GUI : public QWidget, Observer {
    Q_OBJECT
private:
    Ethnologists current_ethnologists;
    Service& s;
    TableModel* table_model;
    QTableView* table;
    
    QLineEdit* building_id;
    QLineEdit* building_descrption;
    QLineEdit* building_location;

    QPushButton* add_building;
    QPushButton* update_building;

    QSortFilterProxyModel* proxy1;

public:
    GUI(Ethnologists& e, Service& s, QWidget* parent = Q_NULLPTR);

    void update() override;
    void connect();

    void add_building_gui();

    void update_building_gui();
    //~GUI();

};