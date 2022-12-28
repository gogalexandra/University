#pragma once
#include "Op.h"
#include <stack>

class Exec {
private:
    std::stack<Operation*> operations;
    std::stack<Operation*> undoOperations;

public:
    Exec() {};
    ~Exec() {};
    void saveOperation(Operation* op);
    void undo();
    void redo();
};