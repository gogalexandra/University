#include "TableModel.h"

int TableModel::rowCount(const QModelIndex& parent) const {
    return this->issues.size();
}

int TableModel::columnCount(const QModelIndex& parent) const {
    return 4;
}

QVariant TableModel::data(const QModelIndex& index, int role) const {
    int row = index.row();
    int col = index.column();

    if (role == Qt::DisplayRole) {
        if (col == 0) return QString::fromStdString(this->issues[row].getDescription());
        if (col == 1) return QString::fromStdString(this->issues[row].getStatus());
        if (col == 2) return QString::fromStdString(this->issues[row].getReporter());
        if (col == 3) return QString::fromStdString(this->issues[row].getSolver());
    }
    return QVariant();
}

QVariant TableModel::headerData(int section, Qt::Orientation orientation, int role) const {
    int col = section;

    if (role == Qt::DisplayRole && orientation == Qt::Horizontal) {
        if (col == 0) return QString::fromStdString("Description");
        if (col == 1) return QString::fromStdString("Status");
        if (col == 2) return QString::fromStdString("Reporter");
        if (col == 3) return QString::fromStdString("Solver");
    }
    return QVariant();
}

void TableModel::update(const std::vector<Issue>& i) {
    if (this->issues.size() > 0) {
        this->beginRemoveRows({}, 0, this->issues.size() - 1);
        this->issues.clear();
        this->endRemoveRows();
    }

    if (i.size() > 0) {
        this->beginInsertRows({}, 0, i.size() - 1);
        for (auto item : i) {
            this->issues.push_back(item);
        }
        endInsertRows();
    }
}
