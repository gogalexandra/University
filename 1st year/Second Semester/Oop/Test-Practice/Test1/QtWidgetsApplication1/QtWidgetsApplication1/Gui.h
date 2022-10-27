#pragma once
#include "Service.h"
#include <iostream>
#include <string>
#include <algorithm>
#include <iterator>
#include <QtWidgets/qwidget.h>
#include <QtWidgets/qlistwidget.h>
#include <QtWidgets/qpushbutton.h>
#include <QtWidgets/qlineedit.h>
#include<QtWidgets/qboxlayout.h>
#include <QtWidgets\qformlayout.h>
#include <qmessagebox.h>
#include <QString>
#include <QLabel>


class GUI :public QWidget {
private:
	Repository r;
	Service s{r};


	QListWidget* items = new QListWidget;
	QVBoxLayout* main_ly = new QVBoxLayout;

	QPushButton* filter = new QPushButton{ "Filter" };
	QPushButton* del = new QPushButton{ "Delete" };
	QPushButton* exit = new QPushButton{ "Exit" };

	

	void initGUIComp();
	void populateList();

	//From here
	void start();

	void remove();

	void filterItems();

	int getListIndex();


signals:
	void ProcessFinished();

public:
	GUI() {
		populateList();
		initGUIComp();
	}
	//~GUI();
};