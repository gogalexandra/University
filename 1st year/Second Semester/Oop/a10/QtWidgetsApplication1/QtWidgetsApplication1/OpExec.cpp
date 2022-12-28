#include "OpExec.h"

void OpExec::saveOperation(Operation* op) {
    this->undoOperations = std::stack<Operation*>();
    op->perform();
    this->operations.push(op);
}

void OpExec::undo() {
    if (this->operations.size() > 0) {
        this->operations.top()->undo();
        this->undoOperations.push(this->operations.top());
        this->operations.pop();
    }
}

void OpExec::redo() {
    if (this->undoOperations.size() > 0) {
        this->undoOperations.top()->redo();
        this->operations.push(this->undoOperations.top());
        this->undoOperations.pop();
    }
}