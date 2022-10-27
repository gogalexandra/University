
#include "GUI.h"
#include "QMessageBox"

GUI::GUI(Writer& w, Service& s, QWidget* parent) : current_writer(w), QWidget(parent), s(s) {
    this->setWindowTitle(QString::fromStdString(w.getName() + " is " + w.getExpertize()));
    this->s.addObserver(this);

    QVBoxLayout* mainLayout = new QVBoxLayout();
    this->setLayout(mainLayout);

    this->table_model = new TableModel(this->s.getRepoIdea()->getIdeas(), curr);
    this->table = new QTableView();
    this->table->setModel(this->table_model);

    proxy1 = new QSortFilterProxyModel{};
    proxy1->setSourceModel(this->table_model);
    this->table->setSortingEnabled(true);
    proxy1->setFilterKeyColumn(0);
    proxy1->sort(0);
    proxy1->sort(1);
    this->table->setModel(proxy1);


    this->idea_descrption = new QLineEdit();
    this->idea_descrption->setPlaceholderText("Description of the idea here");

    this->idea_act = new QLineEdit();
    this->idea_act->setPlaceholderText("Act of the idea here");
    this->add_idea = new QPushButton("Add idea");


    this->remove_idea = new QPushButton("Remove idea");
    mainLayout->addWidget(this->remove_idea);

    this->approve_idea = new QPushButton("Aprove idea");

    mainLayout->addWidget(this->table);
    mainLayout->addWidget(this->approve_idea);
    mainLayout->addWidget(this->idea_descrption);
    mainLayout->addWidget(this->idea_act);
    mainLayout->addWidget(this->add_idea);
    mainLayout->addWidget(this->remove_idea);
    if (w.getExpertize() == "senior") {
        this->approve_idea->setDisabled(false);   
        this->remove_idea->setDisabled(false);
    }
    else {
        this->approve_idea->setDisabled(true);
        this->remove_idea->setDisabled(true);
    }

    this->connect();

}

void GUI::update() {
    this->table_model->update(this->s.getRepoIdea()->getIdeas());
}

void GUI::connect() {
    QObject::connect(this->add_idea, &QPushButton::clicked, this, &GUI::add_idea_gui);
    QObject::connect(this->remove_idea, &QPushButton::clicked, this, &GUI::remove_idea_gui);
    QObject::connect(this->table, &QTableView::clicked, this, &GUI::approve_idea_gui);
    QObject::connect(this->approve_idea, &QPushButton::clicked, this, &GUI::update_idea_gui);
}

void GUI::add_idea_gui() {
    try {
        Idea i = Idea(this->idea_descrption->text().toStdString(), "proposed", this->current_writer.getName(), this->idea_act->text().toInt());
        this->s.add_idea(i.getDescription(), i.getStatus(), i.getCreator(), i.getAct());
        this->idea_descrption->clear();
        this->idea_act->clear();
        this->update();
    }
    catch (...) {
        QMessageBox::information(this, "Error!", QString::fromStdString("Something went wrong!"));
    }

}

void GUI::remove_idea_gui() {
    QModelIndex index = this->table->selectionModel()->currentIndex();
    std::string id_to_remove = this->proxy1->index(index.row(), 1).data().toString().toStdString();

    if (id_to_remove == "proposed") {
        int id = this->s.get_index_idea_service(this->proxy1->index(index.row(), 0).data().toString().toStdString(), this->proxy1->index(index.row(), 3).data().toString().toInt());
        if (id != -1) {
            this->s.remove_idea(id);
            this->update();
        }
        else {
            QMessageBox::information(this, "Error!", QString::fromStdString("not existing"));
        }
    }
    else {
        QMessageBox::information(this, "Error!", QString::fromStdString("Can not delete"));
    }
}

void GUI::approve_idea_gui() {
    QModelIndex index = this->table->selectionModel()->currentIndex();
    std::string id_selected = this->proxy1->index(index.row(), 1).data().toString().toStdString();

    if (id_selected == "accepted") {
        this->approve_idea->setDisabled(true);
    }
    else {
        this->approve_idea->setDisabled(false);
    }

}

void GUI::update_idea_gui() {
    QModelIndex index = this->table->selectionModel()->currentIndex();
    std::string id_selected = this->proxy1->index(index.row(), 1).data().toString().toStdString();

    this->s.update_idea(this->proxy1->index(index.row(), 0).data().toString().toStdString(),
        "accepted",
        this->proxy1->index(index.row(), 2).data().toString().toStdString(),
        this->proxy1->index(index.row(), 3).data().toString().toInt());
}
