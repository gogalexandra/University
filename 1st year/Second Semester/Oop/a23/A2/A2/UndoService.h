#pragma once
#include "Repository.h"

typedef struct{
	MaterialRepository** history;
	int capacity;
	int nrElements;
	int index;

}UndoService;

UndoService* createUndoRepo();
void SaveData(UndoService* ur, MaterialRepository* mr);
bool Undo(UndoService* ur, MaterialRepository* mr);
bool Redo(UndoService* ur, MaterialRepository* mr);
void resizeUR(UndoService* ur);
void destroyUR(UndoService* ur);
void testsUndo();