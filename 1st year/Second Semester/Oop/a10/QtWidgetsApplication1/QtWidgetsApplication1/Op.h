#pragma once
#include "TextRepo.h"

class Operation {
public:
    Operation() {};
    ~Operation() {};

    virtual void perform() = 0;
    virtual void undo() = 0;
    virtual void redo() = 0;
};

class AddOperation : public Operation {
private:
    Dog d;
    TextRepo* repo;

public:
    AddOperation(Dog& d, TextRepo* repo) : d(d), repo(repo) {};
    ~AddOperation() {};

    void perform() override;
    void undo() override;
    void redo() override;
};

class DeleteOperation : public Operation {
private:
    Dog d;
    TextRepo* repo;

public:
    DeleteOperation(Dog& d, TextRepo* repo) : d(d), repo(repo) {};
    ~DeleteOperation() {};

    void perform() override;
    void undo() override;
    void redo() override;
};

class UpdateOperation : public Operation {
private:
    Dog prev;
    Dog current;
    TextRepo* repo;

public:
    UpdateOperation(Dog& previous, Dog& current, TextRepo* repo) : prev(previous), current(current), repo(repo) {};
    ~UpdateOperation() {};

    void perform() override;
    void undo() override;
    void redo() override;
};