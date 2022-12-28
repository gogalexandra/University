#include "Exec.h"

using namespace std;

void Exec::saveOperation(Operation* op) {

    //stack<Operation*> st;
    /*while (!this->undoOperations.empty()) {
        this->undoOperations.pop();
    }*/
    this->undoOperations = stack<Operation*>();
    op->perform();
    this->operations.push(op);
}

void Exec::undo() {
    if (this->operations.size() > 0) {
        this->operations.top()->undo();
        this->undoOperations.push(this->operations.top());
        this->operations.pop();
    }
}

void Exec::redo() {
    if (this->undoOperations.size() > 0) {
        this->undoOperations.top()->redo();
        this->operations.push(this->undoOperations.top());
        this->undoOperations.pop();
    }
}