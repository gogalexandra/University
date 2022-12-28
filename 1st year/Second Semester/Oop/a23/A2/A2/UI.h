#pragma once
#include "Service.h"
#include "UndoService.h"

typedef struct
{
	MaterialService* service;
	UndoService* undoservice;

} UI;

UI* createUI();
void addMaterialUI(UI* ui);
bool updateMaterialUI(UI* ui);
bool deleteMaterialUI(UI* ui);
void searchMaterialUI(UI* ui);
void start(UI* ui);
void sorted_by_supplier(TElem* array, int dim);
void sorted_by_quantity(TElem* array, int dim);
void print_menu(UI* ui);
void print_materials(UI* ui, TElem* array, int dim);
void sortedDescendingUI(UI* ui);
void specificSupplier(UI* ui);
void destroyUI(UI* ui);
void undoUI(UI* ui);
void redoUI(UI* ui);