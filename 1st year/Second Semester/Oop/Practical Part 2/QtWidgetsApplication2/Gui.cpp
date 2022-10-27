#include "Gui.h"
#include "QMessageBox"

GUI::GUI(Programmer& p, Service& s, QWidget* parent) : current_programmer(p), QWidget(parent), s(s) {
    this->setWindowTitle(QString::fromStdString(p.getName()));
    this->s.addObserver(this);

    QVBoxLayout* mainLayout = new QVBoxLayout();
    this->setLayout(mainLayout);

    QLabel* l1 = new QLabel();
    QLabel* l2 = new QLabel();

    QString qstr = QString::fromStdString("Number of revised files: " + to_string(p.getRevisedFiles()));
    QString qstr2 = QString::fromStdString("Number of files that must be revised: " + to_string(p.getTotalFiles()));
    l1->setText(qstr);
    l2->setText(qstr2);

    mainLayout->addWidget(l1);
    mainLayout->addWidget(l2);

    this->table_model = new TableModel(this->s.getRepoSourceFile()->getSourceFiles());
    this->table = new QTableView();
    this->table->setModel(this->table_model);

    proxy1 = new QSortFilterProxyModel{};
    proxy1->setSourceModel(this->table_model);
    this->table->setSortingEnabled(true);
    proxy1->setFilterKeyColumn(0);
    proxy1->sort(0);
    proxy1->sort(1);
    this->table->setModel(proxy1);

    this->sourceFile_name = new QLineEdit();
    this->sourceFile_name->setPlaceholderText("Name of the source file here");
    this->add_sourceFile = new QPushButton("Add source file");


    this->review_sourceFile = new QPushButton("Review source file");

    mainLayout->addWidget(this->table);
    mainLayout->addWidget(this->sourceFile_name);
    mainLayout->addWidget(this->add_sourceFile);
    mainLayout->addWidget(this->review_sourceFile);
    this->connect();
}

void GUI::update()
{
    this->table_model->update(this->s.getRepoSourceFile()->getSourceFiles());
}

void GUI::connect()
{
    QObject::connect(this->add_sourceFile, &QPushButton::clicked, this, &GUI::add_sourceFile_gui);
    QObject::connect(this->table, &QTableView::clicked, this, &GUI::review_sourceFile_gui);
    QObject::connect(this->review_sourceFile, &QPushButton::clicked, this, &GUI::update_sourceFile_gui);
}

void GUI::add_sourceFile_gui()
{
    try {
        SourceFile sf = SourceFile(this->sourceFile_name->text().toStdString(), "not_revised", this->current_programmer.getName(), "");
        this->s.add_sourceFile(sf.getName(), sf.getStatus(), sf.getCreator(), sf.getReviewer());
        this->sourceFile_name->clear();
        this->update();
    }
    catch (...) {
        QMessageBox::information(this, "Error!", QString::fromStdString("Something went wrong!"));
    }
}

void GUI::review_sourceFile_gui()
{
    QModelIndex index = this->table->selectionModel()->currentIndex();
    std::string id_selected = this->proxy1->index(index.row(), 1).data().toString().toStdString();
    std::string name_selected = this->proxy1->index(index.row(), 2).data().toString().toStdString();

    if (id_selected == "revised" || name_selected == this->current_programmer.getName()) {
        this->review_sourceFile->setDisabled(true);
    }
    else {
        this->review_sourceFile->setDisabled(false);
    }
}

void GUI::update_sourceFile_gui()
{
    QModelIndex index = this->table->selectionModel()->currentIndex();
    std::string id_selected = this->proxy1->index(index.row(), 1).data().toString().toStdString();

    this->s.update_sourceFile(this->proxy1->index(index.row(), 0).data().toString().toStdString(),
        "revised",
        this->proxy1->index(index.row(), 2).data().toString().toStdString(),
        this->current_programmer.getName());
    this->update_programmer_gui();
}

void GUI::update_programmer_gui()
{
    int aux = this->current_programmer.getRevisedFiles() + 1;
    this->current_programmer.setRevisedFiles(aux);
   // this->l1->setText(QString::fromStdString("Number of revised files: " + to_string(aux)));
    if (this->current_programmer.getRevisedFiles() == this->current_programmer.getTotalFiles()) {
        QMessageBox* done = new QMessageBox();
        done->setText("Congratulations!");
        done->setVisible(true);
    
    }
}
