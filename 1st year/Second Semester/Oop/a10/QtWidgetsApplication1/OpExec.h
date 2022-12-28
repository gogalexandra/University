#pragma once
#include "Op.h"
#include <stack>

class OpExec {
private:
    std::stack<Operation*> operations;
    std::stack<Operation*> undoOperations;

public:
    OpExec() {};
    ~OpExec() {};
    void saveOperation(Operation* op);
    void undo();
    void redo();
};