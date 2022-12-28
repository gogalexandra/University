#include "GUI2.h"

void GUI::initGUIComp()
{
	setLayout(this->main_ly);
	this->start();
	
}

void GUI::populateList()
{
	this->s.openFile();
	dogs->clear();
	vector<Dog> v = this->s.getRepo().getAll();
	for (auto it : v) {
		auto text = it.toString();
		QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(text) };
		item->setData(Qt::UserRole, QString::fromStdString(text));
		dogs->addItem(item);
	}

	//adoptions->clear();
	vector<Dog> d = this->s.getAdoptions().getAll();
	for (auto it : d) {
		auto text = it.toString();
		QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(text) };
		item->setData(Qt::UserRole, QString::fromStdString(text));
		adoptions->addItem(item);
	}
}

void GUI::start()
{
	QPushButton* user = new QPushButton{ "User mode" };
	QPushButton* admin = new QPushButton{ "Admin mode" };
	QPushButton* exit = new QPushButton{ "Exit" };
	QHBoxLayout* start_ly = new QHBoxLayout;

	QObject::connect(user, &QPushButton::clicked, this, [=]() {
		delete user;
		delete admin;
		delete exit;
		delete start_ly;
		this->start_user();
		});

	QObject::connect(admin, &QPushButton::clicked, this, [=]() {
		delete user;
		delete admin;
		delete exit;
		delete start_ly;
		this->start_admin();
		});

	QObject::connect(exit, &QPushButton::clicked, this, [=]() {close(); });
	
	start_ly->addWidget(user);
	start_ly->addWidget(admin);
	start_ly->addWidget(exit);

	user->setVisible(true);
	admin->setVisible(true);
	exit->setVisible(true);
	this->main_ly->addLayout(start_ly);
}

void GUI::start_user()
{
	QVBoxLayout* start_ly = new QVBoxLayout;
	QVBoxLayout* button_ly = new QVBoxLayout;

	QPushButton* csv = new QPushButton{ "CSV File" };
	QPushButton* html = new QPushButton{ "HTML File" };
	QPushButton* back = new QPushButton{ "Back" };


	QObject::connect(csv, &QPushButton::clicked, this, [=]() {
		delete csv;
		delete html;
		delete back;
		delete start_ly;
		this->s.setRepo("csv");
		this->manage_adoptions();
		});

	QObject::connect(html, &QPushButton::clicked, this, [=]() {
		delete back;
		delete csv;
		delete html;
		delete start_ly;
		this->s.setRepo("html"); 
		this->manage_adoptions();
		});

	QObject::connect(back, &QPushButton::clicked, this, [=]() {
		delete csv;
		delete html;
		delete back;
		delete start_ly;
		this->adoptions->setVisible(false);
		this->start();
		});

	button_ly->addWidget(csv);
	button_ly->addWidget(html);
	button_ly->addWidget(back);

	
	start_ly->addLayout(button_ly);

	this->main_ly->addLayout(start_ly);
}

void GUI::start_admin()
{
	QVBoxLayout* start_ly = new QVBoxLayout;
	QHBoxLayout* button_ly = new QHBoxLayout;


	QPushButton* add = new QPushButton{ "Add dog" };
	QPushButton* del = new QPushButton{ "Delete dog" };
	QPushButton* update = new QPushButton{ "Update dog" };
	QPushButton* back = new QPushButton{ "Back" };

	this->dogs->setVisible(true);

	QObject::connect(add, &QPushButton::clicked, this, [=]() {
		this->add(start_ly);
		});


	QObject::connect(del, &QPushButton::clicked, this, [=]() {
		this->del();
		});

	QObject::connect(update, &QPushButton::clicked, this, [=]() {
		this->update(start_ly);
		});

	QObject::connect(back, &QPushButton::clicked, this, [=]() {
		delete add;
		delete del;
		delete update;
		delete back;
		delete start_ly;
		this->dogs->setVisible(false);
		this->start();
		});

	button_ly->addWidget(add);
	button_ly->addWidget(del);
	button_ly->addWidget(update);
	button_ly->addWidget(back);

	start_ly->addWidget(this->dogs);
	start_ly->addLayout(button_ly);

	this->main_ly->addLayout(start_ly);
}

