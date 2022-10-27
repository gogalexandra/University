#include "Statistics.h"
#include <QPainter>

Statistics::Statistics(Programmer& p, Service&s, QWidget* parent): current_programmer(p), QWidget(parent), s(s)
{
    this->setWindowTitle(QString::fromStdString(p.getName()));
    this->s.addObserver(this);

    QVBoxLayout* mainLayout = new QVBoxLayout();
    this->setLayout(mainLayout);

    QPainter painter(this);
    painter.drawEllipse(QPointF(10, 12), current_programmer.getTotalFiles(), current_programmer.getTotalFiles());
}

void Statistics::update()
{
    QPainter painter(this);
    painter.drawEllipse(QPointF(0, 0), current_programmer.getRevisedFiles(), current_programmer.getRevisedFiles());
}
