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
#include "User.h"
#include <QSortFilterProxyModel>

class GUI : public QWidget, Observer {
    Q_OBJECT
private:
    User current_user;
    Service& s;
    TableModel* table_model;
    QTableView* table;
    QLineEdit* issue_descrption;
    QPushButton* add_issue;

    QSortFilterProxyModel* proxy1;

    QPushButton* remove_issue;

    QPushButton* resolve_issue;

public:
    GUI(User& u, Service&, QWidget* parent = Q_NULLPTR);

    void update() override;
    void connect();

    void add_issue_gui();

    void remove_issue_gui();

    void resolve_issue_gui();

    void update_issue_gui();
    //~GUI();

};