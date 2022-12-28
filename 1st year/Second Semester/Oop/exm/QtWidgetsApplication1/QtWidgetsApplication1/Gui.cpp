#include "Gui.h"
#include "QMessageBox"

GUI::GUI(Ethnologists& e, Service& s, QWidget* parent) : current_ethnologists(e), QWidget(parent), s(s)
{
    this->setWindowTitle(QString::fromStdString(e.getName()));
    this->s.addObserver(this);
    this->setStyleSheet("background-color:red;");

    QVBoxLayout* mainLayout = new QVBoxLayout();
    this->setLayout(mainLayout);

    this->table_model = new TableModel(this->s.getRepoBuildings()->getBuildings(), current_ethnologists.getThematicArea());
    this->table = new QTableView();
    this->table->setModel(this->table_model);

    proxy1 = new QSortFilterProxyModel{};
    proxy1->setSourceModel(this->table_model);
    this->table->setSortingEnabled(true);
    proxy1->setFilterKeyColumn(0);
    proxy1->sort(0);
    proxy1->sort(1);
    this->table->setModel(proxy1);

    this->building_id= new QLineEdit();
    this->building_id->setPlaceholderText("Id of the building here");

    this->building_descrption = new QLineEdit();
    this->building_descrption->setPlaceholderText("Description of the building here");
    
    this->building_location= new QLineEdit();
    this->building_location->setPlaceholderText("Location of the building here");

    this->add_building = new QPushButton("Add building");
    this->update_building = new QPushButton("Update building");
   
    mainLayout->addWidget(this->table);
    mainLayout->addWidget(this->add_building);
    mainLayout->addWidget(this->update_building);

    mainLayout->addWidget(this->building_id);
    mainLayout->addWidget(this->building_descrption);
    mainLayout->addWidget(this->building_location);

    this->connect();
}

void GUI::update()
{
	this->table_model->update(this->s.getRepoBuildings()->getBuildings());
}

void GUI::connect()
{
    QObject::connect(this->add_building, &QPushButton::clicked, this, &GUI::add_building_gui);
    QObject::connect(this->update_building, &QPushButton::clicked, this, &GUI::update_building_gui);
}

void GUI::add_building_gui()
{
    try {
        std::string s = this->building_location->text().toStdString();
        std::string delimiter = ";";

        size_t pos = 0;
        std::string token;
        std::vector<std::string> vec;
       
        while ((pos = s.find(delimiter)) != std::string::npos) {
            token = s.substr(0, pos);
            vec.push_back(token);
            s.erase(0, pos + delimiter.length());
        }
        vec.push_back(s);

        Buildings b = Buildings(this->building_id->text().toInt(), this->building_descrption->text().toStdString(), this->current_ethnologists.getThematicArea(), vec);
        this->s.add_building(b.getId(), b.getDescription(), b.getThematicArea(), b.getLocation());
        this->building_id->clear();
        this->building_descrption->clear();
        this->building_location->clear();
        this->update();
    }
    catch (...) {
        QMessageBox::information(this, "Error!", QString::fromStdString("Something went wrong!"));
    }
}

void GUI::update_building_gui()
{
    QModelIndex index = this->table->selectionModel()->currentIndex();
    std::string id_selected = this->proxy1->index(index.row(), 1).data().toString().toStdString();
    std::string ta = this->proxy1->index(index.row(), 2).data().toString().toStdString();
    if (ta != current_ethnologists.getThematicArea()) {
        QMessageBox::information(this, "Error!", QString::fromStdString("Can not update this one!"));
        this->building_id->clear();
        this->building_descrption->clear();
        this->building_location->clear();
        return;
    }

    std::string s = this->building_location->text().toStdString();
    std::string delimiter = ";";

    size_t pos = 0;
    std::string token;
    std::vector<std::string> vec;

    while ((pos = s.find(delimiter)) != std::string::npos) {
        token = s.substr(0, pos);
        vec.push_back(token);
        s.erase(0, pos + delimiter.length());
    }
    vec.push_back(s);
    if (vec.size() != 0) {
        if (this->building_descrption->text().toStdString() != "") {
            this->s.update_building(this->proxy1->index(index.row(), 0).data().toString().toInt(),
                this->building_descrption->text().toStdString(),
                this->proxy1->index(index.row(), 2).data().toString().toStdString(),
                vec);
        }
        else {
            this->s.update_building(this->proxy1->index(index.row(), 0).data().toString().toInt(),
                this->proxy1->index(index.row(), 1).data().toString().toStdString(),
                this->proxy1->index(index.row(), 2).data().toString().toStdString(),
                vec);
        }

    }
    else {
        std::string s = this->proxy1->index(index.row(), 3).data().toString().toStdString();
        std::string delimiter = ";";

        size_t pos = 0;
        std::string token;
        std::vector<std::string> vec1;

        while ((pos = s.find(delimiter)) != std::string::npos) {
            token = s.substr(0, pos);
            vec1.push_back(token);
            s.erase(0, pos + delimiter.length());
        }
        vec1.push_back(s);


        if (this->building_descrption->text().toStdString() != "") {
            this->s.update_building(this->proxy1->index(index.row(), 0).data().toString().toInt(),
                this->building_descrption->text().toStdString(),
                this->proxy1->index(index.row(), 2).data().toString().toStdString(),
                vec1);
        }
        else {
            this->s.update_building(this->proxy1->index(index.row(), 0).data().toString().toInt(),
                this->proxy1->index(index.row(), 1).data().toString().toStdString(),
                this->proxy1->index(index.row(), 2).data().toString().toStdString(),
                vec1);
        }
    
    }
    this->building_id->clear();
    this->building_descrption->clear();
    this->building_location->clear();
}