void GUI::add(QVBoxLayout* start_ly)
{
	QLabel* breed_text = new QLabel{ "Breed: " };
	QLineEdit* breed_input = new QLineEdit{};
	QLabel* name_text = new QLabel{ "Name: " };
	QLineEdit* name_input = new QLineEdit{};
	QLabel* age_text = new QLabel{ "Age: " };
	QLineEdit* age_input = new QLineEdit{};
	QLabel* photo_text = new QLabel{ "Photograph: " };
	QLineEdit* photo_input = new QLineEdit{};

	QPushButton* done = new QPushButton{ "Done" };

	QFormLayout* text_ly = new QFormLayout;

	QObject::connect(done, &QPushButton::clicked, this, [=]() {
		string breed = breed_input->text().toStdString();
		int age = age_input->text().toInt();
		string name = name_input->text().toStdString();
		string photp = photo_input->text().toStdString();
		try {
			this->s.addDogToRepo(breed, name, age, photp);
		}
		catch (const ValidatorException& e) {
			cout << e.getMessage() << endl;
		}
		this->populateList();
		delete breed_text;
		delete breed_input;
		delete name_text;
		delete name_input;
		delete age_text;
		delete age_input;
		delete photo_text;
		delete photo_input;
		delete done;
		delete text_ly;
		});

	text_ly->addRow(breed_text, breed_input);
	text_ly->addRow(name_text, name_input);
	text_ly->addRow(age_text, age_input);
	text_ly->addRow(photo_text, photo_input);
	text_ly->addRow(done);

	start_ly->addLayout(text_ly);

}

void GUI::update(QVBoxLayout* start_ly)
{
	QLabel* breed_text = new QLabel{ "Breed: " };
	QLineEdit* breed_input = new QLineEdit{};
	QLabel* name_text = new QLabel{ "Name: " };
	QLineEdit* name_input = new QLineEdit{};
	QLabel* age_text = new QLabel{ "Age: " };
	QLineEdit* age_input = new QLineEdit{};
	QLabel* photo_text = new QLabel{ "Photograph: " };
	QLineEdit* photo_input = new QLineEdit{};

	QPushButton* done = new QPushButton{ "Done" };

	QFormLayout* text_ly = new QFormLayout;

	int index = this->getListIndex();

	if (index != -1) {
		vector<Dog> d = this->s.getRepo().getAll();
		QString breed = QString::fromStdString(d[index].getBreed());
		breed_input->setText(breed);
		QString name = QString::fromStdString(d[index].getName());
		name_input->setText(name);
		name_input->setReadOnly(true);
		QString age = QString::number(d[index].getAge());
		age_input->setText(age);
		QString photo = QString::fromStdString(d[index].getPhotograph());
		photo_input->setText(photo);
	}

	QObject::connect(done, &QPushButton::clicked, this, [=]() {
		string breed = breed_input->text().toStdString();
		int age = age_input->text().toInt();
		string name = name_input->text().toStdString();
		string photp = photo_input->text().toStdString();
		this->s.updateDogFromRepo(breed, name, age, photp);
		this->populateList();

		delete breed_text;
		delete breed_input;
		delete name_text;
		delete name_input;
		delete age_text;
		delete age_input;
		delete photo_text;
		delete photo_input;
		delete done;
		delete text_ly;

		});

	text_ly->addRow(breed_text, breed_input);
	text_ly->addRow(name_text, name_input);
	text_ly->addRow(age_text, age_input);
	text_ly->addRow(photo_text, photo_input);
	text_ly->addRow(done);

	start_ly->addLayout(text_ly);
}

void GUI::del()
{
	int index = this->getListIndex();
	if (index != -1) {
		vector<Dog> d = this->s.getRepo().getAll();
		string name = d[index].getName();
		this->s.deleteDogFromRepo(name);
		this->populateList();
	}

}

void GUI::manage_adoptions()
{
	QVBoxLayout* start_ly = new QVBoxLayout;
	QHBoxLayout* button_ly = new QHBoxLayout;

	QPushButton* adopt = new QPushButton{ "Adopt" };
	QPushButton* back = new QPushButton{ "Back" };

	QObject::connect(adopt, &QPushButton::clicked, this, [=]() {
		delete adopt;
		delete back;
		this->adoptions->setVisible(false);
		this->adopt();
		});


	QObject::connect(back, &QPushButton::clicked, this, [=]() {
		delete adopt;
		delete back;
		delete start_ly;
		this->adoptions->setVisible(false);
		this->start_user();
		});


	this->adoptions->setVisible(true);

	button_ly->addWidget(adopt);
	button_ly->addWidget(back);

	start_ly->addWidget(this->adoptions);
	start_ly->addLayout(button_ly);

	this->main_ly->addLayout(start_ly);

}

