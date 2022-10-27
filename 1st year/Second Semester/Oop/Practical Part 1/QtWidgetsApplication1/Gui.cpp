
#include "GUI.h"
#include "QMessageBox"

GUI::GUI(User& u, Service& s, QWidget* parent) : current_user(u), QWidget(parent), s(s) {
    this->setWindowTitle(QString::fromStdString(u.getName() + " is " + u.getType()));
    this->s.addObserver(this);

    QVBoxLayout* mainLayout = new QVBoxLayout();
    this->setLayout(mainLayout);

    this->table_model = new TableModel(this->s.getRepoIssue()->getIssues());
    this->table = new QTableView();
    this->table->setModel(this->table_model);

    proxy1 = new QSortFilterProxyModel{};
    proxy1->setSourceModel(this->table_model);
    this->table->setSortingEnabled(true);
    proxy1->setFilterKeyColumn(0);
    proxy1->sort(0);
    proxy1->sort(1);
    this->table->setModel(proxy1);


    this->issue_descrption = new QLineEdit();
    this->issue_descrption->setPlaceholderText("Description of the issue here");
    this->add_issue = new QPushButton("Add issue");


    this->remove_issue = new QPushButton("Remove issue");
    mainLayout->addWidget(this->remove_issue);

    this->resolve_issue = new QPushButton("Resolve issue");

    mainLayout->addWidget(this->table);
    if (u.getType() == "tester") {

        mainLayout->addWidget(this->issue_descrption);
        mainLayout->addWidget(this->add_issue);
    }
    else {
        this->resolve_issue->setDisabled(true);
        mainLayout->addWidget(this->resolve_issue);
    }

    this->connect();

}

void GUI::update() {
    this->table_model->update(this->s.getRepoIssue()->getIssues());
}

void GUI::connect() {
    QObject::connect(this->add_issue, &QPushButton::clicked, this, &GUI::add_issue_gui);
    QObject::connect(this->remove_issue, &QPushButton::clicked, this, &GUI::remove_issue_gui);
    QObject::connect(this->table, &QTableView::clicked, this, &GUI::resolve_issue_gui);
    QObject::connect(this->resolve_issue, &QPushButton::clicked, this, &GUI::update_issue_gui);
}

void GUI::add_issue_gui() {
    try {
        Issue i = Issue(this->issue_descrption->text().toStdString(), "open", this->current_user.getName(), "");
        this->s.add_issue(i.getDescription(), i.getStatus(), i.getReporter(), i.getSolver());
        this->issue_descrption->clear();
        this->update();
    }
    catch (...) {
        QMessageBox::information(this, "Error!", QString::fromStdString("already existing"));
    }

}

void GUI::remove_issue_gui() {
    QModelIndex index = this->table->selectionModel()->currentIndex();
    std::string id_to_remove = this->proxy1->index(index.row(), 1).data().toString().toStdString();

    if (id_to_remove == "closed") {
        int id = this->s.get_index_issue_service(this->proxy1->index(index.row(), 0).data().toString().toStdString());
        if (id != -1) {
            this->s.remove_issue(id);
            this->update();
        }
        else {
            QMessageBox::information(this, "Error!", QString::fromStdString("not existing"));
        }
    }
    else {
        QMessageBox::information(this, "Error!", QString::fromStdString("not closed"));
    }
}

void GUI::resolve_issue_gui() {
    QModelIndex index = this->table->selectionModel()->currentIndex();
    std::string id_selected = this->proxy1->index(index.row(), 1).data().toString().toStdString();

    if (id_selected == "open") {
        this->resolve_issue->setDisabled(false);
    }
    else {
        this->resolve_issue->setDisabled(true);
    }

}

void GUI::update_issue_gui() {
    QModelIndex index = this->table->selectionModel()->currentIndex();
    std::string id_selected = this->proxy1->index(index.row(), 1).data().toString().toStdString();

    this->s.update_issue(this->proxy1->index(index.row(), 0).data().toString().toStdString(),
        "closed",
        this->proxy1->index(index.row(), 2).data().toString().toStdString(),
        this->current_user.getName());
}
