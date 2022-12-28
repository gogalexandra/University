#pragma once
#include <iostream>
#include <QAbstractTableModel>
#include <vector>
#include "Buildings.h"

//using namespace std;

class TableModel : public QAbstractTableModel {
private:
    std::vector<Buildings> buildings;
public:
    TableModel(std::vector<Buildings> is, std::string ta) : buildings(is){};

    int rowCount(const QModelIndex& parent = QModelIndex()) const override;

    int columnCount(const QModelIndex& parent = QModelIndex()) const override;

    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;

    QVariant headerData(int section, Qt::Orientation orientation, int role) const override;

    void update(const std::vector<Buildings>&);
};