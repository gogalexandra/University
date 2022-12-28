#pragma once
#include "Repository.h"
#include "UndoService.h"
typedef struct
{
	MaterialRepository* repo;

} MaterialService;

MaterialService* createService();
void addMaterial(MaterialService* ms, TElem m);
bool deleteMaterial(MaterialService* ms, char* name);
bool updateMaterial(MaterialService* ms, TElem m);
void searchMaterial(MaterialService* ms, TElem* new_array, int* dim, char string[], char string2[]);
void sortedDescending(MaterialService* ms, TElem* new_array, int* dim, char string[]);
void sortedSupplier(MaterialService* ms, TElem* new_array, int* dim, char string[], int quantity);
void destroyService(MaterialService* ms);
void testsService();