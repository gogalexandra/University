#include "Gui.h"
#include <QLCDNumber>

void GUI::initGUIComp()
{
	setLayout(this->main_ly);
	this->start();
}

void GUI::populateList()
{
	all->clear();
	vector<TimeInterval> v = this->r.getItems();
	for (auto it : v) {
		auto text = it.toString();
		QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(text) };
		item->setData(Qt::UserRole, QString::fromStdString(text));
		all->addItem(item);
	}
}

void GUI::start()
{
	this->main_ly->addWidget(this->all);
	this->all->setVisible(true);

	this->main_ly->addWidget(this->filter1);
	this->main_ly->addWidget(this->filter2);
	this->main_ly->addWidget(this->back);
	this->main_ly->addWidget(this->exit);

	QObject::connect(this->exit, &QPushButton::clicked, this, [=]() {close(); });

	QObject::connect(this->back, &QPushButton::clicked, this, [=]() {this->populateList(); });

	QObject::connect(this->filter1, &QPushButton::clicked, this, [=]() {this->forFilter1(); });
}

void GUI::forFilter1()
{
	QSlider* sl = new QSlider(Qt::Horizontal, 0);
	this->main_ly->addWidget(sl);
	//QLCDNumber* lcd = new QLCDNumber("lcd");
	//int val = 100;
	//sl->valueChanged(val);
	QObject::connect(sl, SIGNAL(valueChanged(int)), SLOT(populateV2(int)));

}

void GUI::populateV2(int value)
{
	this->all->clear();
	vector<TimeInterval> v = this->r.filterProbability(value);
	for (auto it : v) {
		auto text = it.toString();
		QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(text) };
		item->setData(Qt::UserRole, QString::fromStdString(text));
		all->addItem(item);
	}
}
