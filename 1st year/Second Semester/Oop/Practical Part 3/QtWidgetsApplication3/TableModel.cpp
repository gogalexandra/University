#include "TableModel.h"

int TableModel::rowCount(const QModelIndex& parent) const {
    return this->ideas.size();
}

int TableModel::columnCount(const QModelIndex& parent) const {
    return 4;
}

QVariant TableModel::data(const QModelIndex& index, int role) const {
    int row = index.row();
    int col = index.column();

    if (role == Qt::DisplayRole) {
        if (col == 0) return QString::fromStdString(this->ideas[row].getDescription());
        if (col == 1) return QString::fromStdString(this->ideas[row].getStatus());
        if (col == 2) return QString::fromStdString(this->ideas[row].getCreator());
        if (col == 3) return QString::number(this->ideas[row].getAct());
    }
    return QVariant();
}

QVariant TableModel::headerData(int section, Qt::Orientation orientation, int role) const {
    int col = section;

    if (role == Qt::DisplayRole && orientation == Qt::Horizontal) {
        if (col == 0) return QString::fromStdString("Description");
        if (col == 1) return QString::fromStdString("Status");
        if (col == 2) return QString::fromStdString("Creator");
        if (col == 3) return QString::fromStdString("Act");
    }
    return QVariant();
}

void TableModel::update(const std::vector<Idea>& i) {
    if (this->ideas.size() > 0) {
        this->beginRemoveRows({}, 0, this->ideas.size() - 1);
        this->ideas.clear();
        this->endRemoveRows();
    }

    if (i.size() > 0) {
        this->beginInsertRows({}, 0, i.size() - 1);
        for (auto item : i) {
            this->ideas.push_back(item);
        }
        endInsertRows();
    }
}
