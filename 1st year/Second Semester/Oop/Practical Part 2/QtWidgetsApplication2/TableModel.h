#pragma once
#include <iostream>
#include <QAbstractTableModel>
#include <vector>
#include "SourceFile.h"

//using namespace std;

class TableModel : public QAbstractTableModel {
private:
    std::vector<SourceFile> sourcefiles;
public:
    TableModel(std::vector<SourceFile> sf) : sourcefiles(sf) {};

    int rowCount(const QModelIndex& parent = QModelIndex()) const override;

    int columnCount(const QModelIndex& parent = QModelIndex()) const override;

    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;

    QVariant headerData(int section, Qt::Orientation orientation, int role) const override;

    void update(const std::vector<SourceFile>& sf);

};