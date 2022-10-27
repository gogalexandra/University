#pragma once
#include <iostream>
#include <QAbstractTableModel>
#include <vector>
#include "Idea.h"

//using namespace std;

class TableModel : public QAbstractTableModel {
private:
    std::vector<Idea> ideas;
public:
    TableModel(std::vector<Idea> is) : ideas(is) {};

    int rowCount(const QModelIndex& parent = QModelIndex()) const override;

    int columnCount(const QModelIndex& parent = QModelIndex()) const override;

    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;

    QVariant headerData(int section, Qt::Orientation orientation, int role) const override;

    void update(const std::vector<Idea>&);
};