void GUI::adopt()
{
	QVBoxLayout* start_ly = new QVBoxLayout;
	QHBoxLayout* button_ly = new QHBoxLayout;

	QPushButton* adopt = new QPushButton{ "Adopt" };
	QPushButton* filter = new QPushButton{ "Filter" };
	QPushButton* remove = new QPushButton{ "Remove filter" };
	QPushButton* done = new QPushButton{ "Done" };

	QObject::connect(adopt, &QPushButton::clicked, this, [=]() {
		int index = this->getListIndex();
		if (index != -1) {
			vector<Dog> d = this->s.getRepo().getAll();
			string breed = d[index].getBreed();
			string name = d[index].getName();
			int age = d[index].getAge();
			string photo = d[index].getPhotograph();
			this->s.adoptDogToRepo(breed, name, age, photo);
			this->populateList();
		}
		});

	QObject::connect(filter, &QPushButton::clicked, this, [=]() {
		QLabel* age_text = new QLabel{ "Maximum age: " };
		QLineEdit* age_input = new QLineEdit{};
		QLabel* breed_text = new QLabel{ "Breed: " };
		QLineEdit* breed_input = new QLineEdit{};

		QFormLayout* text_ly = new QFormLayout;

		text_ly->addRow(age_text, age_input);
		text_ly->addRow(breed_text, breed_input);

		start_ly->addLayout(text_ly);

		QPushButton* show = new QPushButton{ "Show" };
		start_ly->addWidget(show);

		QObject::connect(show, &QPushButton::clicked, this, [=]() {
			
			string breed = breed_input->text().toStdString();
			int age = age_input->text().toInt();
			this->dogs->clear();
			STLVector d = this->s.filterDogsFromRepo(breed, age);
			for (auto& it : d.getAll()) {
				auto text = it.toString();
				QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(text) };
				item->setData(Qt::UserRole, QString::fromStdString(text));
				this->dogs->addItem(item);
			}

			delete text_ly;
			delete age_input;
			delete age_text;
			delete breed_input;
			delete breed_text;
			delete show;
			});
		});

	QObject::connect(remove, &QPushButton::clicked, this, [=]() {
		dogs->clear();
		vector<Dog> v = this->s.getRepo().getAll();
		for (auto it : v) {
			auto text = it.toString();
			QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(text) };
			item->setData(Qt::UserRole, QString::fromStdString(text));
			dogs->addItem(item);
		}
		});

	QObject::connect(done, &QPushButton::clicked, this, [=]() {
		delete adopt;
		delete done;
		delete filter;
		delete remove;
		delete start_ly;
		this->dogs->setVisible(false);
		this->manage_adoptions();
		});


	button_ly->addWidget(this->dogs);
	button_ly->addWidget(adopt);
	button_ly->addWidget(filter);
	button_ly->addWidget(remove);
	button_ly->addWidget(done);

	this->dogs->setVisible(true);

	start_ly->addLayout(button_ly);

	this->main_ly->addLayout(start_ly);

}

void GUI::adopt_filtered()
{
	QLabel* age_text = new QLabel{ "Maximum age: " };
	QLineEdit* age_input = new QLineEdit{};
	QLabel* breed_text = new QLabel{ "Breed: " };
	QLineEdit* breed_input = new QLineEdit{};
	QFormLayout* text_ly = new QFormLayout;

	QVBoxLayout* start_ly = new QVBoxLayout;
	QHBoxLayout* button_ly = new QHBoxLayout;

	text_ly->addRow(age_text, age_input);
	text_ly->addRow(breed_text, breed_input);

	start_ly->addLayout(text_ly);
	

	QPushButton* show = new QPushButton{ "Show" };
	QObject::connect(show, &QPushButton::clicked, this, [=]() {

		string breed = breed_input->text().toStdString();
		int age = age_input->text().toInt();
		QListWidget* filtered = new QListWidget;
		filtered->clear();
		STLVector d = this->s.filterDogsFromRepo(breed, age);
		for (auto& it : d.getAll()) {
			auto text = it.toString();
			QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(text) };
			item->setData(Qt::UserRole, QString::fromStdString(text));
			filtered->addItem(item);
		}
		button_ly->addWidget(filtered);
		});

	QPushButton* done = new QPushButton{ "Done" };

	QObject::connect(done, &QPushButton::clicked, this, [=]() {
		
		});

	QObject::connect(done, &QPushButton::clicked, this, [=]() {
		delete done;
		delete show;
		delete start_ly;
		this->dogs->setVisible(false);
		this->manage_adoptions();
		});


	button_ly->addWidget(done);
	button_ly->addWidget(show);

	start_ly->addLayout(text_ly);
	start_ly->addLayout(button_ly);

	this->main_ly->addLayout(start_ly);

}

int GUI::getListIndex()
{

	QModelIndexList allIndexes = this->dogs->selectionModel()->selectedIndexes();
	if (allIndexes.empty()) {
		/*this->dogName->clear();
		this->dogBreed->clear();
		this->dogAge->clear();
		this->dogLink->clear();*/
		return -1;
	}
	int selectedIndex = allIndexes.at(0).row();
	return selectedIndex;
}
