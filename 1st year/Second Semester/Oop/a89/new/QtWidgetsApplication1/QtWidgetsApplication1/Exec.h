#pragma once
#include "Operations.h"
#include <stack>
using namespace std;

class Exec {
private:
    stack<Operation*> operations;
    stack<Operation*> undoOperations;

public:
    Exec() {};
    ~Exec() {};
    void saveOperation(Operation* op);
    void undo();
    void redo();
};