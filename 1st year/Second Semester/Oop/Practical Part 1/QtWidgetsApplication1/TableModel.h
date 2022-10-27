#pragma once
#include <iostream>
#include <QAbstractTableModel>
#include <vector>
#include "Issue.h"

//using namespace std;

class TableModel : public QAbstractTableModel {
private:
    std::vector<Issue> issues;
public:
    TableModel(std::vector<Issue> is) : issues(is) {};

    int rowCount(const QModelIndex& parent = QModelIndex()) const override;

    int columnCount(const QModelIndex& parent = QModelIndex()) const override;

    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;

    QVariant headerData(int section, Qt::Orientation orientation, int role) const override;

    void update(const std::vector<Issue>&);
};
