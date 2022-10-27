#include "Gui.h"

void GUI::initGUIComp()
{
	setLayout(this->main_ly);
	this->main_ly->SetMinimumSize;
	this->start();
}

void GUI::populateList()
{
	//this->s.openFile();
	items->clear();
	vector<Item> v = this->s.getRepo();
	for (auto it : v) {
		auto text = it.toString();
		QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(text) };
		item->setData(Qt::UserRole, QString::fromStdString(text));
		items->addItem(item);
	}
}

void GUI::start()
{
	this->main_ly->addWidget(this->items);
	this->items->setVisible(true);

	this->main_ly->addWidget(this->filter);
	this->main_ly->addWidget(this->del);
	this->main_ly->addWidget(this->exit);


	QObject::connect(this->filter, &QPushButton::clicked, this, [=]() {
		this->filterItems();
		});

	QObject::connect(this->del, &QPushButton::clicked, this, [=]() {
		this->remove();
		});

	QObject::connect(this->exit, &QPushButton::clicked, this, [=]() {close(); });

	this->filter->setVisible(true);
	this->del->setVisible(true);
	this->exit->setVisible(true);
}

void GUI::remove()
{
	int index = this->getListIndex();
	if (index != -1) {
		vector<Item> i = this->s.getRepo();
		string name = i[index].getName();
		this->s.deleteItemFromRepo(name);
		this->populateList();
	}
}

void GUI::filterItems()
{
	del->setVisible(false);
	filter->setVisible(false);
	exit->setVisible(false);

	QLabel* category_text = new QLabel{ "Category: " };
	QLineEdit* category_input = new QLineEdit{};
	QFormLayout* text_ly = new QFormLayout;

	text_ly->addRow(category_text, category_input);
	main_ly->addLayout(text_ly);

	QPushButton* show = new QPushButton{ "Show" };
	main_ly->addWidget(show);
	QObject::connect(show, &QPushButton::clicked, this, [=]() {

		string given_cat = category_input->text().toStdString();
		items->clear();
		vector<Item> f = this->s.filterItemsFromRepo(given_cat);
		for (auto& it : f) {
			auto text = it.toString();
			QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(text) };
			item->setData(Qt::UserRole, QString::fromStdString(text));
			items->addItem(item);
		}
		delete show;
		delete category_input;
		delete category_text;
		delete text_ly;
		del->setVisible(true);
		filter->setVisible(true);
		exit->setVisible(true);
		});

}

int GUI::getListIndex()
{
	QModelIndexList allIndexes = this->items->selectionModel()->selectedIndexes();
	if (allIndexes.empty()) {
		return -1;
	}
	int selectedIndex = allIndexes.at(0).row();
	return selectedIndex;
}
