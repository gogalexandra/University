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

class Statistics : public QWidget, Observer {
    Q_OBJECT
private:
    Programmer current_programmer;
    Service& s;

public:
    Statistics(Programmer& p, Service&, QWidget* parent = Q_NULLPTR);

    void update() override;

    //void update_statistics_gui();

    //~GUI();

};