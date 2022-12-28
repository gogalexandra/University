#include "Operations.h"

void AddOperation::perform() {
    this->redo();
}

void AddOperation::undo() {
    this->repo->removeDog(this->d.getName());
}

void AddOperation::redo() {
    this->repo->addDog(d);
}

void DeleteOperation::perform() {
    this->redo();
}

void DeleteOperation::undo() {
    this->repo->addDog(d);
}

void DeleteOperation::redo() {
    this->repo->removeDog(this->d.getName());
}

void UpdateOperation::perform() {
    this->redo();
}

void UpdateOperation::undo() {
    this->repo->updateDog(this->prev);
}

void UpdateOperation::redo() {
    this->repo->updateDog(this->current);
}