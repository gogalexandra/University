#pragma once
#include "Service.h"
#include <iostream>
#include <string>
#include <algorithm>
#include <iterator>
#include "Validator.h"
#include <QtWidgets/qwidget.h>
#include <QtWidgets/qlistwidget.h>
#include <QtWidgets/qpushbutton.h>
#include <QtWidgets/qlineedit.h>
#include<QtWidgets/qboxlayout.h>
#include <QtWidgets\qformlayout.h>
#include <qmessagebox.h>
#include <QString>
#include <QLabel>
#include "Exec.h"


class GUI :public QWidget {
private:
	TextRepo textrepo{ "input.txt", "input.txt" };
	TextAdoptions textadopt{ "adoptions.txt", "adoptions.txt" };
	Service s{ &textrepo, &textadopt };
	Exec* e;


	QListWidget* dogs = new QListWidget;
	QListWidget* adoptions = new QListWidget;
	QVBoxLayout* main_ly = new QVBoxLayout;



	void initGUIComp();
	void populateList();
	//From here
	void start();
	
	void start_admin();
	void start_user();
	
	//for admin mode
	void add(QVBoxLayout* ly);
	void update(QVBoxLayout* ly);
	void del();
	void undo();
	void redo();

	//for user mode
	void manage_adoptions();
	void adopt();
	void adopt_filtered();

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