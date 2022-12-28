#include "TableModel.h"

int TableModel::rowCount(const QModelIndex& parent) const {
    return this->buildings.size();
}

int TableModel::columnCount(const QModelIndex& parent) const {
    return 4;
}

QVariant TableModel::data(const QModelIndex& index, int role) const {
    int row = index.row();
    int col = index.column();

    if (role == Qt::DisplayRole) {
        if (col == 0) return QString::number(this->buildings[row].getId());
        if (col == 1) return QString::fromStdString(this->buildings[row].getDescription());
        if (col == 2) return QString::fromStdString(this->buildings[row].getThematicArea());
        if (col == 3) {
            std::vector<std::string> vec = this->buildings[row].getLocation();
            std::string vecstr = "";
            for (auto i : vec) {
                vecstr += i;
                vecstr += "; ";
            }
            return QString::fromStdString(vecstr);
        }
    }
    return QVariant();
}

QVariant TableModel::headerData(int section, Qt::Orientation orientation, int role) const {
    int col = section;

    if (role == Qt::DisplayRole && orientation == Qt::Horizontal) {
        if (col == 0) return QString::fromStdString("Id");
        if (col == 1) return QString::fromStdString("Description");
        if (col == 2) return QString::fromStdString("Thematic Area");
        if (col == 3) return QString::fromStdString("Location");
    }
    return QVariant();
}

void TableModel::update(const std::vector<Buildings>& b) {
    if (this->buildings.size() > 0) {
        this->beginRemoveRows({}, 0, this->buildings.size() - 1);
        this->buildings.clear();
        this->endRemoveRows();
    }

    if (b.size() > 0) {
        this->beginInsertRows({}, 0, b.size() - 1);
        for (auto item : b) {
            this->buildings.push_back(item);
        }
        endInsertRows();
    }
}
