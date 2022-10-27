#include "TableModel.h"
#include <QTableWidgetItem>

int TableModel::rowCount(const QModelIndex& parent) const
{
	return this->sourcefiles.size();
}

int TableModel::columnCount(const QModelIndex& parent) const
{
	return 4;
}

QVariant TableModel::data(const QModelIndex& index, int role) const
{
    int row = index.row();
    int col = index.column();

    if (role == Qt::DisplayRole) {
        if (col == 0) return QString::fromStdString(this->sourcefiles[row].getName());
        if (col == 1) return QString::fromStdString(this->sourcefiles[row].getStatus());
        if (col == 2) return QString::fromStdString(this->sourcefiles[row].getCreator());
        if (col == 3) return QString::fromStdString(this->sourcefiles[row].getReviewer());
    }
    if (this->sourcefiles[row].getStatus() == "revised")
        return QVariant(QColor(Qt::green));
    else
        return QVariant();
}

QVariant TableModel::headerData(int section, Qt::Orientation orientation, int role) const
{
    int col = section;

    if (role == Qt::DisplayRole && orientation == Qt::Horizontal) {
        if (col == 0) return QString::fromStdString("Name");
        if (col == 1) return QString::fromStdString("Status");
        if (col == 2) return QString::fromStdString("Creator");
        if (col == 3) return QString::fromStdString("Reviewer");
    }
    return QVariant();
}

void TableModel::update(const std::vector<SourceFile>& sf)
{
    if (this->sourcefiles.size() > 0) {
        this->beginRemoveRows({}, 0, this->sourcefiles.size() - 1);
        this->sourcefiles.clear();
        this->endRemoveRows();
    }

    if (sf.size() > 0) {
        this->beginInsertRows({}, 0, sf.size() - 1);
        for (auto item : sf) {
            this->sourcefiles.push_back(item);
        }
        endInsertRows();
    }
}

