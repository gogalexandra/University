#pragma once
#include "Repo.h"
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
#include <QSlider> 


class GUI :public QWidget {
private:
	Repository r;

	QListWidget* all = new QListWidget;
	QWidget* main_widget = new QWidget{};
	QVBoxLayout* main_ly = new QVBoxLayout{this->main_widget};

	QPushButton* filter1 = new QPushButton{ "Filter by prepic" };
	QPushButton* filter2 = new QPushButton{ "Filter by description" };
	QPushButton* back = new QPushButton{ "Remove filter" };
	QPushButton* exit = new QPushButton{ "Exit" };



	void initGUIComp();
	void populateList();

	//From here
	void start();

	void forFilter1();

	void populateV2(int value);
signals:
	void ProcessFinished();

public:
	GUI() {
		populateList();
		initGUIComp();
	}
	//~GUI();
};