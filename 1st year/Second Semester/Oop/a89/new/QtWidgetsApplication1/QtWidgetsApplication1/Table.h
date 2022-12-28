#pragma once
#include "Service.h"
#include <QAbstractListModel>
#include <qpixmap.h>

class Table : public QAbstractTableModel {
private:
    std::vector<Dog> inputVector;

public:
    Table(std::vector<Dog>&, QObject* parent = nullptr);
    virtual ~Table();

    // number of rows
    int rowCount(const QModelIndex& parent = QModelIndex{}) const override;
    // number of columns
    int columnCount(const QModelIndex& parent = QModelIndex()) const override;
    // Value at a given position
    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;
};